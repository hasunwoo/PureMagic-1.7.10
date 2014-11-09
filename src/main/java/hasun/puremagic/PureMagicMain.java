package hasun.puremagic;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import hasun.puremagic.handlers.SigilRepairHandler;
import hasun.puremagic.items.sigils.SigilSaturation;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Logger;

@Mod(modid = "PureMagic-hasun",version = "0.1")
public class PureMagicMain {
    @Mod.Instance("PureMagic-hasun")
    public static PureMagicMain instance;
    public static Logger logger = null;
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event){
        logger = event.getModLog();
        Item saturation = new SigilSaturation();
        GameRegistry.registerItem(saturation,"Sigil_of_saturation");
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(new SigilRepairHandler());
    }
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event){

    }
}
