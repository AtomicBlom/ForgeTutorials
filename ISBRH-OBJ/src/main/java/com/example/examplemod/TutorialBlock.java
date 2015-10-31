package com.example.examplemod;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class TutorialBlock extends ISBRHRenderedBlock {

    public TutorialBlock() {
        super(Material.iron);
    }

    @Override
    protected ISimpleBlockRenderingHandler getHandler() {
        return TutorialBlockRenderer.getInstance();
    }
}

