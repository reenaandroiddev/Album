package com.learn.album.domain.usecases

import com.learn.album.domain.entities.Album
import com.learn.album.domain.result.Result

interface GetAlbumUseCase {
  suspend fun invoke(): Result<List<Album>>
}