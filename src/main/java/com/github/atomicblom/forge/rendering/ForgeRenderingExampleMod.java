package com.github.atomicblom.forge.rendering;

import com.github.atomicblom.forge.rendering.proxies.BlockProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Resources.MODID, version = Resources.VERSION)
public class ForgeRenderingExampleMod
{
    @SidedProxy(
            modId = Resources.MODID,
            clientSide = "com.github.atomicblom.forge.rendering.proxies.ClientBlockProxy",
            serverSide = "com.github.atomicblom.forge.rendering.proxies.ServerBlockProxy")
    public static BlockProxy BLOCK_PROXY;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        BLOCK_PROXY.registerBlocks();
    }
}
