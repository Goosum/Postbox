package io.github.goosum.postbox.client;

import io.github.goosum.postbox.screen.PostboxScreen;
import io.github.goosum.postbox.screen.ScreenRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class ClientRegistry implements ClientModInitializer {


	@Override
	public void onInitializeClient(ModContainer mod) {
		HandledScreens.register(ScreenRegistry.POSTBOX_SCREEN_HANDLER, PostboxScreen::new);
	}
}
