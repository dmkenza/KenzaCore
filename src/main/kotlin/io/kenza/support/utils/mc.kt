package io.kenza.support.utils

import dev.architectury.platform.Platform
import dev.architectury.platform.Platform.isDevelopmentEnvironment
import dev.architectury.utils.Env
import net.fabricmc.api.EnvType
import net.minecraft.client.MinecraftClient

val mc: MinecraftClient
    get() = MinecraftClient.getInstance()

fun debugMsg(text: String){
    if(isDevelopmentEnvironment()){
        mc.player?.sendMessage(literal(

            text
        ))
    }
}

fun debugPrint(text: String){
    if(isDevelopmentEnvironment()){
        System.out.println(text)
    }
}

fun Any.isClientSide(): Boolean {
    return Platform.getEnvironment() == Env.CLIENT
}

fun Any.isServerSide(): Boolean {
    return Platform.getEnvironment() == Env.SERVER
}

inline fun Any.clientOnly(func: () -> Unit){
    if (Platform.getEnv() == EnvType.CLIENT) {
        func.invoke()
    }
}

//val ms: MinecraftServer
//    get() = MinecraftServer.getInstance()assad