package fm.force.ui.util

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.await
import kotlinx.coroutines.promise

suspend fun <T, R> Iterator<T>.runParallel(transform: suspend (T) -> R): List<R> =
    this
        .asSequence().toList()
        .map { GlobalScope.promise { transform(it) } }
        .map { it.await() }

suspend fun <T, R> Iterator<T>.runParallelIndexed(transform: suspend (index: Int, item: T) -> R): List<R> =
    this
        .asSequence().toList()
        .mapIndexed { index, item -> GlobalScope.promise { transform(index, item) } }
        .map { it.await() }

suspend fun <T, R> Collection<T>.runParallel(transform: suspend (T) -> R): List<R> =
    this
        .map { GlobalScope.promise { transform(it) } }
        .map { it.await() }

suspend fun <T, R> Array<T>.runParallelIndexed(transform: suspend (index: Int, item: T) -> R): List<R> =
    this
        .mapIndexed { index, item -> GlobalScope.promise { transform(index, item) } }
        .map { it.await() }
