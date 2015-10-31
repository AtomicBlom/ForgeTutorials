package com.example.examplemod;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.item.Item;

@Mod(modid = ExampleMod.MODID, version = ExampleMod.VERSION)
public class ExampleMod
{
    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";
    public static final String RESOURCE_PREFIX = MODID.toLowerCase() + ':';

    @SidedProxy(clientSide = Reference.CLIENT_RENDER_PROXY_CLASS, serverSide = Reference.RENDER_PROXY_CLASS)
    public static Proxy proxy;

    @SuppressWarnings("AnonymousInnerClass")
    public static final CreativeTabs CREATIVE_TAB = new CreativeTabs(MODID.toLowerCase())
    {
        @Override
        public Item getTabIconItem()
        {
            return Item.getItemFromBlock(BlockLibrary.tutorialBlock);
        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        BlockLibrary.registerBlocks();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
        proxy.init();
    }
}

