package com.learn.album.data.mapper

import com.learn.album.data.db.AlbumModel
import com.learn.album.domain.entities.Album

object AlbumModelToDomainEntityMapper : Mapper<List<AlbumModel>, List<Album>> {
  override fun map(from: List<AlbumModel>): List<Album> {
    return from.map {
      Album(id = it.id, userId = it.userId, title = it.title)
    }
  }
}