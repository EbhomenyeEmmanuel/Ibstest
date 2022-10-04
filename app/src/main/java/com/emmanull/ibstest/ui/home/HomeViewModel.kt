package com.emmanull.ibstest.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emmanull.ibstest.App.Companion.serviceLocator
import com.emmanull.ibstest.domain.model.HomeUiState
import com.emmanull.ibstest.domain.repositories.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeItem(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val icon: String,
)

class HomeViewModel(
    private val homeRepository: HomeRepository = serviceLocator.provideHomeRepository(),
) :
    ViewModel() {

    private val _uiState =
        MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        onSearchHomeItems()
    }

    fun onSearchHomeItems() {
        viewModelScope.launch {
            homeRepository.getListDetails().flowOn(Dispatchers.IO)
                .catch {
                    _uiState.update {
                        it.copy(
                            errorMessage = "An error has occurred!",
                            isLoading = false,
                            homeList = null
                        )
                    }
                }
                .collect { list ->
                    _uiState.update { it.copy(isLoading = false, homeList = list) }
                }
        }
    }

    fun onErrorShown() {
        _uiState.update {
            it.copy(isLoading = false, errorMessage = null, homeList = null)
        }
    }
}