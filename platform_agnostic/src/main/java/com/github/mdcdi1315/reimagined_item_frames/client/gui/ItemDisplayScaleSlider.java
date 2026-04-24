package com.github.mdcdi1315.reimagined_item_frames.client.gui;

import com.github.mdcdi1315.basemodslib.utils.function.PrimitiveByteAction;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;

public class ItemDisplayScaleSlider
     extends AbstractSliderButton
{
    private final PrimitiveByteAction action;

    public ItemDisplayScaleSlider(int x, int y, int width, int height, byte value, PrimitiveByteAction action)
    {
        super(x, y, width, height, Component.empty(), value / 100d);
        this.action = action;
        updateMessage();
    }

    @Override
    protected void updateMessage()
    {
        this.setMessage(
                Component.translatable(
                        "gui.reimagined_item_frames.item_display_scale_slider_control.message",
                        (int)(this.value * 100d)
                )
        );
    }

    @Override
    protected void applyValue() { action.action((byte) (this.value * 100d)); }
}
