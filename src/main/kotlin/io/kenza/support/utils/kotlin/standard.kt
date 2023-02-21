package io.kenza.support.utils.kotlin

inline fun <reified T> Any?.safeCast() = this as? T

fun Any?.isNull() = this == null

infix fun <A, B, C> Pair<A, B>.to(that: C): Triple<A, B, C> = Triple(first, second, that)