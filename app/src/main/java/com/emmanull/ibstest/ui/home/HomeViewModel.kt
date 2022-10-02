package com.emmanull.ibstest.ui.home

import androidx.lifecycle.ViewModel

class HomeItem(
    val id: Int,
    val name: String,
    val accountNumber: String,
    val phone: String
)

class HomeViewModel : ViewModel() {
    fun onSearchHomeItems() {

    }

    val homeList
        get() = listOf<HomeItem>(
            HomeItem(0, "Vitol Services Limited", "12345", "900009"),
            HomeItem(1, "Vitol Services Limited2", "12345", "900009"),
        )

}