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
  override suspend fun getAlbums(): Result<List<Album>> {
    var albumList: List<Album>? = null
    if (connectivity.hasNetwork()) {
      withContext(Dispatchers.IO)
      {
        val apiAlbumList = albumApi.getAlbums().body()?.let { AlbumResponseToModelMapper.map(it) }
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


}
