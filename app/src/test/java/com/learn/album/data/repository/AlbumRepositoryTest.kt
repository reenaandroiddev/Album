package com.learn.album.data.repository

import com.learn.album.FAKE_FAILURE_ERROR_CODE
import com.learn.album.data.db.AlbumDao
import com.learn.album.data.remote.AlbumApi
import com.learn.album.data.utils.Connectivity
import com.learn.album.failureResponseBody
import com.learn.album.getAlbumModel
import com.learn.album.getAlbumResponse
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class AlbumRepositoryTest {
  private val albumApi: AlbumApi = mock()
  private val albumDao: AlbumDao = mock()
  private val connectivity: Connectivity = mock()

  private lateinit var albumRepository: AlbumRepositoryImpl

  @Before
  fun setUp() {
    albumRepository = AlbumRepositoryImpl(albumApi, albumDao, connectivity)
  }

  @Test
  fun `test getAlbums call api and saves data to the db upon success`() {
    runBlocking {
      //Arrange
      whenever(connectivity.hasNetwork()).thenReturn(true)
      whenever(albumApi.getAlbums()).thenReturn(Response.success(getAlbumResponse()))
      whenever(albumDao.getAlbumsFromDb()).thenReturn(getAlbumModel())

      //Act
      albumRepository.getAlbums()

      //Assert
      verify(albumApi, times(1)).getAlbums()
      verify(albumDao, times(1)).getAlbumsFromDb()
    }
  }

  @Test
  fun `test getAlbums calls api and returns error when apis fails`() {
    runBlocking {
      whenever(connectivity.hasNetwork()).thenReturn(true)
      whenever(albumApi.getAlbums())
        .thenReturn(Response.error(FAKE_FAILURE_ERROR_CODE, failureResponseBody))

      albumRepository.getAlbums()

      verify(albumApi, times(1)).getAlbums()
      verify(albumDao, times(1)).getAlbumsFromDb()
    }
  }

  @Test
  fun `test getAlbums calls api and returns cached data from db upon failure `() {
    runBlocking {
      //Arrange
      whenever(connectivity.hasNetwork()).thenReturn(false)
      whenever(albumDao.getAlbumsFromDb()).thenReturn(getAlbumModel())

      //Act
      albumRepository.getAlbums()

      //Assert
      verify(albumApi, never()).getAlbums()
      verify(albumDao, times(1)).getAlbumsFromDb()
    }
  }

  @Test
  fun `test getAlbums calls api and returns error when no data available in cache`() {
    runBlocking {
      //Arrange
      whenever(connectivity.hasNetwork()).thenReturn(false)

      //Act
      albumRepository.getAlbums()

      //Assert
      verify(albumApi, never()).getAlbums()
      verify(albumDao, times(1)).getAlbumsFromDb()
    }
  }


}