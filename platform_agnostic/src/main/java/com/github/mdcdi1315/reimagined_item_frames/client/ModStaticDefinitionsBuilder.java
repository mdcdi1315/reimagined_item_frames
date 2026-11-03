package com.github.mdcdi1315.reimagined_item_frames.client;

import com.github.mdcdi1315.DotNetLayer.System.Action2;

import com.github.mdcdi1315.basemodslib.eventapi.EventManager;
import com.github.mdcdi1315.basemodslib.registries.RegistryUtils;
import com.github.mdcdi1315.basemodslib.eventapi.IDestroyedOnUseEvent;

import com.github.mdcdi1315.reimagined_item_frames.ServerModInstance;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public final class ModStaticDefinitionsBuilder
{
    private ModStaticDefinitionsBuilder() {}

    public record Event(Action2<ResourceLocation, StateDefinition<Block, BlockState>> register) implements IDestroyedOnUseEvent { }

    public static void Initialize(EventManager manager)
    {
        manager.AddEvent(Event.class);
        manager.AddEventListener(Event.class, ModStaticDefinitionsBuilder::InitializeDefinitions);
    }

    public static StateDefinition<Block, BlockState> CreateFakeDefinition()
    {
        return new StateDefinition.Builder<Block, BlockState>(Blocks.AIR)
                .add(BlockStateProperties.MAP)
                .create(Block::defaultBlockState, BlockState::new);
    }

    public static StateDefinition<Block, BlockState> SIMPLE_ITEM_FRAME_FAKE_DEF;
    public static StateDefinition<Block, BlockState> ADVANCED_ITEM_FRAME_FAKE_DEF;
    public static StateDefinition<Block, BlockState> GLASSED_SIMPLE_ITEM_FRAME_FAKE_DEF;
    public static StateDefinition<Block, BlockState> GLASSED_ADVANCED_ITEM_FRAME_FAKE_DEF;

    private static void InitializeDefinitions(Event register)
    {
        register.register.action(
                RegistryUtils.ConstructResourceLocation(ServerModInstance.MOD_ID, "simple_item_frame"),
                SIMPLE_ITEM_FRAME_FAKE_DEF = CreateFakeDefinition()
        );
        register.register.action(
                RegistryUtils.ConstructResourceLocation(ServerModInstance.MOD_ID, "advanced_item_frame"),
                ADVANCED_ITEM_FRAME_FAKE_DEF = CreateFakeDefinition()
        );
        register.register.action(
                RegistryUtils.ConstructResourceLocation(ServerModInstance.MOD_ID, "glassed_simple_item_frame"),
                GLASSED_SIMPLE_ITEM_FRAME_FAKE_DEF = CreateFakeDefinition()
        );
        register.register.action(
                RegistryUtils.ConstructResourceLocation(ServerModInstance.MOD_ID, "glassed_advanced_item_frame"),
                GLASSED_ADVANCED_ITEM_FRAME_FAKE_DEF = CreateFakeDefinition()
        );
        ServerModInstance.LOGGER.info("Successfully initialized static definitions for the custom item frames!");
    }
}