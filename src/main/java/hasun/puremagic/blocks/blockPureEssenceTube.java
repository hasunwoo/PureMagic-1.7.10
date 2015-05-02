package hasun.puremagic.blocks;

import hasun.puremagic.tileentity.tilePureEssenceTube;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class blockPureEssenceTube extends BlockContainer {
    public blockPureEssenceTube() {
        super(Material.iron);
        setBlockName("blockPureEssenceTube");
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new tilePureEssenceTube();
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
}
