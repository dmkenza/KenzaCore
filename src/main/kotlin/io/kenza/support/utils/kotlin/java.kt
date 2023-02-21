package io.kenza.support.utils.kotlin

import java.util.*
import java.util.stream.Collectors
import java.util.stream.Stream

val <T> Optional<T>.value: T?
    get() = orElse(null)

fun <T> Stream<T>.collectAsList(): MutableList<T> = this.collect(Collectors.toList())
fun <T> Stream<T>.collectAsSet(): MutableSet<T> = this.collect(Collectors.toSet())

