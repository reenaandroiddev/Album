package com.learn.album.domain.entities

data class Album(val id: Int, val userId: Int, val title: String) {

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as Album

    if (id != other.id) return false
    if (userId != other.userId) return false
    if (title != other.title) return false

    return true
  }

  override fun toString(): String {
    return "Album(id=$id, userId=$userId, title='$title')"
  }

  override fun hashCode(): Int {
    var result = id
    result = 31 * result + userId
    result = 31 * result + title.hashCode()
    return result
  }
}