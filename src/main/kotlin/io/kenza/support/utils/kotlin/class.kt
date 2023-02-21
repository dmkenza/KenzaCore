package io.kenza.support.utils.kotlin

import kotlin.reflect.KClass
import org.apache.commons.lang3.ClassUtils
import kotlin.reflect.KParameter

fun KClass<*>.createDefaultObject(): Any? {
    val args = this.constructors.firstOrNull()
        ?.parameters
        ?.filterNot { it.isOptional }
        ?.associate { it to handleParam(it) }
        ?: return null

    return this.constructors.first().callBy(args)
}

// And finally my type handler
private fun handleParam(param: KParameter): Any? = when (param.type.classifier) {
    String::class -> ""
    Boolean::class -> false
    else -> (param.type.classifier as? KClass<*>)?.run {
        kotlin.runCatching {
//            Class.forName(qualifiedName).kotlin
            ClassUtils.getClass(qualifiedName).kotlin
        }.getOrNull()
    }?.createDefaultObject()
}





