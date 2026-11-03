package com.github.mdcdi1315.reimagined_item_frames.tags;

import com.github.mdcdi1315.basemodslib.registries.RegistryUtils;

import com.github.mdcdi1315.reimagined_item_frames.ServerModInstance;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.core.registries.Registries;

public final class EntityTags
{
    private EntityTags() {}

    public static TagKey<EntityType<?>> ITEM_FRAMES;

    public static void Initialize()
    {
        ITEM_FRAMES = TagKey.create(
                Registries.ENTITY_TYPE,
                RegistryUtils.ConstructResourceLocation(ServerModInstance.MOD_ID, "item_frames")
        );
    }
}
