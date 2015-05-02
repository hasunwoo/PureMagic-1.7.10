package hasun.puremagic.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLStateEvent;
import hasun.puremagic.client.blockrenderer.PureEssenceTubeRenderer;
import hasun.puremagic.tileentity.tilePureEssenceTube;

public class rendererRegistery {
    public static void register(FMLStateEvent event) {
        ClientRegistry.bindTileEntitySpecialRenderer(tilePureEssenceTube.class, new PureEssenceTubeRenderer());
    }
}
