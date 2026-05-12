package com.github.mdcdi1315.reimagined_item_frames.items;

import com.github.mdcdi1315.basemodslib.item.IItemRegistrar;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;

public final class CreativeModeTabs
{
    private CreativeModeTabs() {}

    public static final CreativeModeTab REIMAGINED_ITEM_FRAMES;

    static {
        REIMAGINED_ITEM_FRAMES = CreativeModeTab
                .builder(CreativeModeTab.Row.TOP, 0)
                .icon(CreativeModeTabs::GetIcon)
                .title(Component.translatable("creative_mode_tab.reimagined_item_frames"))
                .build();
    }

    private static ItemStack GetIcon() { return new ItemStack(Items.SIMPLE_ITEM_FRAME); }

    public static void Initialize(IItemRegistrar registrar)
    {
        registrar.RegisterCreativeModeTab("reimagined_item_frames_tab", REIMAGINED_ITEM_FRAMES);
    }
}
