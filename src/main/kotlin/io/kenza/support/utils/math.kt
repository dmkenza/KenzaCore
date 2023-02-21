package io.kenza.support.utils

import kotlin.math.absoluteValue


object KMath{
    fun getBetween(from: Int, to: Int) = (java.lang.Math.random() * (to - from) + from).toInt()
    fun getBetween(from: Float, to: Float) = (java.lang.Math.random() * (to - from) + from).toFloat()
    fun getBetween(from: Double, to: Double) = (java.lang.Math.random() * (to - from) + from).toDouble()
}

fun Double.cutByRange( min : Double, max: Double): Double {
    if(this > max){
        return max
    }
    if(this < min){
        return min
    }
    return  this
}


fun <E> List<E>.pickRandomly(): E {
    val index = KMath.getBetween(0  , size.absoluteValue - 1)
    return get(index)
}



