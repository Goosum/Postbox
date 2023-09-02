package io.github.goosum.postbox.entity.model;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class MailCartModel<T extends Entity> extends EntityModel<T> {
	private final ModelPart bb_main;
	private final ModelPart envelope_r1;
	private final ModelPart envelope_r2;
	private final ModelPart envelope_r3;
	private final ModelPart envelope_r4;
	private final ModelPart wall4_r1;
	public MailCartModel(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
		this.envelope_r1 = root.getChild("envelope_r1");
		this.envelope_r2 = root.getChild("envelope_r2");
		this.envelope_r3 = root.getChild("envelope_r3");
		this.envelope_r4 = root.getChild("envelope_r4");
		this.wall4_r1 = root.getChild("wall4_r1");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -2.0F, -6.0F, 12.0F, 1.0F, 12.0F, new Dilation(0.0F))
				.uv(26, 39).cuboid(-6.0F, -8.0F, -5.0F, 1.0F, 6.0F, 10.0F, new Dilation(0.0F))
				.uv(38, 3).cuboid(5.0F, -8.0F, -5.0F, 1.0F, 6.0F, 10.0F, new Dilation(0.0F))
				.uv(6, 0).cuboid(3.0F, -2.0F, 6.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(5, 5).cuboid(-5.0F, -2.0F, 6.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 3).cuboid(-5.0F, -2.0F, -7.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(3.0F, -2.0F, -7.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(0, 13).cuboid(-4.75F, -11.5F, -4.75F, 9.5F, 9.5F, 9.5F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		modelPartData.addChild("envelope_r1", ModelPartBuilder.create().uv(14, 33).cuboid(-1.5F, -0.75F, 0.0F, 3.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.3787F, -11.75F, -2.3713F, 0.0F, -1.7017F, 0.0F));

		modelPartData.addChild("envelope_r2", ModelPartBuilder.create().uv(14, 38).cuboid(-1.5F, -1.75F, -2.0F, 3.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(1.6213F, -12.75F, 2.1213F, 0.0F, -0.2618F, 0.0F));

		modelPartData.addChild("envelope_r3", ModelPartBuilder.create().uv(38, 39).cuboid(-1.5F, -1.25F, -2.0F, 3.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(1.6213F, -12.25F, 2.1213F, 0.0F, -0.6545F, 0.0F));

		modelPartData.addChild("envelope_r4", ModelPartBuilder.create().uv(42, 19).cuboid(1.5F, -0.75F, -2.0F, 3.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, -11.75F, 0.0F, 0.0F, -0.7854F, 0.0F));

		modelPartData.addChild("wall4_r1", ModelPartBuilder.create().uv(28, 21).cuboid(5.0F, -3.0F, -5.5F, 1.0F, 6.0F, 12.0F, new Dilation(0.0F))
				.uv(0, 33).cuboid(-6.0F, -3.0F, -5.5F, 1.0F, 6.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, -5.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {

		bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		envelope_r1.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		envelope_r2.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		envelope_r3.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		envelope_r4.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		wall4_r1.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
}
