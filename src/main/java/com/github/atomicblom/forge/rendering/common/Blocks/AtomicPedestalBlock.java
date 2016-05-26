package com.github.atomicblom.forge.rendering.common.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class AtomicPedestalBlock extends Block {
    public AtomicPedestalBlock() {
        super(Material.GROUND);
    }

    @Override
    public String getUnlocalizedName() {

        return "tile." + getRegistryName();
    }
}
