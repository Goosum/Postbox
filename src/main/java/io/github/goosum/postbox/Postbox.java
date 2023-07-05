package io.github.goosum.postbox;

import io.github.goosum.postbox.item.ItemRegistry;
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
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "sealed_envelope"), ItemRegistry.SEALED_ENVELOPE);
		Registry.register(Registries.ITEM_GROUP, new Identifier(mod.metadata().id(), "postbox"),
				FabricItemGroup.builder()
						.name(Text.literal("Postbox"))
						.icon(() -> new ItemStack(ItemRegistry.SEALED_ENVELOPE))
						.entries((pDisplayParameters, pStackCollector) -> pStackCollector.addStack(ItemRegistry.SEALED_ENVELOPE.getDefaultStack()))
						.build());
	}
}
