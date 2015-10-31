package com.example.examplemod.common;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.Reference;
import com.example.examplemod.common.blocks.TutorialBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

@GameRegistry.ObjectHolder(ExampleMod.MODID)
public final class BlockLibrary {
    public static final Block tutorialBlock = new TutorialBlock();

    public static void registerBlocks() {
        GameRegistry.registerBlock(tutorialBlock, Reference.BLOCKS.TUTORIAL_BLOCK.NAME);
    }
}
