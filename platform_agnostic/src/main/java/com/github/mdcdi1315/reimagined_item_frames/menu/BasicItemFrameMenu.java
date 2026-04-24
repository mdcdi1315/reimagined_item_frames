package com.github.mdcdi1315.reimagined_item_frames.menu;

import com.github.mdcdi1315.reimagined_item_frames.network.Networking;
import com.github.mdcdi1315.reimagined_item_frames.entity.AbstractBaseItemFrameEntity;
import com.github.mdcdi1315.reimagined_item_frames.network.UpdateBasicItemFrameDataPacket;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;

public class BasicItemFrameMenu
    extends AbstractItemFrameMenu
{
    private int rot_in_degrees;

    public BasicItemFrameMenu(int container_id, Inventory inv, FriendlyByteBuf buffer)
    {
        super(Menus.BASIC_ITEM_FRAME_MENU, container_id, inv, buffer);
        if (buffer != null) {
            rot_in_degrees = buffer.readShortLE();
        }
    }

    public BasicItemFrameMenu(int container_id, Inventory inv, AbstractBaseItemFrameEntity item_frame) { this(Menus.BASIC_ITEM_FRAME_MENU, container_id, inv,item_frame); }

    protected BasicItemFrameMenu(MenuType<? extends BasicItemFrameMenu> menuType, int containerId, Inventory player_inventory, AbstractBaseItemFrameEntity entity_reference)
    {
        super(menuType, containerId, player_inventory, entity_reference);
        if (entity_reference != null) { rot_in_degrees = entity_reference.getRotation(); }
    }

    public int GetRotation() { return rot_in_degrees; }

    public void ApplyRotation(int rot_in_degrees) { this.rot_in_degrees = rot_in_degrees; }

    @Override
    public void removed(Player player)
    {
        if (client_entity_id != null) {
            Networking.MANAGER.SendToServer(new UpdateBasicItemFrameDataPacket(client_entity_id, rot_in_degrees));
        }
        super.removed(player);
    }
}
