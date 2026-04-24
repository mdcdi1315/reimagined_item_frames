package com.github.mdcdi1315.reimagined_item_frames.network;

import com.github.mdcdi1315.basemodslib.registries.RegistryUtils;

import com.github.mdcdi1315.reimagined_item_frames.ServerModInstance;
import com.github.mdcdi1315.reimagined_item_frames.entity.AbstractAdvancedBaseItemFrameEntity;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public final class UpdateAdvancedItemFrameDataPacket
    implements CustomPacketPayload
{
    public static final Type<UpdateAdvancedItemFrameDataPacket> TYPE = new Type<>(RegistryUtils.ConstructResourceLocation(ServerModInstance.MOD_ID, "update_advanced_item_frame_data"));

    public int EntityID;
    public short Rotation;
    public byte ItemScale;
    public byte LightLevel;

    private UpdateAdvancedItemFrameDataPacket() {}

    public UpdateAdvancedItemFrameDataPacket(int eid, short rotation, byte scale, byte lightLevel)
    {
        this.EntityID = eid;
        this.ItemScale = scale;
        this.Rotation = rotation;
        this.LightLevel = lightLevel;
    }

    public static void HandlePacket(ServerPlayer sp, UpdateAdvancedItemFrameDataPacket packet)
    {
        if (sp.level().getEntity(packet.EntityID) instanceof AbstractAdvancedBaseItemFrameEntity b)
        {
            b.setRotation(packet.Rotation);
            b.SetItemDisplayScale(packet.ItemScale);
            b.SetDesiredLightLevel(packet.LightLevel);
        }
    }

    @Override
    public Type<UpdateAdvancedItemFrameDataPacket> type() { return TYPE; }

    public static final class StreamCodec
        implements net.minecraft.network.codec.StreamCodec<RegistryFriendlyByteBuf, UpdateAdvancedItemFrameDataPacket>
    {
        @Override
        public UpdateAdvancedItemFrameDataPacket decode(RegistryFriendlyByteBuf buffer)
        {
            var p = new UpdateAdvancedItemFrameDataPacket();
            p.EntityID = buffer.readIntLE();
            p.Rotation = buffer.readShortLE();
            p.ItemScale = buffer.readByte();
            p.LightLevel = buffer.readByte();
            return p;
        }

        @Override
        public void encode(RegistryFriendlyByteBuf o, UpdateAdvancedItemFrameDataPacket packet)
        {
            o.writeIntLE(packet.EntityID);
            o.writeShortLE(packet.Rotation);
            o.writeByte(packet.ItemScale);
            o.writeByte(packet.LightLevel);
        }
    }
}
