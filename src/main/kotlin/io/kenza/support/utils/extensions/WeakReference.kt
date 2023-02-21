package io.kenza.support.utils.extensions

import java.lang.ref.WeakReference

inline fun <reified T> weak(referent: T): WeakReference<T> = WeakReference(referent)
