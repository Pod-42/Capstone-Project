package com.example.group_project

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("X-RapidAPI-Key", "18f06aa827msh381ea3eadf3ea75p1828c3jsne7f3ec96a4a0")
            .addHeader("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
            .build()
        return chain.proceed(newRequest)
    }
}
