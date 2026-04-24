package com.github.mdcdi1315.reimagined_item_frames.menu;

import com.github.mdcdi1315.DotNetLayer.System.Diagnostics.CodeAnalysis.NotNull;
import com.github.mdcdi1315.DotNetLayer.System.Diagnostics.CodeAnalysis.MaybeNull;
import com.github.mdcdi1315.DotNetLayer.System.Diagnostics.CodeAnalysis.AllowNull;

import com.github.mdcdi1315.basemodslib.registries.RegistryUtils;

import com.github.mdcdi1315.reimagined_item_frames.ServerModInstance;

import com.github.mdcdi1315.reimagined_item_frames.entity.AbstractBaseItemFrameEntity;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.SimpleContainer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

public abstract class AbstractItemFrameMenu
    extends AbstractContainerMenu
{
    public static final int INVENTORY_X_POINT = 12;
    public static final int INVENTORY_Y_POINT = 28;
    public static final int INVENTORY_Y_HOTBAR_POINT = 86;

    public static final int RENDERED_ITEM_Y_POINT = 54;
    public static final int RENDERED_ITEM_X_POINT = 202;
    public static final int TEX_X_SIZE = 384;
    public static final int TEX_Y_SIZE = 128, ACTUAL_TEX_Y_SIZE = 110;
    public static final ResourceLocation MENU_TEXTURE = RegistryUtils.ConstructResourceLocation(ServerModInstance.MOD_ID, "textures/gui/item_frame_menu_bgd.png");

    protected Integer client_entity_id;
    private final SimpleContainer container;
    @AllowNull
    private final AbstractBaseItemFrameEntity entity_reference;

    public AbstractItemFrameMenu(@AllowNull MenuType<? extends AbstractItemFrameMenu> menuType, int containerId, Inventory player_inventory, AbstractBaseItemFrameEntity entity_reference)
    {
        super(menuType, containerId);
        client_entity_id = null;
        container = new SimpleContainer(1);
        this.entity_reference = entity_reference;

        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 9; ++y) {
                this.addSlot(new Slot(player_inventory, y + x * 9 + 9, INVENTORY_X_POINT + y * 18, INVENTORY_Y_POINT + x * 18));
            }
        }

        for (int hs = 0; hs < 9; ++hs) {
            this.addSlot(new Slot(player_inventory, hs, INVENTORY_X_POINT + hs * 18, INVENTORY_Y_HOTBAR_POINT));
        }

        container.setItem(0, entity_reference.getItem());
        this.addSlot(new Slot(container, 0, RENDERED_ITEM_X_POINT, RENDERED_ITEM_Y_POINT));
    }

    public AbstractItemFrameMenu(@AllowNull MenuType<? extends AbstractItemFrameMenu> menuType, int containerId, Inventory player_inventory)  { this(menuType, containerId, player_inventory, (FriendlyByteBuf) null); }

    public AbstractItemFrameMenu(@AllowNull MenuType<? extends AbstractItemFrameMenu> menuType, int containerId, Inventory player_inventory, FriendlyByteBuf buffer)
    {
        super(menuType, containerId);
        this.entity_reference = null;
        container = new SimpleContainer(1);

        for (int x = 0; x < 3; ++x) {
            for (int y = 0; y < 9; ++y) {
                this.addSlot(new Slot(player_inventory, y + x * 9 + 9, INVENTORY_X_POINT + y * 18, INVENTORY_Y_POINT + x * 18));
            }
        }

        for (int hs = 0; hs < 9; ++hs) {
            this.addSlot(new Slot(player_inventory, hs, INVENTORY_X_POINT + hs * 18, INVENTORY_Y_HOTBAR_POINT));
        }

        this.addSlot(new Slot(container, 0, RENDERED_ITEM_X_POINT, RENDERED_ITEM_Y_POINT));

        if (buffer != null) { client_entity_id = buffer.readIntLE(); }
    }

    @NotNull
    public final Container GetConstructedContainer() { return this.container; }

    @MaybeNull
    public final AbstractBaseItemFrameEntity GetEntityReference() { return entity_reference; }

    @Override
    public boolean stillValid(Player player) { return true; }

    @Override
    public void removed(Player player)
    {
        if (entity_reference != null) {
            entity_reference.setItem(container.getItem(0));
        }
        super.removed(player);
    }

    public Component GetDisplayItemComponent() {
        return Component.literal("Item To Display");
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index)
    {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot.hasItem())
        {
            ItemStack itemStackInSlot = slot.getItem();
            itemStack = itemStackInSlot.copy();

            if (index < 1) {
                if (!this.moveItemStackTo(itemStackInSlot, 1, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemStackInSlot, 0, 1, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStackInSlot.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemStack;
    }
}
