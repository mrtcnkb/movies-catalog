package com.muratcan.moviescatalog.api

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.muratcan.moviescatalog.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by MuratCan on 2019-10-17.
 */

fun provideOkhttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(StethoInterceptor())
        .build()
}

fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.HOST)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
}

fun provideService(retrofit: Retrofit): Service = retrofit.create(Service::class.java)
