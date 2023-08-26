package io.github.goosum.postbox.networking.packet;

import io.github.goosum.postbox.item.EnvelopeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.quiltmc.qsl.networking.api.PacketSender;

public class EnvelopeInfoC2SPacket {
	public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler networkHandler, PacketByteBuf buf,
								PacketSender response) {
		NbtCompound tag = buf.readNbt();
		if(tag != null) {
			String address = tag.getString("address");
			String sender = tag.getString("sender");


			server.execute(() -> {
				if (player.getMainHandStack().getItem() instanceof EnvelopeItem envelope) {
					envelope.setAddress(address);
					envelope.setSender(sender);
				}
			});
		}
	}
}
