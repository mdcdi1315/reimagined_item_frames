package com.github.mdcdi1315.reimagined_item_frames.items;

import com.github.mdcdi1315.reimagined_item_frames.entity.Entities;
import com.github.mdcdi1315.reimagined_item_frames.entity.SimpleInvisibleItemFrameEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

public final class SimpleInvisibleItemFrameItem
    extends AbstractDerivedItemFrameItem
{
    public SimpleInvisibleItemFrameItem(Properties properties) { super(Entities.SIMPLE_INVISIBLE_ITEM_FRAME_ENTITY, properties); }

    @Override
    protected SimpleInvisibleItemFrameEntity ConstructEntityInstance(Level level, BlockPos position, Direction direction) { return new SimpleInvisibleItemFrameEntity(level, position, direction); }
}
