package com.learn.album.data.remote

import com.learn.album.data.model.AlbumResponse
import retrofit2.Response
import retrofit2.http.GET

interface AlbumApi {
  @GET("albums")
  suspend fun getAlbums(): Response<List<AlbumResponse>>
}