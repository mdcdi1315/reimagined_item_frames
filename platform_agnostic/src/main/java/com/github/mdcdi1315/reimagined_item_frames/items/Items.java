package com.github.mdcdi1315.reimagined_item_frames.items;

import com.github.mdcdi1315.basemodslib.item.IItemRegistrar;
import com.github.mdcdi1315.basemodslib.item.ItemRegistrationInformation;

import net.minecraft.world.item.Item;

public final class Items
{
    private Items() {}

    public static SimpleItemFrameItem SIMPLE_ITEM_FRAME;
    public static AdvancedItemFrameItem ADVANCED_ITEM_FRAME;
    public static GlassedSimpleItemFrameItem GLASSED_SIMPLE_ITEM_FRAME;
    public static GlassedAdvancedItemFrameItem GLASSED_ADVANCED_ITEM_FRAME;
    public static SimpleInvisibleItemFrameItem SIMPLE_INVISIBLE_ITEM_FRAME;
    public static AdvancedInvisibleItemFrameItem ADVANCED_INVISIBLE_ITEM_FRAME;

    public static void Initialize(IItemRegistrar registrar)
    {
        CreativeModeTabs.Initialize(registrar);
        registrar.Register("simple_item_frame", new ItemRegistrationInformation(
                (rl) -> SIMPLE_ITEM_FRAME = new SimpleItemFrameItem(CreateProperties()),
                CreativeModeTabs.REIMAGINED_ITEM_FRAMES
        ));
        registrar.Register("simple_invisible_item_frame", new ItemRegistrationInformation(
                (rl) -> SIMPLE_INVISIBLE_ITEM_FRAME = new SimpleInvisibleItemFrameItem(CreateProperties()),
                CreativeModeTabs.REIMAGINED_ITEM_FRAMES
        ));
        registrar.Register("glassed_simple_item_frame", new ItemRegistrationInformation(
                (rl) -> GLASSED_SIMPLE_ITEM_FRAME = new GlassedSimpleItemFrameItem(CreateProperties()),
                CreativeModeTabs.REIMAGINED_ITEM_FRAMES
        ));
        registrar.Register("advanced_item_frame", new ItemRegistrationInformation(
                (rl) -> ADVANCED_ITEM_FRAME = new AdvancedItemFrameItem(CreateProperties()),
                CreativeModeTabs.REIMAGINED_ITEM_FRAMES
        ));
        registrar.Register("advanced_invisible_item_frame", new ItemRegistrationInformation(
                (rl) -> ADVANCED_INVISIBLE_ITEM_FRAME = new AdvancedInvisibleItemFrameItem(CreateProperties()),
                CreativeModeTabs.REIMAGINED_ITEM_FRAMES
        ));
        registrar.Register("glassed_advanced_item_frame", new ItemRegistrationInformation(
                (rl) -> GLASSED_ADVANCED_ITEM_FRAME = new GlassedAdvancedItemFrameItem(CreateProperties()),
                CreativeModeTabs.REIMAGINED_ITEM_FRAMES
        ));
    }

    private static Item.Properties CreateProperties() { return new Item.Properties().stacksTo(128); }
}
