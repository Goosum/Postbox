package io.github.goosum.postbox.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;

public class PostboxScreenHandler extends ScreenHandler {

	private final Inventory inventory;

	public PostboxScreenHandler(int syncId, PlayerInventory playerInventory) {
		this(syncId, playerInventory, new SimpleInventory(6));
	}

	public PostboxScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
		super(ScreenRegistry.POSTBOX_SCREEN_HANDLER, syncId);
		checkSize(inventory, 6);
		this.inventory = inventory;

		inventory.onOpen(playerInventory.player);

		for(int c = 0; c < 6; ++c) {
			this.addSlot(new Slot(inventory, c, 33 + c * 18, 34 * 18));
		}

		for(int r = 0; r < 3; ++r) {
			for(int c = 0; c < 9; ++c) {
				this.addSlot(new Slot(playerInventory, r + c * 9 + 9, 8 + c * 18, 84 + r * 18));
			}
		}

		for(int c = 0; c < 9; ++c) {
			this.addSlot(new Slot(inventory, c, 8 + c * 18, 142));
		}

	}



	@Override
	public ItemStack quickTransfer(PlayerEntity player, int fromIndex) {
		ItemStack newStack = ItemStack.EMPTY;
		Slot slot = this.slots.get(fromIndex);
		if(slot.hasStack()) {
			ItemStack originalStack = slot.getStack();
			newStack = originalStack.copy();
			if(fromIndex < this.inventory.size()) {
				if(!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if(!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
				return ItemStack.EMPTY;
			}

			if(originalStack.isEmpty()) {
				slot.setStack(ItemStack.EMPTY);
			} else {
				slot.markDirty();
			}
		}

		return newStack;
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return this.inventory.canPlayerUse(player);
	}
}
