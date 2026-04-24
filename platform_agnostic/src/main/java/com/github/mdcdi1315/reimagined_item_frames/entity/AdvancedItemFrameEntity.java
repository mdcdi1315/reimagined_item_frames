package com.github.mdcdi1315.reimagined_item_frames.entity;

import com.github.mdcdi1315.reimagined_item_frames.items.Items;
import com.github.mdcdi1315.reimagined_item_frames.menu.AdvancedItemFrameMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;

public class AdvancedItemFrameEntity
    extends AbstractAdvancedBaseItemFrameEntity
{
    public AdvancedItemFrameEntity(EntityType<? extends AdvancedItemFrameEntity> entityType, Level level) { super(entityType, level); }

    public AdvancedItemFrameEntity(Level level, BlockPos position, Direction direction) { super(Entities.ADVANCED_ITEM_FRAME_ENTITY, level, position, direction); }

    protected AdvancedItemFrameEntity(EntityType<? extends AdvancedItemFrameEntity> entityType, Level level, BlockPos pos, Direction direction) { super(entityType, level, pos, direction); }

    @Override
    protected ItemStack getFrameItemStack() { return new ItemStack(Items.ADVANCED_ITEM_FRAME); }

    @Override
    public AdvancedItemFrameMenu createMenu(int i, Inventory inventory, Player player) { return new AdvancedItemFrameMenu(i, inventory, this); }
}
