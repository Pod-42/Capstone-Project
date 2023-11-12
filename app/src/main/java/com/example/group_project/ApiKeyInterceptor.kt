package com.example.group_project

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("X-RapidAPI-Key", "Your API KEY")
            .addHeader("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
            .build()
        return chain.proceed(newRequest)
    }
}
