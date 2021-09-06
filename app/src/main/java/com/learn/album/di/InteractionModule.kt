package com.learn.album.di

import com.learn.album.domain.usecases.getalbums.GetAlbumUseCase
import com.learn.album.domain.usecases.getalbums.GetAlbumUseCaseImpl
import com.learn.album.domain.usecases.getuseralbums.GetUserAlbumUseCase
import com.learn.album.domain.usecases.getuseralbums.GetUserAlbumUseCaseImpl
import org.koin.dsl.module

val interactionModule = module {
  factory<GetAlbumUseCase> { GetAlbumUseCaseImpl(get()) }
  factory <GetUserAlbumUseCase>{GetUserAlbumUseCaseImpl(get())}
}