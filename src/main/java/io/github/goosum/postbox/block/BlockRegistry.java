package io.github.goosum.postbox.block;


import io.github.goosum.postbox.block.block_entity.PostboxBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.block.entity.api.QuiltBlockEntityTypeBuilder;


public class BlockRegistry {

	public static final Block POSTBOX = new PostboxBlock(PostboxBlock.defaultSettings());
	static Item postBoxItem = new BlockItem(POSTBOX, new Item.Settings());


	public static void init(ModContainer mod) {
		Registry.register(Registries.BLOCK, new Identifier(mod.metadata().id(), "postbox"), BlockRegistry.POSTBOX);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "postbox"), postBoxItem);
		PostboxBlockEntity.POSTBOX_BLOCK_ENTITY =
				Registry.register(
						Registries.BLOCK_ENTITY_TYPE,
						new Identifier(mod.metadata().id(), "postbox_block_entity"),
						QuiltBlockEntityTypeBuilder.create(PostboxBlockEntity::new, BlockRegistry.POSTBOX).build()
				);
	}


}
