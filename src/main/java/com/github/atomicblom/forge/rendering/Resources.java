package com.github.atomicblom.forge.rendering;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;

public class Resources {
    public static final String MODID = "forgerendering";
    public static final String VERSION = "1.0";

    public static final char MOD_TAB = new CreativeTabs("forgerendering").;

    public static final class Blocks {
        public static final ResourceLocation AtomicPedestal = new ResourceLocation(MODID, "atomicPedestal");
    }
}
