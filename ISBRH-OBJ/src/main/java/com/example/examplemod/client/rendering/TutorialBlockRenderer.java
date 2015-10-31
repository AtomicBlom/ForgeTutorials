package com.example.examplemod.client.rendering;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.util.Matrix4;
import com.example.examplemod.client.RenderingUtils;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class TutorialBlockRenderer implements ISimpleBlockRenderingHandler {

    private static TutorialBlockRenderer _instance;
    private IIcon icon;

    public static TutorialBlockRenderer getInstance() {
        if (_instance == null) {
            _instance = new TutorialBlockRenderer();
        }
        return _instance;
    }

    private WavefrontObject originalModel;
    private final int renderId;

    private TutorialBlockRenderer() {

        renderId = RenderingRegistry.getNextAvailableRenderId();
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        final Tessellator instance = Tessellator.instance;
        instance.startDrawingQuads();
        Matrix4 matrix = new Matrix4();
        matrix.translate(0.5, 0, 0.5);
        RenderingUtils.renderStaticWavefrontModel(originalModel, matrix, RenderingUtils.LightingMode.DEFAULT, false);
        instance.draw();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        if (RenderingUtils.currentRenderPass == 0) {
            Matrix4 matrix = new Matrix4();
            matrix.translate(x, y, z);
            matrix.translate(0.5, 0, 0.5);
            RenderingUtils.renderStaticWavefrontModel(x, y, z, world, originalModel, matrix, RenderingUtils.LightingMode.DEFAULT, false);
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return renderId;
    }

    public void remapTextures() {
        //This won't neccessarily work if remapping to two texture sheets.
        final String s = ExampleMod.RESOURCE_PREFIX + "models/cupola.obj";
        originalModel = (WavefrontObject) AdvancedModelLoader.loadModel(new ResourceLocation(s));
        RenderingUtils.remapUVs(originalModel.groupObjects, icon);
    }

    public void setIcon(IIcon icon) {
        this.icon = icon;
    }
}
