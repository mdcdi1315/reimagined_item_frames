package com.github.mdcdi1315.reimagined_item_frames.items;

import com.github.mdcdi1315.reimagined_item_frames.entity.Entities;
import com.github.mdcdi1315.reimagined_item_frames.entity.SimpleItemFrameEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

public final class SimpleItemFrameItem
    extends AbstractDerivedItemFrameItem
{
    public SimpleItemFrameItem(Properties properties) { super(Entities.SIMPLE_ITEM_FRAME_ENTITY, properties); }

    @Override
    protected SimpleItemFrameEntity ConstructEntityInstance(Level level, BlockPos position, Direction direction) { return new SimpleItemFrameEntity(level, position, direction); }
}
