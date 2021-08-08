package com.learn.album.domain.usecases

import com.learn.album.domain.entities.Album
import com.learn.album.domain.repository.AlbumRepository
import com.learn.album.domain.result.Result

class GetAlbumUseCaseImpl(private val albumRepository: AlbumRepository) : GetAlbumUseCase {
  override suspend fun invoke(): Result<List<Album>> {
    return albumRepository.getAlbums()
  }
}