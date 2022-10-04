package com.emmanull.ibstest.domain.model

import com.emmanull.ibstest.ui.home.HomeItem

data class HomeUiState(
    val isLoading: Boolean = true,
    val homeList: List<HomeItem>? = null,
)