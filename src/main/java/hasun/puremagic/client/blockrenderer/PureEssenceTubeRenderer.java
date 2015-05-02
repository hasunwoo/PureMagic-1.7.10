package hasun.puremagic.client.blockrenderer;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class PureEssenceTubeRenderer extends TileEntitySpecialRenderer {
    float pixel = 1F / 16F;
    ResourceLocation texture = new ResourceLocation("puremagic", "textures/models/PureEssencePipe.png");

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double translationX, double translationY, double translationZ, float f) {
        GL11.glTranslated(translationX, translationY, translationZ);
        GL11.glDisable(GL11.GL_LIGHTING);
        this.bindTexture(texture);
        drawEssential();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glTranslated(-translationX, -translationY, -translationZ);
    }

    public void drawEssential() {
        //draw core of cable
        Tessellator t = Tessellator.instance;
        t.startDrawingQuads();
        t.addVertexWithUV(pixel * 6, 1 - pixel * 6, 0, pixel * 0, pixel * -1);
        t.addVertexWithUV(pixel * 9, 1 - pixel * 6, 0, pixel * 3, pixel * -1);
        t.addVertexWithUV(pixel * 6, 1 - pixel * 9, 0, pixel * 0, pixel * 2);
        t.addVertexWithUV(pixel * 9, 1 - pixel * 9, 0, pixel * 3, pixel * 2);
        t.draw();
    }
}