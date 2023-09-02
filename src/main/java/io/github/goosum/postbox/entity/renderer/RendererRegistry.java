package io.github.goosum.postbox.entity.renderer;

import io.github.goosum.postbox.entity.EntityRegistry;
import io.github.goosum.postbox.entity.model.MailCartModel;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class RendererRegistry {

	public static final EntityModelLayer MAIL_CART_MODEL_LAYER = new EntityModelLayer(new Identifier("postbox", "mail_cart"), "main");

	public static void init() {
		EntityRendererRegistry.register(EntityRegistry.MAIL_CART, (MailCartEntityRenderer::new));

		EntityModelLayerRegistry.registerModelLayer(MAIL_CART_MODEL_LAYER, MailCartModel::getTexturedModelData);
	}

}
