package ru.grandibambino.testforlifehackstudio.presentation

sealed class DataResult {

    class Success<out T>(val data: T?) : DataResult()
    class Error(val error: Throwable) : DataResult()

}