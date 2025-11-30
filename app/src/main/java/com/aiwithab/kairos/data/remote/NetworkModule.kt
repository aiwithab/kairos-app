package com.aiwithab.kairos.data.remote

import com.aiwithab.kairos.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Network module for configuring Retrofit and OkHttp clients
 * Provides singleton instances for API communication
 */
object NetworkModule {
    
    /**
     * Base URL for the API
     * TODO: Move to BuildConfig for different environments (dev, staging, prod)
     */
    private const val BASE_URL = "http://192.168.1.25:8000"
    
    /**
     * HTTP logging interceptor for debugging network requests
     */
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    
    /**
     * Configured OkHttp client with timeouts and logging
     */
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(Constants.NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(Constants.NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(Constants.NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .callTimeout(Constants.NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .build()
    
    /**
     * Retrofit instance configured with Gson converter
     */
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    
    /**
     * API service instance
     */
    val apiService: ApiService = retrofit.create(ApiService::class.java)
}
