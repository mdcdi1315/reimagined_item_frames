package com.github.mdcdi1315.reimagined_item_frames.entity;

import com.github.mdcdi1315.DotNetLayer.System.Diagnostics.CodeAnalysis.NotNull;
import com.github.mdcdi1315.DotNetLayer.System.Diagnostics.CodeAnalysis.MaybeNull;

import com.github.mdcdi1315.basemodslib.menu.MenuProviderEx;

import com.github.mdcdi1315.reimagined_item_frames.network.Networking;
import com.github.mdcdi1315.reimagined_item_frames.mixin.ItemFrameDataAccessor;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.inventory.AbstractContainerMenu;

// Provides the essential tools to program a new item frame entity.
// From this abstraction all custom item frame entities are inheriting from.
public abstract class AbstractBaseItemFrameEntity
    extends ItemFrame
    implements MenuProviderEx
{
    protected AbstractBaseItemFrameEntity(EntityType<? extends ItemFrame> entityType, Level level) { super(entityType, level); }

    protected AbstractBaseItemFrameEntity(EntityType<? extends ItemFrame> entityType, Level level, BlockPos pos, Direction direction) { super(entityType, level, pos, direction); }

    @NotNull
    @Override
    protected abstract ItemStack getFrameItemStack();

    @Override
    @MaybeNull
    public abstract AbstractContainerMenu createMenu(int i, Inventory inventory, Player player);

    public void setRotation(int rotation) { SetRotation(rotation, true); }

    @Override
    public final InteractionResult interact(Player player, InteractionHand hand, Vec3 location)
    {
        if (((ItemFrameDataAccessor)this).GetFixed()) {
            return InteractionResult.PASS;
        } else {
            Networking.MANAGER.OpenMenu(player, this);
            return InteractionResult.SUCCESS;
        }
    }

    protected final void SetRotation(int rotation, boolean updateNeighbours)
    {
        this.getEntityData().set(ItemFrameDataAccessor.GetRotationData(), rotation);
        if (updateNeighbours && this.pos != null) {
            this.level().updateNeighbourForOutputSignal(this.pos, Blocks.AIR);
        }
    }

    @Override
    public void WriteScreenOpeningData(ServerPlayer player, FriendlyByteBuf buf)
    {
        buf.writeIntLE(getId());
        buf.writeShortLE(getRotation());
    }
}
