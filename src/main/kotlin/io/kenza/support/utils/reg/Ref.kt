package io.kenza.support.utils.reg

import dev.architectury.registry.registries.DeferredRegister
import io.kenza.support.utils.keys.ActionBinder
import net.minecraft.block.Block
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.render.block.entity.BlockEntityRenderer
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.RecipeType
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier
import net.minecraft.village.VillagerProfession
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.poi.PointOfInterestType
import java.util.function.Supplier


object Ref {
    //    lateinit var MOD_TAB: ItemGroup
    var _MOD_ID = ""

    lateinit var MOD_TAB: ItemGroup

    var MOD_ID = ""
        get() = _MOD_ID

    lateinit var ITEMS: DeferredRegister<Item>
    lateinit var SOUNDS_EVENTS: DeferredRegister<SoundEvent>
    lateinit var BLOCKS: DeferredRegister<Block>
    lateinit var BLOCK_ENTITY_TYPES: DeferredRegister<BlockEntityType<*>>
    lateinit var SCREEN_HANDLERS: DeferredRegister<ScreenHandlerType<*>>
    lateinit var POINT_OF_INTEREST_TYPES: DeferredRegister<PointOfInterestType>
    lateinit var VILLAGER_PROFESSIONS: DeferredRegister<VillagerProfession>
    lateinit var RECIPE_SERIALIZERS: DeferredRegister<RecipeSerializer<*>>
    lateinit var RECIPE_TYPES: DeferredRegister<RecipeType<*>>
    lateinit var CONFIGURED_FEATURES: DeferredRegister<ConfiguredFeature<*, *>>


    val KEY_BINDINGS_MAP = HashMap<KeyBinding, ActionBinder>()

    var CLIENT_ENTITY_REGISTERER_MAP =
        HashMap<Identifier, Supplier<BlockEntityRenderer<out BlockEntity>>>()

    var DATAGEN_SIMPLE_ITEMS = HashMap<Identifier, net.minecraft.data.client.Model?>()
    var DATAGEN_SIMPLE_BLOCKS = HashMap<Identifier, Any>()


    val DATAGEN_STONE_MATERIALS = ArrayList<Identifier>()
    val DATAGEN_WOOD_MATERIALS = ArrayList<Identifier>()

    val DATA_GEN_WOOD_MATERIAL_NAMES
        get() = Ref.DATAGEN_WOOD_MATERIALS.map {
            it.path
        }
    val DATA_GEN_STONES_MATERIAL_NAMES
        get() = Ref.DATAGEN_STONE_MATERIALS.map {
            it.path
        }

}
