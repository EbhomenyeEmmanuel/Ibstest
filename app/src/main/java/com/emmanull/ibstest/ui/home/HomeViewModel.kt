package com.emmanull.ibstest.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emmanull.ibstest.App.Companion.serviceLocator
import com.emmanull.ibstest.domain.model.HomeUiState
import com.emmanull.ibstest.domain.repositories.HomeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeItem(
    val id: String,
    val name: String,
    val accountNumber: String,
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
            delay(2000)
            homeRepository.getListDetails().catch { }.collect { list ->
                _uiState.update { it.copy(isLoading = false, homeList = list) }
            }
        }
    }
}