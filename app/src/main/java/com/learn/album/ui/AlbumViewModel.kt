package com.learn.album.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learn.album.domain.entities.Album
import com.learn.album.domain.result.Result
import com.learn.album.domain.usecases.getalbums.GetAlbumUseCase
import com.learn.album.domain.usecases.getuseralbums.GetUserAlbumUseCase
import com.learn.album.ui.adapter.AlbumItem
import com.learn.album.ui.event.SingleLiveEvent
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class AlbumViewModel(
    private val getAlbumUseCase: GetAlbumUseCase,
    private val getUserAlbumUseCase: GetUserAlbumUseCase
) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.IO + job

    val albumList = MutableLiveData<List<Album>>()
    val error = SingleLiveEvent<String>()
    val tempAlbumList = mutableListOf<Album>()
    val albumListSealed = mutableListOf<AlbumItem>()

    val userId = MutableLiveData(1)

    fun loadAlbums() {
        launch {
            val result = userId.value?.let { getUserAlbumUseCase.invoke(it) }
            withContext(Dispatchers.Main)
            {
                when (result) {
                    is Result.Success -> {
                        result.data.forEach {
                            val  header: AlbumItem.Header = AlbumItem.Header(it.userId)
                            val albumList :AlbumItem.Item = AlbumItem.Item(mutableListOf())
                            albumListSealed.add(header)
                            albumListSealed.add(albumList)

                            tempAlbumList.add(it)
                        }
                        albumList.value = tempAlbumList.sortedBy { it.title }
                    }
                    is Result.Error -> error.value = result.exception.message
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}