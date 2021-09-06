package com.learn.album.domain.usecases.getalbums

import com.learn.album.domain.entities.Album
import com.learn.album.domain.repository.AlbumRepository
import com.learn.album.domain.result.Result

class GetAlbumUseCaseImpl(private val albumRepository: AlbumRepository) : GetAlbumUseCase {
    override suspend fun invoke(param: Int): Result<List<Album>> {
        return albumRepository.getAlbums()
    }

}