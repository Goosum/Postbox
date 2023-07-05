package io.github.goosum.postbox.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;

public class PostboxBlock extends Block {

	public PostboxBlock(Settings settings) {
		super(settings);
	}
	public static Settings defaultSettings() {
		return QuiltBlockSettings.create()
				.strength(2.0F, 3.0F)
				.sounds(BlockSoundGroup.WOOD);
	}

	private static final VoxelShape SHAPE = Block.createCuboidShape(1, 0, 1, 15, 14, 15);

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}

}
