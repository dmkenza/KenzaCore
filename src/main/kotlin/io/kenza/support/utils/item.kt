package io.kenza.support.utils

import io.kenza.support.utils.base.gson
import io.kenza.support.annotations.NbtKey
import io.kenza.support.utils.kotlin.createDefaultObject
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import kotlin.reflect.full.findAnnotation

fun ItemStack?.writeJsonNbt(key: String, function: () -> String) {
    val nbt = this?.nbt
    nbt?.putString(key, function.invoke())
}

fun ItemStack?.writeJsonNbt(key: String, json: String) {
    val nbt = this?.getOrCreateSubNbt(key)
    nbt?.putString("json", json)
}

fun ItemStack?.readJsonNbt(key: String) : String {
    val nbt = this?.nbt
    return  kotlin.runCatching {
        nbt?.getString(key).toString()
    }.getOrNull() ?: ""
}

fun ItemStack.incremenDamage() {
    this.damage = (this.damage + 1)
}

fun Item.id(): Identifier? {
    return this.`arch$registryName`()
}

inline fun <reified T> ItemStack?.readData(): T {
    val key = T::class.findAnnotation<NbtKey>()?.value ?: throw Exception("Data Class should have NbtKey")
    val data = readJsonNbt(key).run {
        gson.fromJson(this, T::class.java) ?: T::class.createDefaultObject() as T
    }
    return data
}


inline fun <reified T> ItemStack?.changeData(changeFun: (old: T) -> T): T {

    val key = T::class.findAnnotation<NbtKey>()?.value ?: throw Exception("Data Class should have NbtKey")
    val old = readJsonNbt(key).run {
        gson.fromJson(this, T::class.java) ?: T::class.createDefaultObject() as T
    }
    val new = changeFun.invoke(old)

    writeJsonNbt(key) {
        gson.toJson(new)
    }
    return new
}