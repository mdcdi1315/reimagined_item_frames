package com.github.mdcdi1315.reimagined_item_frames.entity;

import com.github.mdcdi1315.DotNetLayer.System.Diagnostics.CodeAnalysis.MaybeNull;

import com.github.mdcdi1315.reimagined_item_frames.items.Items;
import com.github.mdcdi1315.reimagined_item_frames.menu.BasicItemFrameMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class SimpleInvisibleItemFrameEntity
    extends AbstractBaseItemFrameEntity
{
    public SimpleInvisibleItemFrameEntity(EntityType<? extends SimpleInvisibleItemFrameEntity> entityType, Level level) { super(entityType, level); }

    public SimpleInvisibleItemFrameEntity(Level level, BlockPos pos, Direction facingDirection) { super(Entities.SIMPLE_INVISIBLE_ITEM_FRAME_ENTITY, level, pos, facingDirection); }

    protected SimpleInvisibleItemFrameEntity(EntityType<? extends SimpleInvisibleItemFrameEntity> entityType, Level level, BlockPos pos, Direction direction) { super(entityType, level, pos, direction); }

    @Override
    protected ItemStack getFrameItemStack() { return new ItemStack(Items.SIMPLE_INVISIBLE_ITEM_FRAME); }

    @Override
    @MaybeNull
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) { return new BasicItemFrameMenu(i, inventory, this); }
}
