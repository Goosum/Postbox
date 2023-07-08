package io.github.goosum.postbox.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;

public class ScreenRegistry {

	public static ScreenHandlerType<PostboxScreenHandler> POSTBOX_SCREEN_HANDLER;

	public static void init(ModContainer mod) {
		POSTBOX_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(mod.metadata().id(), "postbox_screen_handler"), PostboxScreenHandler::new);
	}
}
