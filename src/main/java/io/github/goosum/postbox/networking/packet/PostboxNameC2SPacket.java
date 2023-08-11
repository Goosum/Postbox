package io.github.goosum.postbox.networking.packet;

import io.github.goosum.postbox.block.PostboxBlock;
import io.github.goosum.postbox.block.block_entity.PostboxBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.quiltmc.qsl.networking.api.PacketSender;

import java.util.Objects;

public class PostboxNameC2SPacket {

	public static void register(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler networkHandler, PacketByteBuf buf, PacketSender response) {
			String received = buf.readString();

			int dividingLine = received.lastIndexOf("Mutable");

			String pos = received.substring(dividingLine);
			String newName = received.substring(0, dividingLine);

			int x = Integer.parseInt(pos.substring(10, 12));
			int y = Integer.parseInt(pos.substring(16, 18));
			int z = Integer.parseInt(pos.substring(22, 24));

			BlockPos postboxPos = new BlockPos(x, y, z);

			server.execute(() -> {
				Block block = player.getWorld().getBlockState(postboxPos).getBlock();
				if(block instanceof PostboxBlock postboxBlock) {
					BlockEntity blockEntity = server.getOverworld().getBlockEntity(postboxPos);
					if (blockEntity instanceof PostboxBlockEntity postboxBlockEntity) {
						postboxBlockEntity.setName(newName);
					}
				}
			});
		}
	}

