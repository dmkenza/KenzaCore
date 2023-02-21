package io.kenza.support.base

import com.google.common.base.Suppliers
import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.Registries
import io.kenza.support.utils.reg.Ref
import net.minecraft.item.Item
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.sound.SoundEvent
import net.minecraft.util.registry.Registry
import java.util.function.Supplier

open class BaseInitializer {


    val REGISTRIES: Supplier<Registries> = Suppliers.memoize {
        Registries.get(Ref.MOD_ID)
    }

    fun createRegisters() {

        Ref.ITEMS = DeferredRegister.create<Item>(Ref.MOD_ID, Registry.ITEM_KEY)
        Ref.BLOCKS = DeferredRegister.create(Ref.MOD_ID, Registry.BLOCK_KEY)
        Ref.BLOCK_ENTITY_TYPES = DeferredRegister.create(Ref.MOD_ID, Registry.BLOCK_ENTITY_TYPE_KEY)

        Ref.POINT_OF_INTEREST_TYPES = DeferredRegister.create(Ref.MOD_ID, Registry.POINT_OF_INTEREST_TYPE_KEY)
        Ref.VILLAGER_PROFESSIONS = DeferredRegister.create(Ref.MOD_ID, Registry.VILLAGER_PROFESSION_KEY)

        Ref.SOUNDS_EVENTS =
            DeferredRegister.create<SoundEvent>(Ref.MOD_ID, Registry.SOUND_EVENT_KEY)
        Ref.SCREEN_HANDLERS = DeferredRegister.create<ScreenHandlerType<*>>(Ref.MOD_ID, Registry.MENU_KEY)

        Ref.RECIPE_SERIALIZERS = DeferredRegister.create(Ref.MOD_ID, Registry.RECIPE_SERIALIZER_KEY)
        Ref.RECIPE_TYPES = DeferredRegister.create(Ref.MOD_ID, Registry.RECIPE_TYPE_KEY)

        Ref.CONFIGURED_FEATURES = DeferredRegister.create(Ref.MOD_ID, Registry.CONFIGURED_FEATURE_KEY)
    }

    open fun onInitialize() {
        finishInit()
    }


    private fun finishInit() {
        Ref.SOUNDS_EVENTS.register()
        Ref.BLOCKS.register()
        Ref.ITEMS.register()
        Ref.BLOCK_ENTITY_TYPES.register()
        Ref.SCREEN_HANDLERS.register()

        Ref.POINT_OF_INTEREST_TYPES.register()
        Ref.VILLAGER_PROFESSIONS.register()
        Ref.RECIPE_SERIALIZERS.register()
        Ref.RECIPE_TYPES.register()

        Ref.CONFIGURED_FEATURES.register()
    }
}