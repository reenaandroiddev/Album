package com.learn.album.di

import com.learn.album.data.repository.AlbumRepositoryImpl
import com.learn.album.data.utils.Connectivity
import com.learn.album.data.utils.ConnectivityImpl
import com.learn.album.domain.repository.AlbumRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
  factory<Connectivity> { ConnectivityImpl(androidContext()) }
  factory<AlbumRepository> { AlbumRepositoryImpl(get(), get(), get()) }
}