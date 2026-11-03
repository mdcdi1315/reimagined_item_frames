package com.github.mdcdi1315.reimagined_item_frames.entity;

import com.github.mdcdi1315.basemodslib.registries.RegistryUtils;
import com.github.mdcdi1315.basemodslib.entity.IEntityTypeRegistrar;
import com.github.mdcdi1315.basemodslib.entity.EntityTypeRegistrationInfo;

import com.github.mdcdi1315.reimagined_item_frames.ServerModInstance;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.core.registries.Registries;

public final class Entities
{
    private Entities() {}

    public static EntityType<SimpleItemFrameEntity> SIMPLE_ITEM_FRAME_ENTITY;
    public static EntityType<AdvancedItemFrameEntity> ADVANCED_ITEM_FRAME_ENTITY;
    public static EntityType<GlassedSimpleItemFrameEntity> GLASSED_SIMPLE_ITEM_FRAME_ENTITY;
    public static EntityType<GlassedAdvancedItemFrameEntity> GLASSED_ADVANCED_ITEM_FRAME_ENTITY;
    public static EntityType<SimpleInvisibleItemFrameEntity> SIMPLE_INVISIBLE_ITEM_FRAME_ENTITY;
    public static EntityType<AdvancedInvisibleItemFrameEntity> ADVANCED_INVISIBLE_ITEM_FRAME_ENTITY;

    public static void Initialize(IEntityTypeRegistrar registrar)
    {
        registrar.RegisterEntity("simple_invisible_item_frame", new EntityTypeRegistrationInfo<>(
                () -> SIMPLE_INVISIBLE_ITEM_FRAME_ENTITY = EntityType.Builder.of(
                        (EntityType.EntityFactory<SimpleInvisibleItemFrameEntity>) SimpleInvisibleItemFrameEntity::new, MobCategory.MISC)
                        .sized(0.5F, 0.5F)
                        .eyeHeight(0.0F)
                        .clientTrackingRange(10)
                        .updateInterval(Integer.MAX_VALUE)
                        .build(ResourceKey.create(
                                Registries.ENTITY_TYPE,
                                RegistryUtils.ConstructResourceLocation(ServerModInstance.MOD_ID, "simple_invisible_item_frame")
                        ))
        ));

        registrar.RegisterEntity("advanced_invisible_item_frame", new EntityTypeRegistrationInfo<>(
                () -> ADVANCED_INVISIBLE_ITEM_FRAME_ENTITY = EntityType.Builder.of(
                        (EntityType.EntityFactory<AdvancedInvisibleItemFrameEntity>) AdvancedInvisibleItemFrameEntity::new, MobCategory.MISC)
                        .sized(0.5F, 0.5F)
                        .eyeHeight(0.0F)
                        .clientTrackingRange(10)
                        .updateInterval(Integer.MAX_VALUE)
                        .build(ResourceKey.create(
                                Registries.ENTITY_TYPE,
                                RegistryUtils.ConstructResourceLocation(ServerModInstance.MOD_ID, "advanced_invisible_item_frame")
                        ))
        ));

        registrar.RegisterEntity("simple_item_frame",  new EntityTypeRegistrationInfo<>(
                () -> SIMPLE_ITEM_FRAME_ENTITY = EntityType.Builder.of(
                        (EntityType.EntityFactory<SimpleItemFrameEntity>) SimpleItemFrameEntity::new, MobCategory.MISC)
                        .sized(0.5F, 0.5F)
                        .eyeHeight(0.0F)
                        .clientTrackingRange(10)
                        .updateInterval(Integer.MAX_VALUE)
                        .build(ResourceKey.create(
                                Registries.ENTITY_TYPE,
                                RegistryUtils.ConstructResourceLocation(ServerModInstance.MOD_ID, "simple_item_frame")
                        ))
        ));

        registrar.RegisterEntity("advanced_item_frame", new EntityTypeRegistrationInfo<>(
                () -> ADVANCED_ITEM_FRAME_ENTITY = EntityType.Builder.of(
                        (EntityType.EntityFactory<AdvancedItemFrameEntity>) AdvancedItemFrameEntity::new, MobCategory.MISC)
                        .sized(0.5F, 0.5F)
                        .eyeHeight(0.0F)
                        .clientTrackingRange(10)
                        .updateInterval(Integer.MAX_VALUE)
                        .build(ResourceKey.create(
                                Registries.ENTITY_TYPE,
                                RegistryUtils.ConstructResourceLocation(ServerModInstance.MOD_ID, "advanced_item_frame")
                        ))
        ));

        registrar.RegisterEntity("glassed_simple_item_frame",  new EntityTypeRegistrationInfo<>(
                () -> GLASSED_SIMPLE_ITEM_FRAME_ENTITY = EntityType.Builder.of(
                        (EntityType.EntityFactory<GlassedSimpleItemFrameEntity>) GlassedSimpleItemFrameEntity::new, MobCategory.MISC)
                        .sized(0.5F, 0.5F)
                        .eyeHeight(0.0F)
                        .clientTrackingRange(10)
                        .updateInterval(Integer.MAX_VALUE)
                        .build(ResourceKey.create(
                                Registries.ENTITY_TYPE,
                                RegistryUtils.ConstructResourceLocation(ServerModInstance.MOD_ID, "glassed_simple_item_frame")
                        ))
        ));

        registrar.RegisterEntity("glassed_advanced_item_frame", new EntityTypeRegistrationInfo<>(
                () -> GLASSED_ADVANCED_ITEM_FRAME_ENTITY = EntityType.Builder.of(
                        (EntityType.EntityFactory<GlassedAdvancedItemFrameEntity>) GlassedAdvancedItemFrameEntity::new, MobCategory.MISC)
                        .sized(0.5F, 0.5F)
                        .eyeHeight(0.0F)
                        .clientTrackingRange(10)
                        .updateInterval(Integer.MAX_VALUE)
                        .build(ResourceKey.create(
                                Registries.ENTITY_TYPE,
                                RegistryUtils.ConstructResourceLocation(ServerModInstance.MOD_ID, "glassed_advanced_item_frame")
                        ))
        ));
    }
}
