package com.learn.album.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.learn.album.R
import com.learn.album.databinding.ActivityAlbumBinding
import com.learn.album.ui.adapter.AlbumAdapter
import com.learn.album.ui.utils.OnProceedClickListener
import com.learn.album.ui.utils.TITLE
import com.learn.album.ui.utils.showAlert
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumActivity : AppCompatActivity() {
  private val albumAdapter: AlbumAdapter by lazy { AlbumAdapter() }
  private val albumViewModel: AlbumViewModel by viewModel()
  private lateinit var binding: ActivityAlbumBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_album)
    binding.progressCircular.visibility = View.VISIBLE

    setAdapter()
    observeLiveData()
    albumViewModel.loadAlbums()
  }

  private fun observeLiveData() {
    albumViewModel.albumList.observe(this, {
      binding.isDataAvailable = true
      binding.progressCircular.visibility = View.GONE
      albumAdapter.setAlbumItem(it)
    })

    albumViewModel.error.observe(this, {
      binding.isDataAvailable = false
      binding.progressCircular.visibility = View.GONE
      showAlert(this, it, TITLE, object : OnProceedClickListener {
        override fun proceed() {
          finish()
        }
      })
    })
  }

  private fun setAdapter() {
    binding.rvAlbum.apply {
      adapter = albumAdapter
    }
  }

}