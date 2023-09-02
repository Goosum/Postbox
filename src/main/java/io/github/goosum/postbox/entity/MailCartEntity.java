package io.github.goosum.postbox.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.world.World;

public class MailCartEntity extends MinecartEntity {

	public MailCartEntity(EntityType<?> entityType, World world) {
		super(entityType, world);
	}
}
