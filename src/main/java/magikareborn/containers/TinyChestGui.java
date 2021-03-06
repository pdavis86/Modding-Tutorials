package magikareborn.containers;

import magikareborn.ModRoot;
import magikareborn.helpers.ResourceHelper;
import magikareborn.tileentities.TinyChestTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class TinyChestGui extends GuiContainer {

    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;

    private static final ResourceLocation background = ResourceHelper.getGuiResourceLocation("tinychest");
    //private static final ResourceLocation background =  new ResourceLocation(ModRoot.MODID, "textures/gui/tinychest.png");

    public TinyChestGui(TinyChestTileEntity tileEntity, TinyChestContainer container) {
        super(container);

        xSize = WIDTH;
        ySize = HEIGHT;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
