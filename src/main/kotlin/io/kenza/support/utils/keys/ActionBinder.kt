package io.kenza.support.utils.keys

import net.minecraft.client.option.KeyBinding

sealed class ActionBinder(){

//    data class ActionViewBinder (val mapping: KeyBinding, val view: View) : ActionBinder() {
//
//        override fun onTick() {
//            val action = mapping.getAction()
////            println("kenzaa " + action)
//
//            when(action){
//                is KeyAction.ActionDown -> view.onCreate()
//                is KeyAction.ActionHold -> {}//view.onTick()
//                is KeyAction.ActionRelease -> {}//view.onTick()
//                is KeyAction.ActionUp ->  {}//view.onDestroy()
//            }
//        }
//    }

    data class ActionBinderHolder (val mapping: KeyBinding, val keyActionReceiver: KeyActionReceiver) : ActionBinder() {

        override fun onTick() {
            val action = mapping.getAction()
            keyActionReceiver.onKeyAction(action)
        }
    }


    abstract fun onTick()
}
