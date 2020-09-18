package io.github.jonthesquirrel.tutorial.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class Oven extends Block {

    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(1, 10, 1, 15, 11, 15),
            Block.makeCuboidShape(0, 0, 0, 2, 2, 2),
            Block.makeCuboidShape(0, 0, 14, 2, 2, 16),
            Block.makeCuboidShape(14, 0, 0, 16, 2, 2),
            Block.makeCuboidShape(14, 0, 14, 16, 2, 16),
            Block.makeCuboidShape(3, 3, 3, 13, 10, 13),
            Block.makeCuboidShape(13, 3, 2, 14, 10, 14),
            Block.makeCuboidShape(2, 3, 2, 3, 10, 14),
            Block.makeCuboidShape(11, 3, 2, 12, 10, 3),
            Block.makeCuboidShape(3, 3, 13, 13, 10, 14),
            Block.makeCuboidShape(9, 3, 2, 10, 10, 3),
            Block.makeCuboidShape(6, 3, 2, 7, 10, 3),
            Block.makeCuboidShape(4, 3, 2, 5, 10, 3),
            Block.makeCuboidShape(1, 2, 1, 15, 3, 15),
            Block.makeCuboidShape(6, 5, 12, 10, 16, 16)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(1, 10, 1, 15, 11, 15),
            Block.makeCuboidShape(14, 0, 0, 16, 2, 2),
            Block.makeCuboidShape(0, 0, 0, 2, 2, 2),
            Block.makeCuboidShape(14, 0, 14, 16, 2, 16),
            Block.makeCuboidShape(0, 0, 14, 2, 2, 16),
            Block.makeCuboidShape(3, 3, 3, 13, 10, 13),
            Block.makeCuboidShape(2, 3, 13, 14, 10, 14),
            Block.makeCuboidShape(2, 3, 2, 14, 10, 3),
            Block.makeCuboidShape(13, 3, 11, 14, 10, 12),
            Block.makeCuboidShape(2, 3, 3, 3, 10, 13),
            Block.makeCuboidShape(13, 3, 9, 14, 10, 10),
            Block.makeCuboidShape(13, 3, 6, 14, 10, 7),
            Block.makeCuboidShape(13, 3, 4, 14, 10, 5),
            Block.makeCuboidShape(1, 2, 1, 15, 3, 15),
            Block.makeCuboidShape(0, 5, 6, 4, 16, 10)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(1, 10, 1, 15, 11, 15),
            Block.makeCuboidShape(14, 0, 14, 16, 2, 16),
            Block.makeCuboidShape(14, 0, 0, 16, 2, 2),
            Block.makeCuboidShape(0, 0, 14, 2, 2, 16),
            Block.makeCuboidShape(0, 0, 0, 2, 2, 2),
            Block.makeCuboidShape(3, 3, 3, 13, 10, 13),
            Block.makeCuboidShape(2, 3, 2, 3, 10, 14),
            Block.makeCuboidShape(13, 3, 2, 14, 10, 14),
            Block.makeCuboidShape(4, 3, 13, 5, 10, 14),
            Block.makeCuboidShape(3, 3, 2, 13, 10, 3),
            Block.makeCuboidShape(6, 3, 13, 7, 10, 14),
            Block.makeCuboidShape(9, 3, 13, 10, 10, 14),
            Block.makeCuboidShape(11, 3, 13, 12, 10, 14),
            Block.makeCuboidShape(1, 2, 1, 15, 3, 15),
            Block.makeCuboidShape(6, 5, 0, 10, 16, 4)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(1, 10, 1, 15, 11, 15),
            Block.makeCuboidShape(0, 0, 14, 2, 2, 16),
            Block.makeCuboidShape(14, 0, 14, 16, 2, 16),
            Block.makeCuboidShape(0, 0, 0, 2, 2, 2),
            Block.makeCuboidShape(14, 0, 0, 16, 2, 2),
            Block.makeCuboidShape(3, 3, 3, 13, 10, 13),
            Block.makeCuboidShape(2, 3, 2, 14, 10, 3),
            Block.makeCuboidShape(2, 3, 13, 14, 10, 14),
            Block.makeCuboidShape(2, 3, 4, 3, 10, 5),
            Block.makeCuboidShape(13, 3, 3, 14, 10, 13),
            Block.makeCuboidShape(2, 3, 6, 3, 10, 7),
            Block.makeCuboidShape(2, 3, 9, 3, 10, 10),
            Block.makeCuboidShape(2, 3, 11, 3, 10, 12),
            Block.makeCuboidShape(1, 2, 1, 15, 3, 15),
            Block.makeCuboidShape(12, 5, 6, 16, 16, 10)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    public Oven() {
        super(Block.Properties.create(Material.IRON)
                .hardnessAndResistance(3.5f, 4.0f)
                .sound(SoundType.ANVIL)
                .harvestLevel(0)
                .harvestTool(ToolType.PICKAXE)
                .setRequiresTool()
        );
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case NORTH:
                return SHAPE_N;
            case EAST:
                return SHAPE_E;
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 0.6f;
    }
}
