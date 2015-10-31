package com.example.examplemod.common.blocks;

import com.example.examplemod.client.rendering.TutorialBlockRenderer;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.material.Material;

public class TutorialBlock extends ISBRHRenderedBlock {

    public TutorialBlock() {
        super(Material.iron);
    }

    @Override
    protected ISimpleBlockRenderingHandler getHandler() {
        return TutorialBlockRenderer.getInstance();
    }
}

