package io.github.goosum.postbox.entity.renderer;

import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.goosum.postbox.entity.MailCartEntity;
import io.github.goosum.postbox.entity.model.MailCartModel;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Axis;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class MailCartEntityRenderer extends EntityRenderer<MailCartEntity> {

	private final EntityModel<MailCartEntity> model;
	private final BlockRenderManager blockRenderManager;


	public MailCartEntityRenderer(EntityRendererFactory.Context ctx) {
		super(ctx);
		this.shadowRadius = 0.7f;
		this.model = new MailCartModel<>(ctx.getPart(RendererRegistry.MAIL_CART_MODEL_LAYER));
		this.blockRenderManager = ctx.getBlockRenderManager();
	}

	@Override
	public Identifier getTexture(MailCartEntity entity) {
		return new Identifier("postbox", "textures/entity/mail_cart_texture.png");
	}

	@Override
	public void render(MailCartEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
		super.render(entity, yaw, yaw, matrices, vertexConsumers, light);
		matrices.push();
		long l = (long)entity.getId() * 493286711L;
		l = l * l * 4392167121L + l * 98761L;
		float h = (((float)(l >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
		float j = (((float)(l >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
		float k = (((float)(l >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
		matrices.translate(h, j, k);
		double d = MathHelper.lerp((double)yaw, entity.lastRenderX, entity.getX());
		double e = MathHelper.lerp((double)yaw, entity.lastRenderY, entity.getY());
		double m = MathHelper.lerp((double)yaw, entity.lastRenderZ, entity.getZ());
		double n = 0.30000001192092896;
		Vec3d vec3d = entity.snapPositionToRail(d, e, m);
		float o = MathHelper.lerp(yaw, entity.prevPitch, entity.getPitch());
		if (vec3d != null) {
			Vec3d vec3d2 = entity.snapPositionToRailWithOffset(d, e, m, 0.30000001192092896);
			Vec3d vec3d3 = entity.snapPositionToRailWithOffset(d, e, m, -0.30000001192092896);
			if (vec3d2 == null) {
				vec3d2 = vec3d;
			}

			if (vec3d3 == null) {
				vec3d3 = vec3d;
			}

			matrices.translate(vec3d.x - d, (vec3d2.y + vec3d3.y) / 2.0 - e, vec3d.z - m);
			Vec3d vec3d4 = vec3d3.add(-vec3d2.x, -vec3d2.y, -vec3d2.z);
			if (vec3d4.length() != 0.0) {
				vec3d4 = vec3d4.normalize();
				yaw = (float)(Math.atan2(vec3d4.z, vec3d4.x) * 180.0 / Math.PI);
				o = (float)(Math.atan(vec3d4.y) * 73.0);
			}
		}

		matrices.translate(0.0F, 0.375F, 0.0F);
		matrices.multiply(Axis.Y_POSITIVE.rotationDegrees(180.0F - yaw));
		matrices.multiply(Axis.Z_POSITIVE.rotationDegrees(-o));
		float p = (float)entity.getDamageWobbleTicks() - yaw;
		float q = entity.getDamageWobbleStrength() - yaw;
		if (q < 0.0F) {
			q = 0.0F;
		}

		if (p > 0.0F) {
			matrices.multiply(Axis.X_POSITIVE.rotationDegrees(MathHelper.sin(p) * p * q / 10.0F * (float)entity.getDamageWobbleSide()));
		}

		int r = entity.getBlockOffset();
		BlockState blockState = entity.getContainedBlock();
		if (blockState.getRenderType() != BlockRenderType.INVISIBLE) {
			matrices.push();
			float s = 0.75F;
			matrices.scale(0.75F, 0.75F, 0.75F);
			matrices.translate(-0.5F, (float)(r - 8) / 16.0F, 0.5F);
			matrices.multiply(Axis.Y_POSITIVE.rotationDegrees(90.0F));
			this.renderBlock(entity, yaw, blockState, matrices, vertexConsumers, light);
			matrices.pop();
		}

		matrices.scale(-1.0F, -1.0F, 1.0F);
		this.model.setAngles(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
		VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.model.getLayer(this.getTexture(entity)));
		this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
		matrices.pop();
	}

	private void renderBlock(MailCartEntity entity, float yaw, BlockState blockState, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
		this.blockRenderManager.renderBlockAsEntity(blockState, matrices, vertexConsumers, light, OverlayTexture.DEFAULT_UV);
	}
}
