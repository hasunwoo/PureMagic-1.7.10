package hasun.puremagic;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import hasun.puremagic.blocks.blockPureEssence;
import hasun.puremagic.blocks.blockPureEssenceTube;
import hasun.puremagic.client.rendererRegistery;
import hasun.puremagic.commands.cheatSetEssenceLevel;
import hasun.puremagic.commands.cheatSetMaxEssence;
import hasun.puremagic.commands.cheatSetTier;
import hasun.puremagic.items.Crystals.FunctionalCrystalEssenceDetection;
import hasun.puremagic.items.Crystals.FunctionalCrystalSaturation;
import hasun.puremagic.items.playerEssenceStorageUpgrade.EssenceStorageUpgradeTier1;
import hasun.puremagic.liquid.FluidPureEssence;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import org.apache.logging.log4j.Logger;

@Mod(modid = "PureMagic", name = "PureMagic", version = "0.1")
public class PureMagicMain {
    @Mod.Instance("PureMagic")
    public static PureMagicMain instance;
    public static Logger logger = null;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT) {
            rendererRegistery.register(event);
        }
        Fluid LiquidPureEssence = new FluidPureEssence("Pure Essence");
        FluidRegistry.registerFluid(LiquidPureEssence);
        Item functionalCrystalSaturation = new FunctionalCrystalSaturation(10);
        GameRegistry.registerItem(functionalCrystalSaturation, "FunctionalCrystalSaturation");
        Item functionalCrystalEssenceDetection = new FunctionalCrystalEssenceDetection(0);
        GameRegistry.registerItem(functionalCrystalEssenceDetection, "FunctionalCrystalEssenceDetection");
        Item EssenceStorageUpTier1 = new EssenceStorageUpgradeTier1(50000);
        GameRegistry.registerItem(EssenceStorageUpTier1, "EssenceStorageUpgradeTier1");
        Block BlockLiquidPE = new blockPureEssence(LiquidPureEssence);
        GameRegistry.registerBlock(BlockLiquidPE, "blockPureEssence");
        Block BlockPureEssenceTube = new blockPureEssenceTube();
        GameRegistry.registerBlock(BlockPureEssenceTube, "blockPureEssenceTube");
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }

    @Mod.EventHandler
    public void serverstarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new cheatSetEssenceLevel());
        event.registerServerCommand(new cheatSetMaxEssence());
        event.registerServerCommand(new cheatSetTier());
    }
}
