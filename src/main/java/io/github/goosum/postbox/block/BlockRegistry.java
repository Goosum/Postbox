package io.github.goosum.postbox.block;


import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;


public class BlockRegistry {

	public static final Block POSTBOX = new PostboxBlock(PostboxBlock.defaultSettings());
	static Item postBoxItem = new BlockItem(POSTBOX, new Item.Settings());

	public static void init(ModContainer mod) {
		Registry.register(Registries.BLOCK, new Identifier(mod.metadata().id(), "postbox"), BlockRegistry.POSTBOX);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "postbox"), postBoxItem);
	}


}
