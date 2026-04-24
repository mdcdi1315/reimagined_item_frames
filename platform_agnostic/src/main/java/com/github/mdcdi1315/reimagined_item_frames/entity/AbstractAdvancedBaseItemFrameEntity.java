package com.github.mdcdi1315.reimagined_item_frames.entity;

import com.github.mdcdi1315.basemodslib.world.NBTUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.EntityType;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;

import java.util.Optional;

public abstract class AbstractAdvancedBaseItemFrameEntity
    extends AbstractBaseItemFrameEntity
{
    public static final EntityDataAccessor<Byte> DESIRED_LIGHT = SynchedEntityData.defineId(AbstractAdvancedBaseItemFrameEntity.class, EntityDataSerializers.BYTE);
    public static final EntityDataAccessor<Byte> ITEM_DISPLAY_SCALE = SynchedEntityData.defineId(AbstractAdvancedBaseItemFrameEntity.class, EntityDataSerializers.BYTE);

    protected AbstractAdvancedBaseItemFrameEntity(EntityType<? extends ItemFrame> entityType, Level level) {
        super(entityType, level);
    }

    protected AbstractAdvancedBaseItemFrameEntity(EntityType<? extends ItemFrame> entityType, Level level, BlockPos pos, Direction direction) {
        super(entityType, level, pos, direction);
    }

    @Override
    public void WriteScreenOpeningData(ServerPlayer player, FriendlyByteBuf buf)
    {
        super.WriteScreenOpeningData(player, buf);
        buf.writeByte(GetItemDisplayScale());
        buf.writeByte(GetDesiredLightLevel());
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DESIRED_LIGHT, (byte)0);
        builder.define(ITEM_DISPLAY_SCALE, (byte)50);
    }

    public byte GetDesiredLightLevel() { return getEntityData().get(DESIRED_LIGHT); }

    public void SetDesiredLightLevel(byte value) { getEntityData().set(DESIRED_LIGHT, value); }

    public byte GetItemDisplayScale() { return getEntityData().get(ITEM_DISPLAY_SCALE); }

    public void SetItemDisplayScale(byte value) { getEntityData().set(ITEM_DISPLAY_SCALE, value); }

    public void readAdditionalSaveData(CompoundTag compound)
    {
        super.readAdditionalSaveData(compound);
        Optional<Byte> o = NBTUtils.GetByte(compound, "light_level");
        this.SetDesiredLightLevel(o.isPresent() ? o.get() : 0);
        o = NBTUtils.GetByte(compound, "item_display_scale");
        this.SetItemDisplayScale(o.isPresent() ? o.get() : 50);
    }

    public void addAdditionalSaveData(CompoundTag compound)
    {
        super.addAdditionalSaveData(compound);
        compound.putByte("light_level", this.GetDesiredLightLevel());
        compound.putByte("item_display_scale", this.GetItemDisplayScale());
    }
}
