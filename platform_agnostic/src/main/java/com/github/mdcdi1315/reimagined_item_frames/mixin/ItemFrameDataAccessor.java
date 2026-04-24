package com.github.mdcdi1315.reimagined_item_frames.mixin;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.network.syncher.EntityDataAccessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemFrame.class)
public interface ItemFrameDataAccessor
{
    @Accessor("DATA_ITEM")
    static EntityDataAccessor<ItemStack> GetItemStackData() { throw new AssertionError("Implemented through Mixin."); }

    @Accessor("DATA_ROTATION")
    static EntityDataAccessor<Integer> GetRotationData() { throw new AssertionError("Implemented through Mixin."); }

    @Accessor("fixed")
    boolean GetFixed();

    @Accessor("fixed")
    void SetFixed(boolean value);

    @Accessor("dropChance")
    float GetDropChance();

    @Accessor("dropChance")
    void SetDropChance(float value);
}
