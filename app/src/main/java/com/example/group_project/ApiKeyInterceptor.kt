package com.example.group_project

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
<<<<<<< HEAD
            .addHeader("X-RapidAPI-Key", "9c5c3415ccmshf8d198ed0eab45bp1b1d77jsnaa4c9db208ea")
=======
            .addHeader("X-RapidAPI-Key", "5b1ee3b3fdmsh3a24dbd1237204dp14bf21jsn175b41d48588")
>>>>>>> dc606d6a6061a066853ef3a5fee449f187fb13a4
            .addHeader("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
            .build()
        return chain.proceed(newRequest)
    }
}
