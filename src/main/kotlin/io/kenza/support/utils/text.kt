package io.kenza.support.utils

import net.minecraft.text.MutableText
import net.minecraft.text.Text

fun translatable(text: String, vararg args: Any): MutableText = Text.translatable(text, *args)
fun literal(text: String): MutableText = Text.literal(text)

val EMPTY = literal("")

