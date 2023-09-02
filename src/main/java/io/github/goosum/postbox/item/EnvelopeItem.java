package io.github.goosum.postbox.item;

import io.github.goosum.postbox.screen.EnvelopeScreenHandler;
import io.github.goosum.postbox.util.EnvelopeInventoryUtils;
import io.github.goosum.postbox.util.ImplementedInventory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EnvelopeItem extends Item implements ImplementedInventory {

	public EnvelopeItem(Settings settings) {
		super(settings);
	}

	private DefaultedList<ItemStack> sealedItem;

	private String address = "";
	private String sender = "";


	public static boolean isSealed(ItemStack envelope) {
		if(envelope.getItem() instanceof EnvelopeItem) {
			if (envelope.hasNbt()) {
				SimpleInventory inventory = new SimpleInventory(1);
				EnvelopeInventoryUtils.fromTag(envelope.getNbt().getCompound("inventory"), inventory);
				return !inventory.isEmpty();
			}
		}
			return false;
	}



	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if(!world.isClient() && hand == Hand.MAIN_HAND) {
			user.openHandledScreen(new ExtendedScreenHandlerFactory() {
				@Override
				public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
					ItemStack stack = user.getStackInHand(hand);
					if(stack.getItem() instanceof EnvelopeItem envelope) {
						buf.writeItemStack(stack);
						buf.writeNbt(envelope.createEnvelopeNbt());
					}
				}

				@Override
				public Text getDisplayName() {
					return Text.literal("Envelope");
				}

				@Nullable
				@Override
				public ScreenHandler createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
					return new EnvelopeScreenHandler(i, playerInventory, user.getStackInHand(hand));
				}
			});
		}

		return super.use(world, user, hand);
	}


	@Override
	public DefaultedList<ItemStack> getItems() {
		return sealedItem;
	}

	public NbtCompound createEnvelopeNbt() {
		NbtCompound tag = new NbtCompound();
		tag.putString("address", address);
		tag.putString("sender", sender);
		return tag;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
}
