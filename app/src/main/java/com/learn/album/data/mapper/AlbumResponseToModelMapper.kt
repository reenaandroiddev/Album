package com.learn.album.data.mapper

import com.learn.album.data.db.AlbumModel
import com.learn.album.data.model.AlbumResponse

object AlbumResponseToModelMapper : Mapper<List<AlbumResponse>, List<AlbumModel>> {
  override fun map(from: List<AlbumResponse>): List<AlbumModel> {
    return from.map {
      AlbumModel(id = it.id, userId = it.userId, title = it.title)
    }
  }
}