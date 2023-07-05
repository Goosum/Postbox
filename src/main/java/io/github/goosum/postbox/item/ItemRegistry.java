package io.github.goosum.postbox.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class ItemRegistry {
	public static final Item SEALED_ENVELOPE = new Item(new QuiltItemSettings());

	public static void init(ModContainer mod) {
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "sealed_envelope"), ItemRegistry.SEALED_ENVELOPE);
	}

}
