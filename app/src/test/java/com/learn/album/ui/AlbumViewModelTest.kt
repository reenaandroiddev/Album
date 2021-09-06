package com.learn.album.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.learn.album.domain.result.Result
import com.learn.album.domain.usecases.getalbums.GetAlbumUseCase
import com.learn.album.getAlbumEntity
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AlbumViewModelTest {

  private val getAlbumUseCase: GetAlbumUseCase = mock()
  private lateinit var albumViewModel: AlbumViewModel

  @get:Rule
  var instantExecutorRule = InstantTaskExecutorRule()

  @ExperimentalCoroutinesApi
  @get:Rule
  var mainCoroutineRule = MainCoroutineRule()


  @Before
  fun setUp() {
    albumViewModel = AlbumViewModel(getAlbumUseCase)
  }

  @ExperimentalCoroutinesApi
  @Test
  fun `call getAlbum api and return result upon success send sorted albums list to the view`() {

    mainCoroutineRule.runBlockingTest {
      //Arrange
      whenever(getAlbumUseCase.invoke()).thenReturn(Result.Success(getAlbumEntity()))

      //Act
      albumViewModel.loadAlbums()

      //Assert
      val list = albumViewModel.albumList.getOrAwaitValue()
      assertEquals(5, list[0].id)
      assertEquals(3, list[0].userId)
      assertEquals("eaque aut omnis a", list[0].title)

      assertEquals(4, list[1].id)
      assertEquals(2, list[1].userId)
      assertEquals("non esse culpa molestiae omnis sed optio", list[1].title)

      assertEquals(3, list[2].id)
      assertEquals(1, list[2].userId)
      assertEquals("omnis laborum odio", list[2].title)

      assertEquals(6, list[3].id)
      assertEquals(4, list[3].userId)
      assertEquals("quibusdam autem aliquid et et quia", list[3].title)

      assertEquals(1, list[4].id)
      assertEquals(1, list[4].userId)
      assertEquals("quidem molestiae enim", list[4].title)

    }

  }

  @ExperimentalCoroutinesApi
  @Test
  fun `call getAlbum api and capture error in case of no data available`() {
    mainCoroutineRule.runBlockingTest {
      //Arrange
      whenever(getAlbumUseCase.invoke()).thenReturn(Result.Error(Throwable("api error")))

      //Act
      albumViewModel.loadAlbums()

      //Assert
      val error = albumViewModel.error.getOrAwaitValue()
      assertEquals("api error", error)
    }
  }
}