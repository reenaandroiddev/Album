package com.learn.album.di

import com.learn.album.ui.AlbumViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    factory { AlbumViewModel(get(), get()) }
}