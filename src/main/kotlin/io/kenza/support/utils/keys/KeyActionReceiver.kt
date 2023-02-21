package io.kenza.support.utils.keys

import net.minecraft.client.option.KeyBinding

fun interface  KeyActionReceiver {
    abstract  fun onKeyAction(action: KeyAction)
}



val keysWadDownedMap = HashMap<KeyBinding, Boolean>()

fun KeyBinding.getAction(): KeyAction {
    return if (isPressed && !isWasDowned()) {
        keysWadDownedMap.put(this, true)
        KeyAction.ActionDown(this)
    } else if (isPressed && wasPressed()) {
        KeyAction.ActionHold(this)
    } else if (!isPressed && isWasDowned()) {
        keysWadDownedMap.put(this, false)
        KeyAction.ActionUp(this)
    } else {
        KeyAction.ActionRelease(this)
    }
}

private fun KeyBinding.isWasDowned() : Boolean{
    return keysWadDownedMap.get(this) ?: false
}


sealed class KeyAction(open val bind: KeyBinding) {
    data class ActionDown(override val bind: KeyBinding) : KeyAction(bind)
    data class ActionHold(override val bind: KeyBinding) : KeyAction(bind)
    data class ActionUp(override val bind: KeyBinding) : KeyAction(bind)
    data class ActionRelease(override val bind: KeyBinding) : KeyAction(bind)
}