package com.github.atomicblom.forge.rendering;

import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public class Localization {
    /**
     * creates an unlocalized name for a block.
     *
     * There is no specific reason I use block.getRegistryName as the unlocalized names are not
     * at all related to localization. This is merely a convenience, but you could have a lookup
     * or even use the name of your block class.
     *
     * In practice, is should be in this format though:
     * tile.{ModID}:{uniquelocalizationid}
     *
     * Minecraft will automatically append .name to it.
     *
     * @param block The block to create a localization name for
     * @return the localization key.
     */
    public static String getUnlocalizedNameFor(Block block) {
        ResourceLocation registryName = block.getRegistryName();
        if (registryName == null) {
            throw new RuntimeException("Attempt to get the registry name of a block that doesn't have one yet.");
        }
        return "tile." + registryName;
    }
}
