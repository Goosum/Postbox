package io.github.goosum.postbox.block.block_entity;

import io.github.goosum.postbox.screen.PostboxScreenHandler;
import io.github.goosum.postbox.util.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class PostboxBlockEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory {
	private final DefaultedList<ItemStack> items = DefaultedList.ofSize(6, ItemStack.EMPTY);
	private String name = "";
	public static int postboxNum = 0;

	public static ArrayList<String> names = new ArrayList<>();


	public static BlockEntityType<PostboxBlockEntity> POSTBOX_BLOCK_ENTITY;

	public PostboxBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(POSTBOX_BLOCK_ENTITY, blockPos, blockState);
		String tempName = "Postbox" + postboxNum;
		names.add(tempName);
		this.name = tempName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		return items;
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
		Inventories.readNbt(nbt, this.items);
		this.name = nbt.getString("name");
	}

	@Override
	public void writeNbt(NbtCompound nbt) {
		super.writeNbt(nbt);
		Inventories.writeNbt(nbt, this.items);
		nbt.putString("name", this.name);
	}

	@Override
	public Text getDisplayName() {
		return Text.literal(this.name);
	}

	@Nullable
	@Override
	public ScreenHandler createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
		return new PostboxScreenHandler(i, playerInventory, this);
	}



}
