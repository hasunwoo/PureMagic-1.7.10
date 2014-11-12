package hasun.puremagic.blocks;

import hasun.puremagic.tileentity.tilePureEssenceExtractor;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class blockPureEssenceExtractor extends BlockContainer {
    protected blockPureEssenceExtractor() {
        super(Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new tilePureEssenceExtractor();
    }
}
