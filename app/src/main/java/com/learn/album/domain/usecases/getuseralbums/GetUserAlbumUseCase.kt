package com.learn.album.domain.usecases.getuseralbums

import com.learn.album.domain.entities.Album
import com.learn.album.domain.result.Result
import com.learn.album.domain.usecases.BaseUseCase

interface GetUserAlbumUseCase : BaseUseCase<Int, List<Album>> {
    override suspend fun invoke(param: Int): Result<List<Album>>
}