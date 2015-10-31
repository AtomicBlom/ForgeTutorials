package com.example.examplemod;

import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
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
    public static Proxy render;

    @SuppressWarnings("AnonymousInnerClass")
    public static final CreativeTabs CREATIVE_TAB = new CreativeTabs(MODID.toLowerCase())
    {
        @Override
        public Item getTabIconItem()
        {
            return Item.getItemFromBlock(ModBlock.tutorialBlock);
        }
    };

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
        ModBlock.registerBlocks();
    }
}

class Reference {
    @SuppressWarnings("WeakerAccess")
    public static final String CLIENT_RENDER_PROXY_CLASS = "com.example.examplemod.ClientProxy";

    @SuppressWarnings("WeakerAccess")
    public static final String RENDER_PROXY_CLASS = "com.example.examplemod.Proxy";

    public static class BLOCKS {
        public static class TUTORIAL_BLOCK {
            public static final String NAME = "blockTutorial";
        }
    }
}

@GameRegistry.ObjectHolder(ExampleMod.MODID)
final class ModBlock {
    public static final Block tutorialBlock = new TutorialBlock();

    public static void registerBlocks() {
        GameRegistry.registerBlock(tutorialBlock, Reference.BLOCKS.TUTORIAL_BLOCK.NAME);
    }
}

class TutorialBlock extends Block {
    public TutorialBlock() {
        super(Material.iron);
        setCreativeTab(ExampleMod.CREATIVE_TAB);
        setBlockName(Reference.BLOCKS.TUTORIAL_BLOCK.NAME);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(getUnwrappedUnlocalizedName(getUnlocalizedName()));
    }

    protected static String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf('.') + 1);
    }

    @Override
    public String getUnlocalizedName()
    {
        return "tile." + ExampleMod.RESOURCE_PREFIX + getUnwrappedUnlocalizedName(super.getUnlocalizedName());
    }

}
