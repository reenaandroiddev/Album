package com.learn.album.di

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.learn.album.data.remote.AlbumApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

val networkModule = module(override = true) {
  single { get<Retrofit>().create(AlbumApi::class.java) }
  single {
    Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
      .addCallAdapterFactory(CoroutineCallAdapterFactory())
      .build().create(AlbumApi::class.java)
  }
}