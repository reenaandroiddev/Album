package com.learn.album.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.learn.album.data.utils.ALBUM_TABLE_NAME

@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAlbum(albumModel: AlbumModel)

    @Query("SELECT *FROM $ALBUM_TABLE_NAME")
    suspend fun getAlbumsFromDb(): List<AlbumModel>

    @Query("SELECT *FROM $ALBUM_TABLE_NAME WHERE userId=:userId")
    suspend fun getUserAlbumsFromDb(userId: Int): List<AlbumModel>
}