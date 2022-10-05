package com.emmanull.ibstest.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emmanull.ibstest.App.Companion.serviceLocator
import com.emmanull.ibstest.domain.model.HomeUiState
import com.emmanull.ibstest.domain.repositories.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeItem(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val icon: String,
)

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class HomeViewModel(
    private val homeRepository: HomeRepository = serviceLocator.provideHomeRepository(),
) :
    ViewModel() {

    private val _uiState =
        MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        onFetchHomeItems()
    }

    private fun onFetchHomeItems() {
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

    fun search(key: String) {
        val keyState =
            MutableStateFlow(key)

        viewModelScope.launch {
            keyState.debounce(300).filter { query ->
                return@filter query.isNotEmpty()
            }
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    flow {
                        emit(_uiState.value.homeList?.find {
                            it.name.lowercase().contains(key.lowercase())
                        })
                    }
                }
                .flowOn(Dispatchers.Default)
                .collect { result ->
                    _uiState.update {
                        it.copy(
                            errorMessage = if (result == null) {
                                "Not Found!"
                            } else null,
                            homeList = result?.let { item -> listOf(item) }
                        )
                    }
                }
        }

//      Another way of searching
//        if (key.isNotEmpty()) {
//            _uiState.update {
//                val list = it.homeList?.find {
//                    it.name.lowercase().contains(key.lowercase())
//                }
//                it.copy(
//                    errorMessage = if (list == null) "Not Found!" else null,
//                    homeList = list?.let { item -> listOf(item) }
//                )
//            }
//        }

    }

    fun onErrorShown() {
        _uiState.update {
            it.copy(isLoading = false, errorMessage = null, homeList = null)
        }
    }
}