package io.kenza.support.utils

import dev.architectury.registry.registries.RegistrySupplier
import io.kenza.support.utils.reg.DEFAULT_BLOCK_ITEM_SETTING
import io.kenza.support.utils.reg.Ref.BLOCKS
import io.kenza.support.utils.reg.Ref.BLOCK_ENTITY_TYPES
import io.kenza.support.utils.reg.Ref.CLIENT_ENTITY_REGISTERER_MAP
import io.kenza.support.utils.reg.Ref.CONFIGURED_FEATURES
import io.kenza.support.utils.reg.Ref.DATAGEN_SIMPLE_BLOCKS
import io.kenza.support.utils.reg.Ref.DATAGEN_SIMPLE_ITEMS
import io.kenza.support.utils.reg.Ref.DATAGEN_STONE_MATERIALS
import io.kenza.support.utils.reg.Ref.DATAGEN_WOOD_MATERIALS
import io.kenza.support.utils.reg.Ref.ITEMS
import io.kenza.support.utils.reg.Ref.POINT_OF_INTEREST_TYPES
import io.kenza.support.utils.reg.Ref.RECIPE_SERIALIZERS
import io.kenza.support.utils.reg.Ref.RECIPE_TYPES
import io.kenza.support.utils.reg.Ref.SCREEN_HANDLERS
import io.kenza.support.utils.reg.Ref.SOUNDS_EVENTS
import io.kenza.support.utils.reg.Ref.VILLAGER_PROFESSIONS
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.client.render.block.entity.BlockEntityRenderer
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.nbt.NbtCompound
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.RecipeType
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier
import net.minecraft.util.math.ChunkPos
import net.minecraft.village.VillagerProfession
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.poi.PointOfInterestType
import java.util.function.Supplier


fun Identifier.getRegSoundEvent(): SoundEvent {
    return (SOUNDS_EVENTS.first {
        this == it.id
    } as? RegistrySupplier<SoundEvent>)!!.get()
}


fun Identifier.getRegSupSoundEvent(): RegistrySupplier<SoundEvent> {
    return (SOUNDS_EVENTS.first {
        this == it.id
    })!!
}

fun <T : BlockEntity> Identifier.getRegBlockEntityType(): BlockEntityType<T> {
    return (BLOCK_ENTITY_TYPES.first {
        this == it.id
    } as? RegistrySupplier<BlockEntityType<T>>)!!.get()
}


fun <T : Block> Identifier.getRegBlock(): RegistrySupplier<T>? {
    return BLOCKS.first {
        this == it.id
    } as? RegistrySupplier<T>
}

fun <T : Item> Identifier.getRegItem(): T {
    return (ITEMS.first {
        this == it.id
    } as? RegistrySupplier<T>)!!.get()
}

fun  Identifier.getRegSupItem(): RegistrySupplier<Item> {
    return ITEMS.first {
        this == it.id
    }
}

fun Identifier.getSupConfiguredFeature(): RegistrySupplier<ConfiguredFeature<*, *>>? {
    return (CONFIGURED_FEATURES.first {
        this == it.id
    })
}

fun Identifier.getConfiguredFeature(): ConfiguredFeature<*, *>? {
    return (CONFIGURED_FEATURES.first {
        this == it.id
    } as? RegistrySupplier<ConfiguredFeature<*,*>>)!!.get()

}



fun <T : Block> String.getRegBlock(): RegistrySupplier<T>? {
    return BLOCKS.first {
        identifier(this) == it.id
    } as? RegistrySupplier<T>
}


fun String.getRegItem(): RegistrySupplier<Item>? {
    return ITEMS.first {
        identifier(this) == it.id
    } as? RegistrySupplier<Item>
}


fun Identifier.blockEntityTypeRender(supplier: Supplier<BlockEntityRenderer<out BlockEntity>>) {
    CLIENT_ENTITY_REGISTERER_MAP.put(this, supplier)
}

fun Identifier.blockAndItem(block: Supplier<Block>): RegistrySupplier<Block> {
    item {
        BlockItem(this.getRegBlock<Block>()!!.get(), DEFAULT_BLOCK_ITEM_SETTING)
    }
    return block(block)
}


fun Identifier.block(supplier: Supplier<Block>): RegistrySupplier<Block> {
    return BLOCKS.register(this, supplier)
}

fun Identifier.createBlock(func: () -> AbstractBlock.Settings): Supplier<Block> {
    return Supplier {
        Block(func.invoke())
    }
}

//fun Identifier.fluid(fluid: Fluid): Identifier {
//    Registry.register(Registry.FLUID, this, fluid)
//    return this
//}

fun Identifier.blockItem(): RegistrySupplier<Item> {
    return item {
        return@item getRegBlock<Block>()!!.get().asItem()
    }
}

fun Identifier.itemDataGen(parent: net.minecraft.data.client.Model? = null) {
    DATAGEN_SIMPLE_ITEMS.put(this, parent)
}

fun Identifier.blockDataGen() {
    DATAGEN_SIMPLE_BLOCKS.put(this, Any())
}

fun Identifier.stoneMaterialDataGen() {
    DATAGEN_STONE_MATERIALS.add(this)
}

fun Identifier.woodMaterialDataGen() {
    DATAGEN_WOOD_MATERIALS.add(this)
}

fun Identifier.item(supplier: Supplier<Item>): RegistrySupplier<Item> {
    return ITEMS.register(this, supplier)
}

fun Identifier.soundEvent(supplier: Supplier<SoundEvent>): RegistrySupplier<SoundEvent> {
    return SOUNDS_EVENTS.register(this, supplier)
}

fun Identifier.soundEvent(): RegistrySupplier<SoundEvent> = soundEvent { SoundEvent(this) }
//fun Identifier.soundEvent(): RegistrySupplier<SoundEvent> = soundEvent{SoundEvent.of(this)}

fun Identifier.screenHandler(supplier: Supplier<ScreenHandlerType<*>>): RegistrySupplier<ScreenHandlerType<*>> {
    return SCREEN_HANDLERS.register(this, supplier)
}

fun Identifier.poiType(supplier: Supplier<PointOfInterestType>): RegistrySupplier<PointOfInterestType> {
    return POINT_OF_INTEREST_TYPES.register(this, supplier)
}

fun Identifier.profession(supplier: Supplier<VillagerProfession>): RegistrySupplier<VillagerProfession> {
    return VILLAGER_PROFESSIONS.register(this, supplier)
}


fun Identifier.blockEntityType(supplier: Supplier<BlockEntityType<*>>): RegistrySupplier<BlockEntityType<*>> {
    return BLOCK_ENTITY_TYPES.register(this, supplier)
}

fun Identifier.recipeSerializer(supplier: Supplier<RecipeSerializer<*>>): RegistrySupplier<RecipeSerializer<*>>? {
    return RECIPE_SERIALIZERS.register(this, supplier)
}


fun Identifier.recipeType(supplier: Supplier<RecipeType<*>>): RegistrySupplier<RecipeType<*>>? {
    return RECIPE_TYPES.register(this, supplier)
}


fun Identifier.configuredFeature(supplier: Supplier<ConfiguredFeature<*, *>>): RegistrySupplier<ConfiguredFeature<*, *>>? {
    return CONFIGURED_FEATURES.register(this, supplier)
}


val EMPTY_ITEM = { Item(Item.Settings()) }

//private fun Block.defaultBlockItem(): BlockItem {
//    return BlockItem(this, Item.Settings().group(Ref.MOD_TAB))
//}


fun ChunkPos.toNbt() = NbtCompound().also {
    it.putInt("x", x)
    it.putInt("z", z)
}

fun getChunkPos(nbt: NbtCompound) = ChunkPos(nbt.getInt("x"), nbt.getInt("z"))



