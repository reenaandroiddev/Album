package com.learn.album.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.learn.album.data.utils.ALBUM_TABLE_NAME

@Entity(tableName = ALBUM_TABLE_NAME)
data class AlbumModel(
  @PrimaryKey val id: Int,
  val userId: Int,
  val title: String
)