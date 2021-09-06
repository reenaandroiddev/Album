package com.learn.album.domain.usecases

import com.learn.album.domain.result.Result

interface BaseUseCase<in T : Any, out R : Any> {
    suspend fun invoke(param: T): Result<R>
}