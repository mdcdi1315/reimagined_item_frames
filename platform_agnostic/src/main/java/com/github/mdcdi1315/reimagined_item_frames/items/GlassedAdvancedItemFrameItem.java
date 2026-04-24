package com.github.mdcdi1315.reimagined_item_frames.items;

import com.github.mdcdi1315.reimagined_item_frames.entity.Entities;
import com.github.mdcdi1315.reimagined_item_frames.entity.GlassedAdvancedItemFrameEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

public final class GlassedAdvancedItemFrameItem
    extends AbstractDerivedItemFrameItem
{
    public GlassedAdvancedItemFrameItem(Properties properties) { super(Entities.GLASSED_ADVANCED_ITEM_FRAME_ENTITY, properties); }

    @Override
    protected GlassedAdvancedItemFrameEntity ConstructEntityInstance(Level level, BlockPos position, Direction direction) { return new GlassedAdvancedItemFrameEntity(level, position, direction); }
}
