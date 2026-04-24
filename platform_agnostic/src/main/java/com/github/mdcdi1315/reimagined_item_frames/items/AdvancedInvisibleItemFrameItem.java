package com.github.mdcdi1315.reimagined_item_frames.items;

import com.github.mdcdi1315.reimagined_item_frames.entity.Entities;
import com.github.mdcdi1315.reimagined_item_frames.entity.AdvancedInvisibleItemFrameEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

public final class AdvancedInvisibleItemFrameItem
    extends AbstractDerivedItemFrameItem
{
    public AdvancedInvisibleItemFrameItem(Properties properties) { super(Entities.ADVANCED_INVISIBLE_ITEM_FRAME_ENTITY, properties); }

    @Override
    protected AdvancedInvisibleItemFrameEntity ConstructEntityInstance(Level level, BlockPos position, Direction direction) { return new AdvancedInvisibleItemFrameEntity(level, position, direction); }
}
