package io.kenza.support.base

import dev.architectury.event.events.client.ClientTickEvent
import io.kenza.support.utils.reg.Ref

open class BaseClientInitializer {



    open fun onInitialize(){

        ClientTickEvent.CLIENT_POST.register { instance ->
//            if(!mapping.isPressed){
//                isWasActivated = false
//            }

            Ref.KEY_BINDINGS_MAP.map { (key, receiver) ->
                receiver.onTick()
            }


        }


    }
}