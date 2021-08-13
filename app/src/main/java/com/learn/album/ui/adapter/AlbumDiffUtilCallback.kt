package com.learn.album.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.learn.album.domain.entities.Album

class AlbumDiffUtilCallback(private var oldList: List<Album>, private var newList: List<Album>) :
  DiffUtil.Callback() {

  override fun getOldListSize(): Int {
    return oldList.size
  }

  override fun getNewListSize(): Int {
    return newList.size
  }

  override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return oldList[oldItemPosition].id == newList[newItemPosition].id
  }

  override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
    return oldList[oldItemPosition].equals(newList[newItemPosition])
  }
}