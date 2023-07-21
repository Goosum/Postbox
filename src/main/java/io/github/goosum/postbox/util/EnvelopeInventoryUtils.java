package io.github.goosum.postbox.util;

import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class EnvelopeInventoryUtils {

	public static NbtCompound toTag(SimpleInventory inventory) {
		NbtCompound tag = new NbtCompound();

		ItemStack itemStack = inventory.getStack(0);
		if(!itemStack.isEmpty()) {
			// puts a new nbt tag that has been filled with the item stack's info with the key "stack"
			tag.put("stack", itemStack.writeNbt(new NbtCompound()));
		}
		// returns the tag that was filled with the item stack info.
		return tag;
	}

	public static void fromTag(NbtCompound tag, SimpleInventory inventory) {
		inventory.clear();

		// reads the nbt tag into the itemstack variable. Subsequently, sets the envelope slot to that inventory.
		ItemStack itemStack = ItemStack.fromNbt(tag.getCompound("stack"));
		inventory.setStack(0, itemStack);
	}


}
