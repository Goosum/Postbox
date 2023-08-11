package io.github.goosum.postbox.networking;

import io.github.goosum.postbox.networking.packet.PostboxNameC2SPacket;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

public class PostboxPackets {

	public static final Identifier POSTBOX_NAME = new Identifier("postbox", "namepacket");

	public static void registerC2SPackets() {
		ServerPlayNetworking.registerGlobalReceiver(POSTBOX_NAME, PostboxNameC2SPacket::register);
	}



}
