package io.github.goosum.postbox.item;

import io.github.goosum.postbox.screen.EnvelopeScreenHandler;
import io.github.goosum.postbox.util.ImplementedInventory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class EnvelopeItem extends Item implements ImplementedInventory {

	public EnvelopeItem(Settings settings) {
		super(settings);
	}

	private DefaultedList<ItemStack> sealedItem;

	public boolean isSealed(EnvelopeItem envelope) {
		if(!envelope.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}



	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if(!world.isClient() && hand == Hand.MAIN_HAND) {
			user.openHandledScreen(new ExtendedScreenHandlerFactory() {
				@Override
				public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
					buf.writeItemStack(user.getStackInHand(hand));
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

}
