package io.kenza.support.utils

import io.kenza.support.utils.identifier
import io.kenza.support.utils.reg.Ref
import net.minecraft.block.Blocks
import net.minecraft.util.Identifier

fun identifier(id: String): Identifier {
    return if(id.contains(":")){
        Identifier(id.split(":").get(0), id.split(":").get(1))
    }else{
        Identifier(Ref.MOD_ID, id)
    }
}



fun Identifier.isEmpty(): Boolean {
    return  this == identifier("")
}

fun Identifier.isAir(): Boolean = this == Blocks.AIR.id()
