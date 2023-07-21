package io.github.goosum.postbox.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

public class ItemRegistry {
	public static final Item ENVELOPE = new EnvelopeItem(new QuiltItemSettings().maxCount(1));
	public static final Item STAMP = new Item(new QuiltItemSettings());

	public static void init(ModContainer mod) {
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "sealed_envelope"), ItemRegistry.ENVELOPE);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "stamp"), ItemRegistry.STAMP);
	}

}
