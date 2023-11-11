package com.example.group_project

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("X-RapidAPI-Key", "9c5c3415ccmshf8d198ed0eab45bp1b1d77jsnaa4c9db208ea")
            .addHeader("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
            .build()
        return chain.proceed(newRequest)
    }
}
