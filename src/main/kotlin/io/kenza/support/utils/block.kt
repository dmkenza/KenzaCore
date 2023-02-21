package io.kenza.support.utils

import net.minecraft.block.Block
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d


fun Block.id()
        = this.`arch$registryName`()

fun BlockPos.toVec3d() = Vec3d(x.toDouble(), y.toDouble(), z.toDouble())

fun Vec3d.center(): Vec3d = Vec3d(MathHelper.floor(x) + 0.5, MathHelper.floor(y) + 0.5, MathHelper.floor(z) + 0.5)
