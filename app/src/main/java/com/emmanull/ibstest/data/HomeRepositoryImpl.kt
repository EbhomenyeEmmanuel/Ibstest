package com.emmanull.ibstest.data

import com.emmanull.ibstest.data.api.ApiService
import com.emmanull.ibstest.data.api.model.mappers.ResultApiMapper
import com.emmanull.ibstest.domain.repositories.HomeRepository
import com.emmanull.ibstest.ui.home.HomeItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class HomeRepositoryImpl(
    private val apiService: ApiService,
    private val resultApiMapper: ResultApiMapper = ResultApiMapper()
) : HomeRepository {
    override suspend fun getListDetails(): Flow<List<HomeItem>> {
        val response = apiService.getResults()
        return if (response.isSuccessful && response.body()?.results != null) {
            flow { emit(response.body()!!.results!!.map { item -> resultApiMapper.mapToDomain(item) }) }
        } else {
            throw RuntimeException(response.message())
        }
    }
}