package io.github.goosum.postbox.screen;

import com.mojang.blaze3d.platform.InputUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.goosum.postbox.block.block_entity.PostboxBlockEntity;
import io.github.goosum.postbox.networking.PostboxPackets;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockStateRaycastContext;
import net.minecraft.world.RaycastContext;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking;

public class PostboxScreen extends HandledScreen<PostboxScreenHandler> {

	private static final Identifier TEXTURE = new Identifier("postbox", "textures/gui/postbox_gui.png");
	private TextFieldWidget nameBar;


	public PostboxScreen(PostboxScreenHandler handler, PlayerInventory inventory, Text title) {
		super(handler, inventory, title);
	}

	@Override
	protected void drawBackground(GuiGraphics graphics, float delta, int mouseX, int mouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0f);
		RenderSystem.setShaderTexture(0, TEXTURE);
		int x = (width - backgroundWidth) / 2;
		int y = (height - backgroundHeight) / 2;
		graphics.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
	}

	@Override
	public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
		renderBackground(graphics);
		super.render(graphics, mouseX, mouseY, delta);
		drawMouseoverTooltip(graphics, mouseX, mouseY);
	}

	@Override
	protected void init() {
		super.init();
		setupNameTextField();
		title.setStyle(Style.EMPTY.withColor(13027014));
	}

	private void setupNameTextField() {
		this.nameBar = new TextFieldWidget(textRenderer, this.x + 35, this.y + 11, 101, 10, title) {
			@Override
			public boolean mouseClicked(double mouseX, double mouseY, int button) {
				if(mouseX < this.getX() || mouseX > (this.getX() + 10) || mouseY < this.getY() || (mouseY > this.getY() + 101)) {
					this.setFocused(false);
				}
				return super.mouseClicked(mouseX, mouseY, button);
			}
		};
		this.nameBar.setText(title.getString());
		addDrawableChild(nameBar);
	}

	@Override
	public void closeScreen() {
		rename();
		super.closeScreen();
	}

	private void rename() {
		String nameBarText = nameBar.getText();
		PacketByteBuf bufName = PacketByteBufs.create();

		//PacketByteBuf bufPos = PacketByteBufs.create();

		HitResult postboxHit = MinecraftClient.getInstance().crosshairTarget;
		assert postboxHit != null;
		if(postboxHit.getType() == HitResult.Type.BLOCK) {
			BlockHitResult blockHitResult = ((BlockHitResult) postboxHit);
			BlockPos postboxPos = blockHitResult.getBlockPos();
			nameBarText = nameBarText + postboxPos.toString();
		}
		bufName.writeString(nameBarText);
		ClientPlayNetworking.send(PostboxPackets.POSTBOX_NAME, bufName);
	}


	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if(keyCode == 256) {
			this.closeScreen();
		}

		return !this.nameBar.keyPressed(keyCode, scanCode, modifiers) && !this.nameBar.isActive() ? super.keyPressed(keyCode, scanCode, modifiers) : true;
	}
}
