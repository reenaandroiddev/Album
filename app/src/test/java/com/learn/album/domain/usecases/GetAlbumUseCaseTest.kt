package com.learn.album.domain.usecases

import com.learn.album.domain.repository.AlbumRepository
import com.learn.album.domain.usecases.getalbums.GetAlbumUseCase
import com.learn.album.domain.usecases.getalbums.GetAlbumUseCaseImpl
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAlbumUseCaseTest {
    private val albumRepository: AlbumRepository = mock()
    private lateinit var getAlbumUseCase: GetAlbumUseCase

    @Before
    fun setUp() {
        getAlbumUseCase = GetAlbumUseCaseImpl(albumRepository)
    }

    @Test
    fun `test GetAlbumUseCase calls AlbumRepository`() {
        runBlocking {
            //Act
            getAlbumUseCase.invoke(any())

            //Assert
            verify(albumRepository, times(1)).getAlbums()
        }
    }

}