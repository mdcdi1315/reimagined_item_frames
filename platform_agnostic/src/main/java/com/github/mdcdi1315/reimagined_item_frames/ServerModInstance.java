package com.github.mdcdi1315.reimagined_item_frames;

import com.github.mdcdi1315.basemodslib.item.IItemRegistrar;
import com.github.mdcdi1315.basemodslib.eventapi.EventManager;
import com.github.mdcdi1315.basemodslib.network.NetworkManager;
import com.github.mdcdi1315.basemodslib.menu.IMenuTypeRegistrar;
import com.github.mdcdi1315.basemodslib.mods.IServerModInstance;
import com.github.mdcdi1315.basemodslib.entity.IEntityTypeRegistrar;
import com.github.mdcdi1315.basemodslib.eventapi.mods.registries.MenuTypeRegistryFinalizedEvent;

import com.github.mdcdi1315.reimagined_item_frames.menu.Menus;
import com.github.mdcdi1315.reimagined_item_frames.items.Items;
import com.github.mdcdi1315.reimagined_item_frames.entity.Entities;
import com.github.mdcdi1315.reimagined_item_frames.tags.EntityTags;
import com.github.mdcdi1315.reimagined_item_frames.network.Networking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ServerModInstance
    implements IServerModInstance
{
    public static Logger LOGGER;
    public static final String MOD_ID = "reimagined_item_frames";

    @Override
    public void Initialize()
    {
        LOGGER = LoggerFactory.getLogger("Reimagined Item Frames Mod");
        EntityTags.Initialize();
    }

    @Override
    public void RegisterEntityTypes(IEntityTypeRegistrar registrar) { Entities.Initialize(registrar); }

    @Override
    public void RegisterEvents(EventManager manager)
    {
        manager.AddEventListener(MenuTypeRegistryFinalizedEvent.class, Menus::OnFinalize);
    }

    @Override
    public void RegisterItems(IItemRegistrar registrar) { Items.Initialize(registrar); }

    @Override
    public void RegisterMenuTypes(IMenuTypeRegistrar registrar) { Menus.Initialize(registrar); }

    @Override
    public void InitializeNetwork(NetworkManager manager) { Networking.Initialize(manager); }

    @Override
    public String GetModId() { return MOD_ID; }

    @Override
    public void Dispose()
    {
        LOGGER.info("Reimagined Item Frames Mod Stopping!");
        LOGGER = null;
    }
}
