package hasun.puremagic.liquid;

import net.minecraftforge.fluids.Fluid;

public class FluidPureEssence extends Fluid {
    public FluidPureEssence(String fluidName) {
        super(fluidName);
        this.setDensity(2000);
        this.setViscosity(2000);
    }

    @Override
    public String getUnlocalizedName() {
        return "Pure Essence";
    }
}
