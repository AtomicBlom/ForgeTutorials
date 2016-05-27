package com.github.atomicblom.forge.rendering;

 import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
 import net.minecraft.item.Item;
 import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

@SuppressWarnings("WeakerAccess")
@GameRegistry.ObjectHolder(Resources.MODID)
public final class BlockLibrary {

    public static final Block atomicPedestal = null;

    static void registerBlocks() {
        registerBlockAndItem(new Block(Material.GROUND), Resources.Blocks.AtomicPedestal, CreativeTabs.BUILDING_BLOCKS);
    }

    private static void registerBlockAndItem(Block block, ResourceLocation name, CreativeTabs tab) {
        block.setRegistryName(name)
                .setCreativeTab(tab)
                .setUnlocalizedName(Localization.getUnlocalizedNameFor(block));

        Item blockItem = new ItemBlock(block)
                .setRegistryName(name)
                .setCreativeTab(tab)
                .setUnlocalizedName(block.getUnlocalizedName());

        GameRegistry.register(block);
        GameRegistry.register(blockItem);
    }
}
