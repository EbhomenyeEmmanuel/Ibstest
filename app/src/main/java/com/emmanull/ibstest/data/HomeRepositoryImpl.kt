package com.emmanull.ibstest.data

import com.emmanull.ibstest.data.api.ApiService
import com.emmanull.ibstest.data.api.model.mappers.ResultApiMapper
import com.emmanull.ibstest.domain.repositories.HomeRepository
import com.emmanull.ibstest.ui.home.HomeItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class HomeRepositoryImpl(
    private val apiService: ApiService,
    private val resultApiMapper: ResultApiMapper = ResultApiMapper()
) : HomeRepository {
    override suspend fun getListDetails(): Flow<List<HomeItem>> {
        return flow {
            val response = withContext(Dispatchers.IO) {
                val deferreds = listOf(
                    async {
                        apiService.getResults()
                    },
                    async {
                        apiService.getResults()
                    },
                    async {
                        apiService.getResults()
                    })
                deferreds.awaitAll()
            }
            emit(response.flatMap { items ->
                items.results?.map {
                    resultApiMapper.mapToDomain(it)
                } as List<HomeItem>
            })
        }
    }
}