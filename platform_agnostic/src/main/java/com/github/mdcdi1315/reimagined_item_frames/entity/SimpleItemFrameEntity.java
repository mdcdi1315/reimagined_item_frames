package com.github.mdcdi1315.reimagined_item_frames.entity;

import com.github.mdcdi1315.reimagined_item_frames.items.Items;
import com.github.mdcdi1315.reimagined_item_frames.menu.BasicItemFrameMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class SimpleItemFrameEntity
    extends AbstractBaseItemFrameEntity
{
    public SimpleItemFrameEntity(EntityType<? extends SimpleItemFrameEntity> entityType, Level level) { super(entityType, level); }

    public SimpleItemFrameEntity(Level level, BlockPos position, Direction direction) { super(Entities.SIMPLE_ITEM_FRAME_ENTITY, level, position, direction); }

    protected SimpleItemFrameEntity(EntityType<? extends SimpleItemFrameEntity> entityType, Level level, BlockPos pos, Direction direction) { super(entityType, level, pos, direction); }

    @Override
    protected ItemStack getFrameItemStack() { return new ItemStack(Items.SIMPLE_ITEM_FRAME); }

    @Override
    public AbstractContainerMenu createMenu(int container_id, Inventory inventory, Player player) { return new BasicItemFrameMenu(container_id, inventory,this); }
}
