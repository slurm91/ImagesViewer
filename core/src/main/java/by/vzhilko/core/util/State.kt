package by.vzhilko.core.util

interface State<out T : Any>

sealed class DefaultState<out T : Any> : State<T> {
    class Success<out T : Any>(val value: T) : DefaultState<T>()
    class Error(val error: Throwable): DefaultState<Nothing>()
    object Loading : DefaultState<Nothing>()
    object NoState : DefaultState<Nothing>()
}

