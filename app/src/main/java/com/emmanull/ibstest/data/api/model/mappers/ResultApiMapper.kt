package com.emmanull.ibstest.data.api.model.mappers

import com.emmanull.ibstest.data.api.model.ApiResults
import com.emmanull.ibstest.ui.home.HomeItem

class ResultApiMapper : ApiMapper<ApiResults, HomeItem> {
    override fun mapToDomain(apiEntity: ApiResults): HomeItem {
        return with(apiEntity) {
            HomeItem(
                id = id?.value ?: "",
                name = "${name?.title ?: ""} ${name?.first ?: ""} ${name?.last ?: ""}",
                phone = phone ?: "",
                email = email ?: "",
                icon = picture?.medium ?: ""
            )
        }
    }
}