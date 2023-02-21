package io.kenza.support.utils.reg

import io.kenza.support.utils.reg.Ref.MOD_TAB
import net.minecraft.item.Item

val DEFAULT_BLOCK_ITEM_SETTING: Item.Settings
    get() = Item.Settings().group(MOD_TAB)

val DEFAULT_SINGLE_ITEM_SETTING: Item.Settings
    get() = Item.Settings()
        .maxCount(1)
        .group(MOD_TAB)


