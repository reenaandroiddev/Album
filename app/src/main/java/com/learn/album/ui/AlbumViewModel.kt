package com.learn.album.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learn.album.domain.entities.Album
import com.learn.album.domain.result.Result
import com.learn.album.domain.usecases.GetAlbumUseCase
import com.learn.album.ui.event.SingleLiveEvent
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class AlbumViewModel(
  private val getAlbumUseCase: GetAlbumUseCase
) : ViewModel(), CoroutineScope {

  private val job = Job()
  override val coroutineContext: CoroutineContext = Dispatchers.IO + job

  val albumList = MutableLiveData<List<Album>>()
  val error = SingleLiveEvent<String>()

  fun loadAlbums() {
    launch {
      val result = getAlbumUseCase.invoke()
      withContext(Dispatchers.Main)
      {
        when (result) {
          is Result.Success -> albumList.value = result.data.sortedBy { it.title }
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