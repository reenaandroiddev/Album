package com.learn.album.domain.usecases.getuseralbums

import com.learn.album.domain.entities.Album
import com.learn.album.domain.repository.AlbumRepository
import com.learn.album.domain.result.Result

class GetUserAlbumUseCaseImpl(private val repository: AlbumRepository) : GetUserAlbumUseCase {
    override suspend fun invoke(param: Int): Result<List<Album>> {
        return repository.getUserAlbums(param)
    }

}