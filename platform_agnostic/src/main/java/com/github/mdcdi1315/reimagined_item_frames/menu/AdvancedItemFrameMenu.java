package com.github.mdcdi1315.reimagined_item_frames.menu;

import com.github.mdcdi1315.reimagined_item_frames.network.Networking;
import com.github.mdcdi1315.reimagined_item_frames.network.UpdateAdvancedItemFrameDataPacket;
import com.github.mdcdi1315.reimagined_item_frames.entity.AbstractAdvancedBaseItemFrameEntity;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;

public class AdvancedItemFrameMenu
    extends AbstractItemFrameMenu
{
    private int rot_in_degrees;
    private byte desired_item_scale;
    private byte desired_light_level;

    public AdvancedItemFrameMenu(int container_id, Inventory inv, FriendlyByteBuf buffer) { this(Menus.ADVANCED_ITEM_FRAME_MENU, container_id, inv, buffer); }

    public AdvancedItemFrameMenu(int containerId, Inventory player_inventory, AbstractAdvancedBaseItemFrameEntity entity_reference) { this(Menus.ADVANCED_ITEM_FRAME_MENU, containerId, player_inventory, entity_reference); }

    protected AdvancedItemFrameMenu(MenuType<? extends AbstractItemFrameMenu> menuType, int containerId, Inventory player_inventory, AbstractAdvancedBaseItemFrameEntity entity_reference)
    {
        super(menuType, containerId, player_inventory, entity_reference);
        if (entity_reference != null)
        {
            this.rot_in_degrees = entity_reference.getRotation();
            this.desired_item_scale = entity_reference.GetItemDisplayScale();
            this.desired_light_level = entity_reference.GetDesiredLightLevel();
        }
    }

    protected AdvancedItemFrameMenu(MenuType<? extends AbstractItemFrameMenu> menuType, int containerId, Inventory player_inventory) { super(menuType, containerId, player_inventory); }

    protected AdvancedItemFrameMenu(MenuType<? extends AbstractItemFrameMenu> menuType, int containerId, Inventory player_inventory, FriendlyByteBuf buffer)
    {
        super(menuType, containerId, player_inventory, buffer);
        if (buffer != null)
        {
            this.rot_in_degrees = buffer.readShortLE();
            this.desired_item_scale = buffer.readByte();
            this.desired_light_level = buffer.readByte();
        }
    }

    public int GetRotation() { return this.rot_in_degrees; }

    public byte GetDesiredItemScale() { return this.desired_item_scale; }

    public byte GetDesiredLightLevel() { return this.desired_light_level; }

    public void SetRotation(int rotation) { this.rot_in_degrees = rotation; }

    public void SetDesiredItemScale(byte scale) { this.desired_item_scale = scale; }

    public void SetDesiredLightLevel(byte light) { this.desired_light_level = light; }

    @Override
    public void removed(Player player)
    {
        if (client_entity_id != null)
        {
            Networking.MANAGER.SendToServer(new UpdateAdvancedItemFrameDataPacket(
                    client_entity_id,
                    (short) rot_in_degrees,
                    desired_item_scale,
                    desired_light_level
            ));
        }
        super.removed(player);
    }
}
