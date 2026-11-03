package com.github.mdcdi1315.reimagined_item_frames.client;

import com.github.mdcdi1315.DotNetLayer.System.Diagnostics.CodeAnalysis.AllowNull;

import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.state.MapRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.entity.state.EntityRenderState;

import net.minecraft.core.Direction;
import net.minecraft.world.level.saveddata.maps.MapId;

public class RIFRenderState
    extends EntityRenderState
{
    public int rotation;
    public Direction direction;
    @AllowNull public MapId map_id;
    public final ItemStackRenderState item;
    public final MapRenderState map_render_state;
    public final BlockModelRenderState frame_model;

    public RIFRenderState()
    {
        super();
        this.direction = Direction.NORTH;
        this.item = new ItemStackRenderState();
        this.map_render_state = new MapRenderState();
        this.frame_model = new BlockModelRenderState();
    }
}
