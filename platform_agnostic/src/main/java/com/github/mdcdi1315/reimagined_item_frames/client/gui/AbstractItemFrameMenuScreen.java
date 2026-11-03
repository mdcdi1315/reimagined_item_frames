package com.github.mdcdi1315.reimagined_item_frames.client.gui;

import com.github.mdcdi1315.reimagined_item_frames.menu.AbstractItemFrameMenu;

import net.minecraft.network.chat.Component;

import net.minecraft.world.entity.player.Inventory;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;

public abstract class AbstractItemFrameMenuScreen<T extends AbstractItemFrameMenu>
    extends AbstractContainerScreen<T>
{
    private int display_item_component_width;
    private Component display_item_component;

    public static final int SLIDER_DISPLACEMENT_FROM_BOTTOM = 42;

    public AbstractItemFrameMenuScreen(T menu, Inventory playerInventory, Component title)
    {
        super(
                menu,
                playerInventory,
                title,
                AbstractItemFrameMenu.TEX_X_SIZE,
                AbstractItemFrameMenu.ACTUAL_TEX_Y_SIZE
        );
        titleLabelX = 7;
        titleLabelY = 4;
        inventoryLabelX = 11;
        inventoryLabelY = 17;
    }

    @Override
    protected void init()
    {
        super.init();
        display_item_component = menu.GetDisplayItemComponent();
        display_item_component_width = font.width(display_item_component);
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor gc, int mouse_x, int mouse_y, float partial_tick)
    {
        super.extractRenderState(gc, mouse_x, mouse_y, partial_tick);
        this.extractTooltip(gc, mouse_x, mouse_y);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor gc, int mouse_x, int mouse_y, float partial_tick)
    {
        this.extractTransparentBackground(gc);
        gc.blit(
                RenderPipelines.GUI_TEXTURED,
                AbstractItemFrameMenu.MENU_TEXTURE,
                leftPos,
                topPos,
                0,
                0,
                this.imageWidth,
                this.imageHeight,
                AbstractItemFrameMenu.TEX_X_SIZE,
                AbstractItemFrameMenu.TEX_Y_SIZE
        );
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor gc, int xm, int ym)
    {
        super.extractLabels(gc, xm, ym);
        gc.text(font, display_item_component, (AbstractItemFrameMenu.RENDERED_ITEM_X_POINT + 10) - (display_item_component_width / 2), AbstractItemFrameMenu.RENDERED_ITEM_Y_POINT + 22, 0xFF404040, false);
    }
}
