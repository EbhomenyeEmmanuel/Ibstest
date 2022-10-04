package com.emmanull.ibstest.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emmanull.ibstest.data.di.ServiceLocator
import com.emmanull.ibstest.domain.model.HomeUiState
import com.emmanull.ibstest.domain.model.LoginUiState
import com.emmanull.ibstest.domain.repositories.HomeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeItem(
    val id: Int,
    val name: String,
    val accountNumber: String,
    val phone: String,
    val icon: Int = 0,
)

class HomeViewModel: ViewModel() {

    private val _uiState =
        MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        onSearchHomeItems()
    }

    fun onSearchHomeItems() {
        viewModelScope.launch {
            delay(2000)
            _uiState.update { it.copy(isLoading = false, homeList = homeList) }
        }
    }

    val homeList
        get() = listOf<HomeItem>(
            HomeItem(0, "Vitol Services Limited", "12345", "900009"),
            HomeItem(1, "Vitol Services Limited2", "12345", "900009"),
        )

}