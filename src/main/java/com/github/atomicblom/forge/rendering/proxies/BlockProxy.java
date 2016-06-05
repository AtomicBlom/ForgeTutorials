package com.github.atomicblom.forge.rendering.proxies;

import com.github.atomicblom.forge.rendering.Localization;
import com.github.atomicblom.forge.rendering.Resources;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockProxy
{
    public void registerBlocks() {
        registerBlockAndItem(new Block(Material.GROUND), Resources.Blocks.AtomicPedestal);
    }

    private void registerBlockAndItem(Block block, ResourceLocation name) {
        GameRegistry.register(configureBlock(block, name));
        GameRegistry.register(configureItemBlock(new ItemBlock(block)));
    }

    protected Block configureBlock(Block block, ResourceLocation name) {
        return block.setRegistryName(name)
                .setCreativeTab(Resources.CreativeTab)
                .setUnlocalizedName(Localization.getUnlocalizedNameFor(block));
    }

    protected Item configureItemBlock(ItemBlock itemBlock)
    {
        return itemBlock
                .setRegistryName(itemBlock.block.getRegistryName())
                .setCreativeTab(Resources.CreativeTab)
                .setUnlocalizedName(itemBlock.block.getUnlocalizedName());
    }
}
