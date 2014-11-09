package hasun.puremagic;

import hasun.puremagic.commands.cheatSetEssenceLevel;
import hasun.puremagic.commands.cheatSetMaxEssence;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import hasun.puremagic.handlers.SigilRepairHandler;
import hasun.puremagic.items.sigils.SigilEssenceDetection;
import hasun.puremagic.items.sigils.SigilSaturation;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Logger;

@Mod(modid = "PureMagic-hasun", version = "0.1")
public class PureMagicMain {
    @Mod.Instance("PureMagic-hasun")
    public static PureMagicMain instance;
    public static Logger logger = null;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        Item saturationsigil = new SigilSaturation(1);
        GameRegistry.registerItem(saturationsigil, "Sigil_of_saturation");
        Item detectionsigil = new SigilEssenceDetection(0);
        GameRegistry.registerItem(detectionsigil, "Sigil_of_essenceDetection");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new SigilRepairHandler());
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
