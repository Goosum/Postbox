package io.github.goosum.postbox.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.entity.api.QuiltEntityTypeBuilder;

public class EntityRegistry {

	public static final EntityType<MailCartEntity> MAIL_CART = QuiltEntityTypeBuilder.create(
			SpawnGroup.MISC, MailCartEntity::new).setDimensions(new EntityDimensions(0.98F, 0.7F, true)).maxChunkTrackingRange(10).build();

	public static void init(ModContainer mod) {
		Registry.register(Registries.ENTITY_TYPE, new Identifier(mod.metadata().id(), "mail_cart"), EntityRegistry.MAIL_CART);

	}

}
