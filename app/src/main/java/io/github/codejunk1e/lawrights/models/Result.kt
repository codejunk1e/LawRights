package io.github.codejunk1e.lawrights.models

sealed class Result<out T> {
    object Loading : Result<Nothing>()
    data class Error(val error: String) : Result<Nothing>()
    data class Success <T>(val item: T) : Result<T>()
}