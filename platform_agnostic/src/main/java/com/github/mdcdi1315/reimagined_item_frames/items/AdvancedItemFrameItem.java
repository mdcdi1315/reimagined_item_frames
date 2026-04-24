package com.github.mdcdi1315.reimagined_item_frames.items;

import com.github.mdcdi1315.reimagined_item_frames.entity.Entities;
import com.github.mdcdi1315.reimagined_item_frames.entity.AdvancedItemFrameEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

public final class AdvancedItemFrameItem
    extends AbstractDerivedItemFrameItem
{
    public AdvancedItemFrameItem(Properties properties) { super(Entities.ADVANCED_ITEM_FRAME_ENTITY, properties); }

    @Override
    protected AdvancedItemFrameEntity ConstructEntityInstance(Level level, BlockPos position, Direction direction) { return new AdvancedItemFrameEntity(level, position, direction); }
}
