package com.example.examplemod;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class TutorialBlockRenderer implements ISimpleBlockRenderingHandler {

    private static TutorialBlockRenderer _instance;

    public static TutorialBlockRenderer getInstance() {
        if (_instance == null) {
            _instance = new TutorialBlockRenderer();
        }
        return _instance;
    }

    private final WavefrontObject model;
    private final int renderId;

    private TutorialBlockRenderer() {
        final String s = ExampleMod.RESOURCE_PREFIX + "models/cupola.obj";
        model = (WavefrontObject) AdvancedModelLoader.loadModel(new ResourceLocation(s));

        renderId = RenderingRegistry.getNextAvailableRenderId();
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        //System.out.println("Inventory");
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        if (RenderingUtils.currentRenderPass == 0) {
            Matrix4 matrix = new Matrix4();
            matrix.translate(x, y, z);
            matrix.translate(0.5, 0, 0.5);
            RenderingUtils.renderStaticWavefrontModel(x, y, z, world, model, Tessellator.instance, matrix, 0, false);
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
}
