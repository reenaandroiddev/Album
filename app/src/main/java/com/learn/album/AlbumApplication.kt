package com.learn.album

import android.app.Application
import com.learn.album.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AlbumApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidContext(this@AlbumApplication)
      modules(appModules + dataModules + domainModules)
    }
  }
}

val appModules = listOf(presentationModule)
val dataModules = listOf(networkModule, databaseModule, repositoryModule)
val domainModules = listOf(interactionModule)