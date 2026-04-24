package com.github.mdcdi1315.reimagined_item_frames.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.EntityType;

public class GlassedAdvancedItemFrameEntity
    extends AdvancedItemFrameEntity
{
    public GlassedAdvancedItemFrameEntity(EntityType<? extends GlassedAdvancedItemFrameEntity> entityType, Level level) { super(entityType, level); }

    public GlassedAdvancedItemFrameEntity(Level level, BlockPos pos, Direction direction) { super(Entities.GLASSED_ADVANCED_ITEM_FRAME_ENTITY, level, pos, direction); }

    protected GlassedAdvancedItemFrameEntity(EntityType<? extends GlassedAdvancedItemFrameEntity> entityType, Level level, BlockPos pos, Direction direction) { super(entityType, level, pos, direction); }

    @Override
    protected ItemStack getFrameItemStack() { return ItemStack.EMPTY; } // This is left strategically on purpose to be an empty stack since it is a fragile advanced item frame.

    @Override
    public SoundEvent getBreakSound() { return SoundEvents.GLASS_BREAK; }
}