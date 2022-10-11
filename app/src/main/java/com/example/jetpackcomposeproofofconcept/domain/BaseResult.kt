package com.example.jetpackcomposeproofofconcept.domain

sealed class BaseResult<out T : Any, out U : Any> {
    data class Success<T : Any>(val data: T) : BaseResult<T, Nothing>()
    data class Failure<U : Any>(val rawResponse: U) : BaseResult<Nothing, U>()
}
