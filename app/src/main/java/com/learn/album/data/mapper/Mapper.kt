package com.learn.album.data.mapper

interface Mapper<in From, out To> {
    fun map(from: From): To
}