package magikareborn.init;

import magikareborn.ModRoot;
import magikareborn.base.ModFluid;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ModFluids {

    public static ModFluid MANA_FLUID = new ModFluid(
            "manafluid",
            new ResourceLocation(ModRoot.MODID, "liquid_still"),
            new ResourceLocation(ModRoot.MODID, "liquid_flowing")
    );

    @SideOnly(Side.CLIENT)
    public static void initModels() {

        System.out.println("Registering fluid models");
        //registerFluidModels(MANA_FLUID);
    }

    public static void registerFluidModels(Fluid fluid) {
        if(fluid == null) {
            return;
        }

        Block block = fluid.getBlock();
        if(block != null) {
            Item item = Item.getItemFromBlock(block);
            FluidStateMapper mapper = new FluidStateMapper(fluid);

            // item-model
            if(item != Items.AIR) {
                ModelLoader.registerItemVariants(item);
                ModelLoader.setCustomMeshDefinition(item, mapper);
            }
            // block-model
            ModelLoader.setCustomStateMapper(block, mapper);
        }
    }

    public static class FluidStateMapper extends StateMapperBase implements ItemMeshDefinition {

        public final Fluid fluid;
        public final ModelResourceLocation location;

        public FluidStateMapper(Fluid fluid) {
            this.fluid = fluid;
            this.location = new ModelResourceLocation(new ResourceLocation(ModRoot.MODID.toLowerCase(), "fluid_block"), fluid.getName().toLowerCase());
        }

        @Nonnull
        @Override
        protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
            return location;
        }

        @Nonnull
        @Override
        public ModelResourceLocation getModelLocation(@Nonnull ItemStack stack) {
            return location;
        }
    }
}
