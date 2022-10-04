package com.emmanull.ibstest.domain.repositories

import com.emmanull.ibstest.data.api.model.ApiResults
import com.emmanull.ibstest.ui.home.HomeItem
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun getListDetails(): Flow<List<HomeItem>>

}