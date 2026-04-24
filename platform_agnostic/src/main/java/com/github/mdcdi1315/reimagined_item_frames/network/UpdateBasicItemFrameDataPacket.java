package com.github.mdcdi1315.reimagined_item_frames.network;

import com.github.mdcdi1315.basemodslib.registries.RegistryUtils;
import com.github.mdcdi1315.reimagined_item_frames.ServerModInstance;
import com.github.mdcdi1315.reimagined_item_frames.entity.AbstractBaseItemFrameEntity;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public final class UpdateBasicItemFrameDataPacket
    implements CustomPacketPayload
{
    public static final Type<UpdateBasicItemFrameDataPacket> TYPE = new Type<>(RegistryUtils.ConstructResourceLocation(ServerModInstance.MOD_ID, "update_rotation_value_to_item_frame_entity_packet"));

    public int EntityID;
    public short NewRotationValue;

    public UpdateBasicItemFrameDataPacket(int eid, int rot_value)
    {
        this.EntityID = eid;
        this.NewRotationValue = (short) rot_value;
    }

    private UpdateBasicItemFrameDataPacket() {}

    @Override
    public Type<UpdateBasicItemFrameDataPacket> type() { return TYPE; }

    public static void HandlePacket(ServerPlayer sp, UpdateBasicItemFrameDataPacket packet)
    {
        if (sp.level().getEntity(packet.EntityID) instanceof AbstractBaseItemFrameEntity b) { b.setRotation(packet.NewRotationValue); }
    }

    public static final class StreamCodec
        implements net.minecraft.network.codec.StreamCodec<RegistryFriendlyByteBuf, UpdateBasicItemFrameDataPacket>
    {
        @Override
        public UpdateBasicItemFrameDataPacket decode(RegistryFriendlyByteBuf buffer)
        {
            var p = new UpdateBasicItemFrameDataPacket();
            p.EntityID = buffer.readIntLE();
            p.NewRotationValue = buffer.readShortLE();
            return p;
        }

        @Override
        public void encode(RegistryFriendlyByteBuf o, UpdateBasicItemFrameDataPacket packet)
        {
            o.writeIntLE(packet.EntityID);
            o.writeShortLE(packet.NewRotationValue);
        }
    }
}
