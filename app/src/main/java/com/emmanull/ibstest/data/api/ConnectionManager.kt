package com.emmanull.ibstest.data.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class ConnectionManager (private val context: Context) {

    fun isConnected(): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = manager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting == true
    }
}