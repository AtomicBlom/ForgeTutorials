package com.github.atomicblom.forge.rendering;

import com.github.atomicblom.forge.rendering.common.Blocks.AtomicPedestalBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

@SuppressWarnings("WeakerAccess")
@GameRegistry.ObjectHolder(Resources.MODID)
public final class BlockLibrary {

    public static final Block atomicPedestal = null;

    static void registerBlocks() {
        GameRegistry.register(new AtomicPedestalBlock().setRegistryName(Resources.Blocks.AtomicPedestal));
    }
}
