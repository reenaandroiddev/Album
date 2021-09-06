package com.learn.album.domain.repository

import com.learn.album.domain.entities.Album
import com.learn.album.domain.result.Result

interface AlbumRepository {
    suspend fun getAlbums(): Result<List<Album>>
    suspend fun getUserAlbums(userId: Int): Result<List<Album>>
}