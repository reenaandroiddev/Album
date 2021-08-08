package com.learn.album.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.learn.album.databinding.ListItemAlbumBinding
import com.learn.album.domain.entities.Album

class AlbumViewHolder(private val binding: ListItemAlbumBinding) :
  RecyclerView.ViewHolder(binding.root) {
  fun bind(item: Album) {
    binding.item = item
  }
}