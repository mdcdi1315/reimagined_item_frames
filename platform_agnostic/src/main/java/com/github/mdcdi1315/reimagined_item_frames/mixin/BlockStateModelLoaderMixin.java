package com.github.mdcdi1315.reimagined_item_frames.mixin;

import com.github.mdcdi1315.basemodslib.BaseModsLib;
import com.github.mdcdi1315.basemodslib.ClientOnlyEnvironment;

import com.github.mdcdi1315.reimagined_item_frames.client.ModStaticDefinitionsBuilder;

import net.minecraft.world.level.block.Block;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.client.resources.model.BlockStateModelLoader;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.HashMap;

@ClientOnlyEnvironment
@Mixin(BlockStateModelLoader.class)
public abstract class BlockStateModelLoaderMixin
{
    @Accessor("STATIC_DEFINITIONS")
    static Map<ResourceLocation, StateDefinition<Block, BlockState>> GetStaticDefinitions() { throw new Error("Implemented through Mixin"); }

    @Final
    @Mutable
    @Accessor("STATIC_DEFINITIONS")
    static void SetStaticDefinitions(Map<ResourceLocation, StateDefinition<Block, BlockState>> map) { throw new Error("Implemented through Mixin"); }

    @Inject(method = "<clinit>", at = @At(value = "TAIL"))
    private static void OnStaticInitEnd(CallbackInfo ci)
    {
        HashMap<ResourceLocation, StateDefinition<Block, BlockState>> map = new HashMap<>(GetStaticDefinitions());
        BaseModsLib.GetEventsManager().FireEvent(new ModStaticDefinitionsBuilder.Event(map::put));
        SetStaticDefinitions(map);
    }
}
