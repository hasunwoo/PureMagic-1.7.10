package hasun.puremagic;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import hasun.puremagic.commands.cheatSetEssenceLevel;
import hasun.puremagic.commands.cheatSetMaxEssence;
import hasun.puremagic.items.Crystals.FunctionalCrystalEssenceDetection;
import hasun.puremagic.items.Crystals.FunctionalCrystalSaturation;
import net.minecraft.item.Item;
import org.apache.logging.log4j.Logger;

@Mod(modid = "PureMagic-hasun", version = "0.1")
public class PureMagicMain {
    @Mod.Instance("PureMagic-hasun")
    public static PureMagicMain instance;
    public static Logger logger = null;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Item functionalCrystalSaturation = new FunctionalCrystalSaturation(1);
        GameRegistry.registerItem(functionalCrystalSaturation, "FunctionalCrystalSaturation");
        Item functionalCrystalEssenceDetection = new FunctionalCrystalEssenceDetection(0);
        GameRegistry.registerItem(functionalCrystalEssenceDetection, "FunctionalCrystalEssenceDetection");
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }

    @Mod.EventHandler
    public void serverstarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new cheatSetEssenceLevel());
        event.registerServerCommand(new cheatSetMaxEssence());
    }
}
