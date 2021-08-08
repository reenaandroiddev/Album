package com.learn.album.di

import androidx.room.Room
import com.learn.album.data.db.AlbumDatabase
import com.learn.album.data.utils.ALBUM_DB
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
  single {
    Room.databaseBuilder(androidContext(), AlbumDatabase::class.java, ALBUM_DB)
      .fallbackToDestructiveMigration()
      .build()
  }
  factory { get<AlbumDatabase>().albumDao() }
}