package com.github.atomicblom.forge.rendering;

import com.github.atomicblom.forge.rendering.common.Blocks.AtomicPedestalBlock;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

@SuppressWarnings("WeakerAccess")
@GameRegistry.ObjectHolder(Resources.MODID)
public final class BlockLibrary {

    public static final Block atomicPedestal = null;

    static void registerBlocks() {
        registerBlockAndItem(new AtomicPedestalBlock(), Resources.Blocks.AtomicPedestal, CreativeTabs.BUILDING_BLOCKS);
    }

    private static void registerBlockAndItem(Block block, ResourceLocation name, CreativeTabs tab) {
        GameRegistry.register(block.setRegistryName(name).setCreativeTab(tab));
        GameRegistry.register(new ItemBlock(block).setRegistryName(name).setCreativeTab(tab));
    }
}
