package io.github.goosum.postbox.screen;

import com.mojang.blaze3d.platform.InputUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.goosum.postbox.block.block_entity.PostboxBlockEntity;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

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
		titleX = this.x + 35;
		titleY = this.y + 11;
	}

	private void setupNameTextField() {
		this.nameBar = new TextFieldWidget(textRenderer, this.x + 35, this.y + 11, 101, 10, title);
		this.nameBar.setText(title.getString());
		addDrawableChild(nameBar);
	}

	public String getText() {
		return nameBar.getText();
	}

	public void setText(String string) {
		nameBar.setText(string);
	}

	public boolean isNameBarActive() {
		return nameBar.isActive();
	}
}
