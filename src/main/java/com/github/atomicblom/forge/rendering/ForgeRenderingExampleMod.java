package com.github.atomicblom.forge.rendering;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Resources.MODID, version = Resources.VERSION)
public class ForgeRenderingExampleMod
{
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        BlockLibrary.registerBlocks();
    }
}
