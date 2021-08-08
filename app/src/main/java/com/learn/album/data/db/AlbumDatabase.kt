package com.learn.album.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [AlbumModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AlbumDatabase : RoomDatabase() {
  abstract fun albumDao(): AlbumDao
}