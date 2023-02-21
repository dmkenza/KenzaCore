package io.kenza.support.utils

import com.sun.source.tree.Scope
import dev.architectury.event.CompoundEventResult
import dev.architectury.event.EventResult
import dev.architectury.event.events.client.ClientGuiEvent
import dev.architectury.hooks.client.screen.ScreenAccess
import dev.architectury.platform.Platform.isDevelopmentEnvironment
import dev.architectury.platform.Platform.isFabric
import kotlinx.coroutines.*
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.screen.TitleScreen
import net.minecraft.client.gui.screen.world.SelectWorldScreen
import net.minecraft.client.sound.PositionedSoundInstance
import net.minecraft.server.integrated.IntegratedServerLoader
import net.minecraft.sound.SoundEvents
import org.lwjgl.system.Platform.Architecture
import kotlin.coroutines.CoroutineContext


var job: Job? = null
private var initTitleCounter2 = 0
private var afterInitTitleCounter2 = 0

//

fun openLastWorldOnInit() {
//    ClientLifecycleEvents.CLIENT_STARTED

    if (!isDevelopmentEnvironment()) {
        return
    }



    ClientGuiEvent.INIT_POST.register(
        ClientGuiEvent.ScreenInitPost { screen, access ->
            initTitleCounter2++
            job?.cancel()
//            println("kenzaa2 $initTitleCounter2")
//            if (initTitleCounter2 == 4) {
//                openStartWorld(screen, access)
//            }


            if (afterInitTitleCounter2 > 0){
                if (initTitleCounter2 == afterInitTitleCounter2 + 4) {
                    MinecraftClient.getInstance()
                        .soundManager
                        .play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 2.0f))
                }
            }


            if (afterInitTitleCounter2 > 0 || initTitleCounter2 < 8) {
                CompoundEventResult.interruptDefault(screen)
                return@ScreenInitPost
            }

            job = GlobalScope.launch {
                delay(1000)
                mc.execute {
                    openStartWorld(screen, access)
                }
                afterInitTitleCounter2 = initTitleCounter2
            }


            CompoundEventResult.interruptDefault(screen)
        }
    )


//    EVENTS.register(ScreenInitCallback { screen: Screen?, buttons: ScreenInitCallback.ButtonList? ->
//        onScreenInit(
//            screen,
//            buttons
//        )
//    })
}


fun openStartWorld(screen: Screen, access: ScreenAccess) {

    if (screen is TitleScreen) {
        //open world after start minecraft
        val client = MinecraftClient.getInstance()
        client.levelStorage.levelList.firstOrNull()?.let { level ->
            client.soundManager.play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 2.0f))
            if (client.levelStorage.levelExists(level.rootPath)) {
                client.setScreenAndRender(SelectWorldScreen(screen))
                IntegratedServerLoader(client, client.levelStorage).start(
                    screen, level.rootPath
                )
//                val session: Session = this.createSession(levelName)
//                client.startIntegratedServer(
//                    level.rootPath,
//                    level.sessionLockPath
//                )
            }
        }
    }
}

