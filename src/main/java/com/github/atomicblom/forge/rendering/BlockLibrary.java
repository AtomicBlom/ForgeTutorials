package com.github.atomicblom.forge.rendering;

import com.github.atomicblom.forge.rendering.common.Blocks.AtomicPedestalBlock;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

@SuppressWarnings("WeakerAccess")
@GameRegistry.ObjectHolder(Resources.MODID)
public final class BlockLibrary {

    public static final Block atomicPedestal = null;

    static void registerBlocks() {
        Block atomicPedestal = new AtomicPedestalBlock()
                .setRegistryName(Resources.Blocks.AtomicPedestal)
                .setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        GameRegistry.register(atomicPedestal);
        GameRegistry.register(new ItemBlock(atomicPedestal).setRegistryName(Resources.Blocks.AtomicPedestal).setCreativeTab(CreativeTabs.BUILDING_BLOCKS));
    }
}
