package com.github.mdcdi1315.reimagined_item_frames.client;

import com.github.mdcdi1315.DotNetLayer.System.Action2;

import com.github.mdcdi1315.basemodslib.eventapi.EventManager;
import com.github.mdcdi1315.basemodslib.registries.RegistryUtils;
import com.github.mdcdi1315.basemodslib.eventapi.IDestroyableEvent;

import com.github.mdcdi1315.reimagined_item_frames.ServerModInstance;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public final class ModStaticDefinitionsBuilder
{
    private ModStaticDefinitionsBuilder() {}

    public record Event(Action2<ResourceLocation, StateDefinition<Block, BlockState>> register) implements IDestroyableEvent { }

    public static void Initialize(EventManager manager)
    {
        manager.AddEvent(Event.class);
        manager.AddEventListener(Event.class, ModStaticDefinitionsBuilder::InitializeDefinitions);
    }

    private static void InitializeDefinitions(Event register)
    {
        StateDefinition<Block, BlockState> definition = new StateDefinition.Builder<Block, BlockState>(Blocks.AIR).create(Block::defaultBlockState, BlockState::new);
        register.register.action(
                RegistryUtils.ConstructResourceLocation(ServerModInstance.MOD_ID, "simple_item_frame"),
                definition
        );
        register.register.action(
                RegistryUtils.ConstructResourceLocation(ServerModInstance.MOD_ID, "advanced_item_frame"),
                definition
        );
        register.register.action(
                RegistryUtils.ConstructResourceLocation(ServerModInstance.MOD_ID, "glassed_simple_item_frame"),
                definition
        );
        register.register.action(
                RegistryUtils.ConstructResourceLocation(ServerModInstance.MOD_ID, "glassed_advanced_item_frame"),
                definition
        );
    }
}