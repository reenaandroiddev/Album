package com.learn.album.di

import com.learn.album.domain.usecases.GetAlbumUseCase
import com.learn.album.domain.usecases.GetAlbumUseCaseImpl
import org.koin.dsl.module

val interactionModule = module {
  factory<GetAlbumUseCase> { GetAlbumUseCaseImpl(get()) }
}