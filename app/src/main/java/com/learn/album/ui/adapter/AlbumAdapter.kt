package com.learn.album.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.learn.album.R
import com.learn.album.databinding.ListItemAlbumBinding
import com.learn.album.domain.entities.Album

class AlbumAdapter : RecyclerView.Adapter<AlbumViewHolder>() {
  private lateinit var albumList: List<Album>

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
    val binding: ListItemAlbumBinding = DataBindingUtil.inflate(
      LayoutInflater.from(parent.context),
      R.layout.list_item_album, parent, false
    )
    return AlbumViewHolder(binding)
  }

  override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
    holder.bind(albumList[position])
  }

  override fun getItemCount(): Int {
    return if (::albumList.isInitialized) albumList.size else 0
  }

  fun setAlbumItem(list: List<Album>) {
    albumList = list
    notifyDataSetChanged()
  }
}