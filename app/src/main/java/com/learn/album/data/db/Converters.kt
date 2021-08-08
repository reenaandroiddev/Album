package com.learn.album.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
  private val gson = Gson()

  @TypeConverter
  fun fromAlbumListToJson(list: List<AlbumModel>?): String {
    return list?.let { gson.toJson(it) } ?: ""
  }

  @TypeConverter
  fun fromJsonToAlbumList(jsonList: String): List<AlbumModel> {
    val listType = object : TypeToken<List<AlbumModel>>() {}.type
    return gson.fromJson(jsonList, listType)
  }

}