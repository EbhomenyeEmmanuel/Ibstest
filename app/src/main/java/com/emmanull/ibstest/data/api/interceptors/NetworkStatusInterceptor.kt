package com.emmanull.ibstest.data.api.interceptors

import com.emmanull.ibstest.data.api.ConnectionManager
import com.emmanull.ibstest.domain.NetworkUnavailableException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkStatusInterceptor (private val connectionManager: ConnectionManager) :
    Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (connectionManager.isConnected()) {
            chain.proceed(chain.request())
        } else {
            throw NetworkUnavailableException()
        }
    }
}