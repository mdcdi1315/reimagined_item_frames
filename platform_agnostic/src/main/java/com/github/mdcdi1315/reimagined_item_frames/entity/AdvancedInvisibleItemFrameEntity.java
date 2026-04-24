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
import net.minecraft.world.inventory.AbstractContainerMenu;

public class AdvancedInvisibleItemFrameEntity
    extends AbstractAdvancedBaseItemFrameEntity
{
    public AdvancedInvisibleItemFrameEntity(EntityType<? extends AdvancedInvisibleItemFrameEntity> entityType, Level level) { super(entityType, level); }

    public AdvancedInvisibleItemFrameEntity(Level level, BlockPos pos, Direction facingDirection) { super(Entities.ADVANCED_INVISIBLE_ITEM_FRAME_ENTITY, level, pos, facingDirection); }

    @Override
    protected ItemStack getFrameItemStack() { return new ItemStack(Items.ADVANCED_INVISIBLE_ITEM_FRAME); }

    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) { return new AdvancedItemFrameMenu(i, inventory, this); }
}
