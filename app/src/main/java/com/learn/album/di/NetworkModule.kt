package com.learn.album.di

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.learn.album.BuildConfig
import com.learn.album.data.remote.AlbumApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

val networkModule = module(override = true) {
    single { get<Retrofit>().create(AlbumApi::class.java) }
    single { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }
    single {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder()
                        .also { it.addHeader("Authorization", "Bearer tokjlkhklhhh")}.build())
            }
    }
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(AlbumApi::class.java)
    }
}