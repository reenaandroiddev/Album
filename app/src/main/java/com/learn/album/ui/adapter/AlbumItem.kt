package com.learn.album.ui.adapter

import com.learn.album.domain.entities.Album

sealed class AlbumItem {
    data class Header(var title: Int) : AlbumItem()
    data class Item(val albumList: List<Album>) : AlbumItem()
}
