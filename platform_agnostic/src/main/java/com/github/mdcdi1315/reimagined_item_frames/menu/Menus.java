package com.github.mdcdi1315.reimagined_item_frames.menu;

import com.github.mdcdi1315.basemodslib.menu.MenuTypeCreaterEx;
import com.github.mdcdi1315.basemodslib.menu.IMenuTypeRegistrar;
import com.github.mdcdi1315.basemodslib.registries.RegistryUtils;
import com.github.mdcdi1315.basemodslib.menu.MenuTypeRegistrationInfo;
import com.github.mdcdi1315.basemodslib.eventapi.mods.registries.MenuTypeRegistryFinalizedEvent;

import com.github.mdcdi1315.reimagined_item_frames.ServerModInstance;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.AbstractContainerMenu;

public final class Menus
{
    private Menus() {}

    public static MenuType<BasicItemFrameMenu> BASIC_ITEM_FRAME_MENU;
    public static MenuType<AdvancedItemFrameMenu> ADVANCED_ITEM_FRAME_MENU;

    public static void Initialize(IMenuTypeRegistrar registrar)
    {
        registrar.Register("basic_item_frame_menu", new MenuTypeRegistrationInfo<>(FeatureFlagSet.of(), (MenuTypeCreaterEx<? extends AbstractContainerMenu>) BasicItemFrameMenu::new));
        registrar.Register("advanced_item_frame_menu", new MenuTypeRegistrationInfo<>(FeatureFlagSet.of(), (MenuTypeCreaterEx<? extends AbstractContainerMenu>) AdvancedItemFrameMenu::new));
    }

    public static void OnFinalize(MenuTypeRegistryFinalizedEvent event)
    {
        BASIC_ITEM_FRAME_MENU = event.GetRegistryObjectChecked(RegistryUtils.ConstructResourceLocation(ServerModInstance.MOD_ID, "basic_item_frame_menu"));
        ADVANCED_ITEM_FRAME_MENU = event.GetRegistryObjectChecked(RegistryUtils.ConstructResourceLocation(ServerModInstance.MOD_ID, "advanced_item_frame_menu"));
    }
}
