package com.learn.album

import com.learn.album.data.db.AlbumModel
import com.learn.album.data.model.AlbumResponse
import com.learn.album.domain.entities.Album
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody

fun getAlbumResponse(): List<AlbumResponse> {
  return listOf(
    AlbumResponse(1, 1, "quidem molestiae enim"),
    AlbumResponse(2, 1, "sunt qui excepturi placeat culpa"),
    AlbumResponse(3, 1, "omnis laborum odio"),
    AlbumResponse(4, 2, "non esse culpa molestiae omnis sed optio"),
    AlbumResponse(5, 3, "eaque aut omnis a"),
    AlbumResponse(6, 4, "quibusdam autem aliquid et et quia")
  )
}

fun getAlbumEntity(): List<Album> {
  return listOf(
    Album(1, 1, "quidem molestiae enim"),
    Album(2, 1, "sunt qui excepturi placeat culpa"),
    Album(3, 1, "omnis laborum odio"),
    Album(4, 2, "non esse culpa molestiae omnis sed optio"),
    Album(5, 3, "eaque aut omnis a"),
    Album(6, 4, "quibusdam autem aliquid et et quia")
  )
}

fun getAlbumModel(): List<AlbumModel> {
  return listOf(
    AlbumModel(1, 1, "quidem molestiae enim"),
    AlbumModel(2, 1, "sunt qui excepturi placeat culpa"),
    AlbumModel(3, 1, "omnis laborum odio"),
    AlbumModel(4, 2, "non esse culpa molestiae omnis sed optio"),
    AlbumModel(5, 3, "eaque aut omnis a"),
    AlbumModel(6, 4, "quibusdam autem aliquid et et quia")
  )
}

val failureResponseBody = ResponseBody.create("text".toMediaTypeOrNull(), "network error")
const val FAKE_FAILURE_ERROR_CODE = 400


