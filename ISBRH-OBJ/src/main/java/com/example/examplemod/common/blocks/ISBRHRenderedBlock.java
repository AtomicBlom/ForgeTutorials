package com.example.examplemod.common.blocks;

import com.example.examplemod.client.RenderingUtils;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.material.Material;

public abstract class ISBRHRenderedBlock extends TutorialModBlock {

    public ISBRHRenderedBlock(Material material) {
        super(material);
    }

    protected abstract ISimpleBlockRenderingHandler getHandler();

    @Override
    public int getRenderType() {
        return getHandler().getRenderId();
    }


    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean canRenderInPass(int pass) {
        RenderingUtils.currentRenderPass = pass;
        return true;
    }

    @Override
    //Maximum number of passes for block
    public int getRenderBlockPass() {
        return 1;
    }
}
