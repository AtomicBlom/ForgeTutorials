package com.github.atomicblom.forge.rendering;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

@SuppressWarnings("WeakerAccess")
@GameRegistry.ObjectHolder(Resources.MODID)
public final class BlockLibrary {

    public static final Block atomicPedestal = null;

    static void registerBlocks() {
        registerBlockAndItem(new Block(Material.GROUND), Resources.Blocks.AtomicPedestal);
    }

    private static void registerBlockAndItem(Block block, ResourceLocation name) {
        GameRegistry.register(configureBlock(block, name));
        GameRegistry.register(getItemForBlock(block));
    }

    private static Block configureBlock(Block block, ResourceLocation name) {
        return block.setRegistryName(name)
                .setCreativeTab(Resources.CreativeTab)
                .setUnlocalizedName(Localization.getUnlocalizedNameFor(block));
    }

    private static Item getItemForBlock(Block block) {
        return new ItemBlock(block)
                .setRegistryName(block.getRegistryName())
                .setCreativeTab(Resources.CreativeTab)
                .setUnlocalizedName(block.getUnlocalizedName());
    }
}
