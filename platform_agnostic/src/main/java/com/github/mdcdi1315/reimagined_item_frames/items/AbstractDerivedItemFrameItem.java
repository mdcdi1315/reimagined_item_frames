package com.github.mdcdi1315.reimagined_item_frames.items;

import com.github.mdcdi1315.reimagined_item_frames.entity.AbstractBaseItemFrameEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemFrameItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.gameevent.GameEvent;

public abstract class AbstractDerivedItemFrameItem
    extends ItemFrameItem
{
    public AbstractDerivedItemFrameItem(EntityType<? extends AbstractBaseItemFrameEntity> type, Properties properties) { super(type, properties); }

    protected abstract AbstractBaseItemFrameEntity ConstructEntityInstance(Level level, BlockPos position, Direction direction);

    @Override
    public final InteractionResult useOn(UseOnContext context)
    {
        Player player = context.getPlayer();
        ItemStack itemstack = context.getItemInHand();
        Direction direction = context.getClickedFace();
        BlockPos blockpos = context.getClickedPos().relative(direction);

        if (player != null && !this.mayPlace(player, direction, itemstack, blockpos)) {
            return InteractionResult.FAIL;
        } else {
            Level level = context.getLevel();
            AbstractBaseItemFrameEntity entity = ConstructEntityInstance(level, blockpos, direction);

            CustomData customdata = itemstack.getOrDefault(DataComponents.ENTITY_DATA, CustomData.EMPTY);
            if (!customdata.isEmpty()) {
                EntityType.updateCustomEntityTag(level, player, entity, customdata);
            }

            if (entity.survives()) {
                if (!level.isClientSide) {
                    entity.playPlacementSound();
                    level.gameEvent(player, GameEvent.ENTITY_PLACE, entity.position());
                    level.addFreshEntity(entity);
                }

                itemstack.shrink(1);
                return InteractionResult.sidedSuccess(level.isClientSide);
            } else {
                return InteractionResult.CONSUME;
            }
        }
    }
}
