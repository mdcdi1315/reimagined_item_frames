package com.github.mdcdi1315.reimagined_item_frames.network;

import com.github.mdcdi1315.basemodslib.network.NetworkManager;
import com.github.mdcdi1315.basemodslib.network.INetworkBuilder;
import com.github.mdcdi1315.basemodslib.network.ServerSideNetworkPacketRegistrationInfo;

public final class Networking
{
    private Networking() {}

    public static NetworkManager MANAGER;

    public static void Initialize(NetworkManager network_manager)
    {
        INetworkBuilder builder = (MANAGER = network_manager).GetBuilder();
        builder.RegisterServerBoundPacket(new ServerSideNetworkPacketRegistrationInfo<>(
                UpdateBasicItemFrameDataPacket.class,
                UpdateBasicItemFrameDataPacket.TYPE,
                new UpdateBasicItemFrameDataPacket.StreamCodec(),
                UpdateBasicItemFrameDataPacket::HandlePacket
        ));
        builder.RegisterServerBoundPacket(new ServerSideNetworkPacketRegistrationInfo<>(
                UpdateAdvancedItemFrameDataPacket.class,
                UpdateAdvancedItemFrameDataPacket.TYPE,
                new UpdateAdvancedItemFrameDataPacket.StreamCodec(),
                UpdateAdvancedItemFrameDataPacket::HandlePacket
        ));
    }
}
