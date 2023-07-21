package io.github.goosum.postbox.screen;

import io.github.goosum.postbox.util.EnvelopeInventoryUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class EnvelopeScreenHandler extends ScreenHandler {

	private final SimpleInventory inventory;

	public EnvelopeScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
		this(syncId, playerInventory, buf.readItemStack());
	}

	public EnvelopeScreenHandler(int syncId, PlayerInventory playerInventory, ItemStack itemStack) {
		super(ScreenRegistry.ENVELOPE_SCREEN_HANDLER, syncId);
		this.inventory = new SimpleInventory(1) {
			@Override
			public void markDirty() {
				itemStack.getOrCreateNbt().put("inventory", EnvelopeInventoryUtils.toTag(this));
				super.markDirty();
			}
		};
		checkSize(inventory, 1);

		NbtCompound tag = itemStack.getOrCreateNbt().getCompound("inventory");

		EnvelopeInventoryUtils.fromTag(tag, inventory);


		inventory.onOpen(playerInventory.player);

			this.addSlot(new Slot(this.inventory, 0, 80, 34));


		for(int r = 0; r < 3; ++r) {
			for(int c = 0; c < 9; ++c) {
				this.addSlot(new Slot(playerInventory, c + r * 9 + 9, 8 + c * 18, 84 + r * 18));
			}
		}

		for(int c = 0; c < 9; ++c) {
			this.addSlot(new Slot(playerInventory, c, 8 + c * 18, 142));
		}
	}

	@Override
	public ItemStack quickTransfer(PlayerEntity player, int fromIndex) {
		ItemStack newStack = ItemStack.EMPTY;
		Slot slot = this.slots.get(fromIndex);
		if(slot.hasStack() && slot != null) {
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
