package com.github.mdcdi1315.reimagined_item_frames.entity;

import com.github.mdcdi1315.DotNetLayer.System.Diagnostics.CodeAnalysis.MaybeNull;

import com.github.mdcdi1315.basemodslib.menu.MenuProviderEx;

import com.github.mdcdi1315.reimagined_item_frames.ServerModInstance;
import com.github.mdcdi1315.reimagined_item_frames.network.Networking;
import com.github.mdcdi1315.reimagined_item_frames.mixin.ItemFrameDataAccessor;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;

// Provides the essential tools to program a new item frame entity.
// From this abstraction all custom item frame entities are inheriting from.
public abstract class AbstractBaseItemFrameEntity
    extends ItemFrame
    implements MenuProviderEx
{
    protected AbstractBaseItemFrameEntity(EntityType<? extends ItemFrame> entityType, Level level) { super(entityType, level); }

    protected AbstractBaseItemFrameEntity(EntityType<? extends ItemFrame> entityType, Level level, BlockPos pos, Direction direction) { super(entityType, level, pos, direction); }

    @Override
    protected abstract ItemStack getFrameItemStack();

    @Override
    @MaybeNull
    public abstract AbstractContainerMenu createMenu(int i, Inventory inventory, Player player);

    public void setRotation(int rotation) { SetRotation(rotation, true); }

    @Override
    public final InteractionResult interact(Player player, InteractionHand hand)
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

    protected final void removeFramedMap(ItemStack stack)
    {
        MapId mapid = this.getFramedMapId(stack);
        if (mapid != null) {
            MapItemSavedData mapitemsaveddata = MapItem.getSavedData(mapid, this.level());
            if (mapitemsaveddata != null) {
                mapitemsaveddata.removedFromFrame(this.pos, this.getId());
                mapitemsaveddata.setDirty(true);
            }
        }

        stack.setEntityRepresentation(null);
    }

    @Override
    public void WriteScreenOpeningData(ServerPlayer player, FriendlyByteBuf buf)
    {
        buf.writeIntLE(getId());
        buf.writeShortLE(getRotation());
    }

    public void addAdditionalSaveData(CompoundTag compound)
    {
        BlockPos blockpos = this.getPos();
        compound.putInt("TileX", blockpos.getX());
        compound.putInt("TileY", blockpos.getY());
        compound.putInt("TileZ", blockpos.getZ());

        if (!this.getItem().isEmpty()) {
            compound.put("item", this.getItem().save(this.registryAccess()));
            compound.putShort("item_rot", (short) this.getRotation());
            compound.putFloat("item_dc", ((ItemFrameDataAccessor)this).GetDropChance());
        }

        compound.putBoolean("invisible", this.isInvisible());
        compound.putByte("facing", (byte)this.direction.get3DDataValue());
        compound.putBoolean("fixed", ((ItemFrameDataAccessor)this).GetFixed());
    }

    public void readAdditionalSaveData(CompoundTag compound)
    {
        BlockPos blockpos = new BlockPos(compound.getInt("TileX"), compound.getInt("TileY"), compound.getInt("TileZ"));
        if (!blockpos.closerThan(this.blockPosition(), 16.0)) {
            ServerModInstance.LOGGER.error("Block-attached entity at invalid position: {}", blockpos);
        } else {
            this.pos = blockpos;
        }

        ItemStack itemstack;
        if (compound.contains("item", 10)) {
            CompoundTag compoundtag = compound.getCompound("item");
            itemstack = ItemStack.parse(this.registryAccess(), compoundtag).orElse(ItemStack.EMPTY);
        } else {
            itemstack = ItemStack.EMPTY;
        }

        ItemStack itemstack1 = this.getItem();
        if (!itemstack1.isEmpty() && !ItemStack.matches(itemstack, itemstack1)) {
            this.removeFramedMap(itemstack1);
        }

        this.setItem(itemstack, false);
        if (!itemstack.isEmpty()) {
            this.SetRotation(compound.getShort("item_rot"), false);
            if (compound.contains("item_dc", 99)) {
                ((ItemFrameDataAccessor)this).SetDropChance(compound.getFloat("item_dc"));
            }
        }

        this.setDirection(Direction.from3DDataValue(compound.getByte("facing")));
        this.setInvisible(compound.getBoolean("invisible"));
        ((ItemFrameDataAccessor)this).SetFixed(compound.getBoolean("fixed"));
    }
}
