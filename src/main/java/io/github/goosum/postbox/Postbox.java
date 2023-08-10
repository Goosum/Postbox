package io.github.goosum.postbox;

import io.github.goosum.postbox.block.BlockRegistry;
import io.github.goosum.postbox.item.ItemRegistry;
import io.github.goosum.postbox.screen.ScreenRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Postbox implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Postbox");

	@Override
	public void onInitialize(ModContainer mod) {
		ItemRegistry.init(mod);
		BlockRegistry.init(mod);
		ScreenRegistry.init(mod);

		Registry.register(Registries.ITEM_GROUP, new Identifier(mod.metadata().id(), "postbox"),
				FabricItemGroup.builder()
						.name(Text.literal("Postbox"))
						.icon(() -> new ItemStack(BlockRegistry.POSTBOX))
						.entries((pDisplayParameters, pStackCollector) -> {
							pStackCollector.addStack(ItemRegistry.ENVELOPE.getDefaultStack());
							pStackCollector.addStack(BlockRegistry.POSTBOX.asItem().getDefaultStack());
						})
						.build());
	}
}
