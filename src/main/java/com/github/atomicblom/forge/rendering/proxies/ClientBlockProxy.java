package com.github.atomicblom.forge.rendering.proxies;

import com.github.atomicblom.forge.rendering.Resources;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;

public class ClientBlockProxy extends BlockProxy
{
    @Override
    protected Item configureItemBlock(ItemBlock block)
    {
        final Item item = super.configureItemBlock(block);

        ModelLoader.setCustomModelResourceLocation(
                item,
                0,
                new ModelResourceLocation(
                        block.getRegistryName(),
                        Resources.Blocks.NORMAL_VARIANT
                )
        );

        return item;
    }
}
