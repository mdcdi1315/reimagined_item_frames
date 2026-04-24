package com.github.mdcdi1315.reimagined_item_frames.entity;

import com.github.mdcdi1315.basemodslib.entity.IEntityTypeRegistrar;
import com.github.mdcdi1315.basemodslib.entity.EntityTypeRegistrationInfo;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

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
                () -> SIMPLE_INVISIBLE_ITEM_FRAME_ENTITY = EntityType.Builder.of((EntityType.EntityFactory<SimpleInvisibleItemFrameEntity>) SimpleInvisibleItemFrameEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).eyeHeight(0.0F).clientTrackingRange(10).updateInterval(Integer.MAX_VALUE).build("simple_invisible_item_frame")
        ));

        registrar.RegisterEntity("advanced_invisible_item_frame", new EntityTypeRegistrationInfo<>(
                () -> ADVANCED_INVISIBLE_ITEM_FRAME_ENTITY = EntityType.Builder.of((EntityType.EntityFactory<AdvancedInvisibleItemFrameEntity>) AdvancedInvisibleItemFrameEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).eyeHeight(0.0F).clientTrackingRange(10).updateInterval(Integer.MAX_VALUE).build("advanced_invisible_item_frame")
        ));

        registrar.RegisterEntity("simple_item_frame",  new EntityTypeRegistrationInfo<>(
                () -> SIMPLE_ITEM_FRAME_ENTITY = EntityType.Builder.of((EntityType.EntityFactory<SimpleItemFrameEntity>) SimpleItemFrameEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).eyeHeight(0.0F).clientTrackingRange(10).updateInterval(Integer.MAX_VALUE).build("simple_item_frame")
        ));

        registrar.RegisterEntity("advanced_item_frame", new EntityTypeRegistrationInfo<>(
                () -> ADVANCED_ITEM_FRAME_ENTITY = EntityType.Builder.of((EntityType.EntityFactory<AdvancedItemFrameEntity>) AdvancedItemFrameEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).eyeHeight(0.0F).clientTrackingRange(10).updateInterval(Integer.MAX_VALUE).build("advanced_item_frame")
        ));

        registrar.RegisterEntity("glassed_simple_item_frame",  new EntityTypeRegistrationInfo<>(
                () -> GLASSED_SIMPLE_ITEM_FRAME_ENTITY = EntityType.Builder.of((EntityType.EntityFactory<GlassedSimpleItemFrameEntity>) GlassedSimpleItemFrameEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).eyeHeight(0.0F).clientTrackingRange(10).updateInterval(Integer.MAX_VALUE).build("glassed_simple_item_frame")
        ));

        registrar.RegisterEntity("glassed_advanced_item_frame", new EntityTypeRegistrationInfo<>(
                () -> GLASSED_ADVANCED_ITEM_FRAME_ENTITY = EntityType.Builder.of((EntityType.EntityFactory<GlassedAdvancedItemFrameEntity>) GlassedAdvancedItemFrameEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).eyeHeight(0.0F).clientTrackingRange(10).updateInterval(Integer.MAX_VALUE).build("glassed_advanced_item_frame")
        ));
    }
}
