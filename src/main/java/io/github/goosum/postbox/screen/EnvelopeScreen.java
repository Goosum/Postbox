package io.github.goosum.postbox.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.goosum.postbox.networking.PostboxPackets;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking;

public class EnvelopeScreen extends HandledScreen<EnvelopeScreenHandler> {

	private static final Identifier TEXTURE = new Identifier("postbox", "textures/gui/envelope_gui.png");

	private TextFieldWidget addressBar;
	private TextFieldWidget senderBar;




	public EnvelopeScreen(EnvelopeScreenHandler handler, PlayerInventory inventory, Text title) {
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
		titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;

		setupTextFields();
	}

	private void setupTextFields() {
		EnvelopeScreenHandler screenHandler = this.getScreenHandler();
		addressBar = new TextFieldWidget(textRenderer, this.x + 37, this.y + 16, 101, 10, Text.literal(screenHandler.getAddress())) {
			@Override
			public boolean mouseClicked(double mouseX, double mouseY, int button) {
				if(mouseX < this.getX() || mouseX > (this.getX() + 10) || mouseY < this.getY() || (mouseY > this.getY() + 101)) {
					this.setFocused(false);
				}
				return super.mouseClicked(mouseX, mouseY, button);
			}
		};
		senderBar = new TextFieldWidget(textRenderer, this.x + 37, this.y + 63, 101, 10, Text.literal(screenHandler.getSender())) {
			@Override
			public boolean mouseClicked(double mouseX, double mouseY, int button) {
				if(mouseX < this.getX() || mouseX > (this.getX() + 10) || mouseY < this.getY() || (mouseY > this.getY() + 101)) {
					this.setFocused(false);
				}
				return super.mouseClicked(mouseX, mouseY, button);
			}
		};
		this.addressBar.setText(screenHandler.getAddress());
		this.addressBar.setFocused(false);
		this.senderBar.setFocused(false);
		this.senderBar.setText(screenHandler.getSender());
		addDrawableChild(addressBar);
		addDrawableChild(senderBar);
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if(keyCode == 256) {
			this.closeScreen();
		}

		return (!this.addressBar.keyPressed(keyCode, scanCode, modifiers) && !this.senderBar.keyPressed(keyCode, scanCode, modifiers)) && (!this.addressBar.isActive() && !this.senderBar.isActive()) ? super.keyPressed(keyCode, scanCode, modifiers) : true;
	}

	@Override
	public void closeScreen() {
		saveInfo();
		super.closeScreen();
	}

	private void saveInfo() {
		PacketByteBuf buf = PacketByteBufs.create();
		NbtCompound tag = new NbtCompound();

		String address = addressBar.getText();
		String sender = senderBar.getText();

		tag.putString("address", address);
		tag.putString("sender", sender);

		buf.writeNbt(tag);

		ClientPlayNetworking.send(PostboxPackets.ENVELOPE_INFO, buf);
	}
}
