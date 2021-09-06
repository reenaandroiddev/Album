package com.learn.album.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumApi {
    @GET("albums")
    suspend fun getAlbums(): Response<List<AlbumResponse>>

    @GET("users/{userId}/albums")
    suspend fun getUserAlbums(@Path("userId") userId: Int): Response<List<AlbumResponse>>
}