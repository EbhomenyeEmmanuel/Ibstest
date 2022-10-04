package com.emmanull.ibstest.data.api.interceptors

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor

class LoggingInterceptor() : HttpLoggingInterceptor.Logger {
    val TAG = this::class.java.simpleName

    override fun log(message: String) {
        Log.i(TAG, message)
    }
}