package com.github.mdcdi1315.reimagined_item_frames;

import com.github.mdcdi1315.basemodslib.eventapi.EventManager;
import com.github.mdcdi1315.basemodslib.mods.IClientModInstance;
import com.github.mdcdi1315.basemodslib.client.IMenuScreensRegistrar;
import com.github.mdcdi1315.basemodslib.client.IEntityRendererRegistrar;
import com.github.mdcdi1315.basemodslib.client.EntityRendererRegistrationInfo;

import com.github.mdcdi1315.reimagined_item_frames.client.*;
import com.github.mdcdi1315.reimagined_item_frames.client.gui.BasicItemFrameMenuScreen;
import com.github.mdcdi1315.reimagined_item_frames.client.gui.AdvancedItemFrameMenuScreen;

import com.github.mdcdi1315.reimagined_item_frames.menu.Menus;
import com.github.mdcdi1315.reimagined_item_frames.entity.Entities;

public final class ClientModInstance
    implements IClientModInstance
{
    @Override
    public void RegisterEntityRenderers(IEntityRendererRegistrar registrar)
    {
        registrar.Register(new EntityRendererRegistrationInfo<>(() -> Entities.SIMPLE_ITEM_FRAME_ENTITY, SimpleItemFrameRenderer::new));
        registrar.Register(new EntityRendererRegistrationInfo<>(() -> Entities.ADVANCED_ITEM_FRAME_ENTITY, AdvancedItemFrameRenderer::new));
        registrar.Register(new EntityRendererRegistrationInfo<>(() -> Entities.GLASSED_SIMPLE_ITEM_FRAME_ENTITY, GlassedSimpleItemFrameRenderer::new));
        registrar.Register(new EntityRendererRegistrationInfo<>(() -> Entities.GLASSED_ADVANCED_ITEM_FRAME_ENTITY, GlassedAdvancedItemFrameRenderer::new));
        registrar.Register(new EntityRendererRegistrationInfo<>(() -> Entities.SIMPLE_INVISIBLE_ITEM_FRAME_ENTITY, SimpleInvisibleItemFrameRenderer::new));
        registrar.Register(new EntityRendererRegistrationInfo<>(() -> Entities.ADVANCED_INVISIBLE_ITEM_FRAME_ENTITY, AdvancedInvisibleItemFrameRenderer::new));
    }

    @Override
    public void RegisterMenuScreens(IMenuScreensRegistrar registrar)
    {
        registrar.RegisterMenuScreen(() -> Menus.BASIC_ITEM_FRAME_MENU, BasicItemFrameMenuScreen::new);
        registrar.RegisterMenuScreen(() -> Menus.ADVANCED_ITEM_FRAME_MENU, AdvancedItemFrameMenuScreen::new);
    }

    @Override
    public void RegisterEvents(EventManager manager) { ModStaticDefinitionsBuilder.Initialize(manager); }

    @Override
    public void Dispose() { }

    @Override
    public String GetModId() { return ServerModInstance.MOD_ID; }
}
