package com.learn.album.data.repository

import com.learn.album.data.db.AlbumDao
import com.learn.album.data.mapper.AlbumModelToDomainEntityMapper
import com.learn.album.data.mapper.AlbumResponseToModelMapper
import com.learn.album.data.remote.AlbumApi
import com.learn.album.data.utils.Connectivity
import com.learn.album.data.utils.DB_ENTRY_ERROR
import com.learn.album.domain.entities.Album
import com.learn.album.domain.repository.AlbumRepository
import com.learn.album.domain.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AlbumRepositoryImpl(
    private val albumApi: AlbumApi,
    private val albumDao: AlbumDao,
    private val connectivity: Connectivity
) : AlbumRepository {
    var albumList: List<Album>? = null

    override suspend fun getAlbums(): Result<List<Album>> {
        if (connectivity.hasNetwork()) {
            withContext(Dispatchers.IO)
            {
                val apiAlbumList =
                    albumApi.getAlbums().body()?.let { AlbumResponseToModelMapper.map(it) }
                apiAlbumList?.map {
                    albumDao.saveAlbum(it)
                }
            }
        }
        withContext(Dispatchers.IO)
        {
            val dbAlbumList = albumDao.getAlbumsFromDb()
            if (!dbAlbumList.isNullOrEmpty()) {
                albumList = AlbumModelToDomainEntityMapper.map(dbAlbumList)
            }
        }

        return when (albumList.isNullOrEmpty()) {
            true -> Result.Error(Throwable(DB_ENTRY_ERROR))
            else -> Result.Success(albumList!!)
        }
    }

    override suspend fun getUserAlbums(userId: Int): Result<List<Album>> {
        var userAlbumList: List<Album>? = null


        withContext(Dispatchers.IO)
        {
            //Fetch From Network
            if (connectivity.hasNetwork()) {
                albumApi.getUserAlbums(userId).body()?.let {
                    AlbumResponseToModelMapper.map(it)
                }?.map { albumModel -> albumDao.saveAlbum(albumModel) }
            }

            //Fetch from Local DB
            userAlbumList =
                albumDao.getUserAlbumsFromDb(userId).let { AlbumModelToDomainEntityMapper.map(it) }

        }


        return when (userAlbumList.isNullOrEmpty()) {
            true -> Result.Error(Throwable(DB_ENTRY_ERROR))
            else -> Result.Success(userAlbumList!!)
        }
    }


}
