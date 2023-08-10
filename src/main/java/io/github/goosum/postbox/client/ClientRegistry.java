package io.github.goosum.postbox.client;

import io.github.goosum.postbox.item.EnvelopeItem;
import io.github.goosum.postbox.item.ItemRegistry;
import io.github.goosum.postbox.screen.EnvelopeScreen;
import io.github.goosum.postbox.screen.PostboxScreen;
import io.github.goosum.postbox.screen.ScreenRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public class ClientRegistry implements ClientModInitializer {


	@Override
	public void onInitializeClient(ModContainer mod) {
		HandledScreens.register(ScreenRegistry.POSTBOX_SCREEN_HANDLER, PostboxScreen::new);
		HandledScreens.register(ScreenRegistry.ENVELOPE_SCREEN_HANDLER, EnvelopeScreen::new);

		ModelPredicateProviderRegistry.register(ItemRegistry.ENVELOPE, new Identifier("sealed"), (itemStack, clientWorld, livingEntity, i) -> {
			if(EnvelopeItem.isSealed(itemStack)) {
				return 0.0F;
			}
			return 1.0F;
		});
	}
}
