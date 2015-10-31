package com.example.examplemod;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

/**
 * Created by codew on 1/11/2015.
 */
public class LightingUtils {
    public static BlockLightingInfo calculateBlockLighting(int side, IBlockAccess world, Block block, int x, int y, int z, float colR, float colG, float colB)
    {
        float f3 = 0.0F;
        float f4 = 0.0F;
        float f5 = 0.0F;
        float f6 = 0.0F;
        int l = block.getMixedBrightnessForBlock(world, x, y, z);

        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        int i1;
        float f7;

        BlockLightingInfo lightingInfo = new BlockLightingInfo();

        if(side==0)
        {
            //            if (RenderBlocks.getInstance().renderMinY <= 0.0D)
            //            {
            //                --y;
            //            }

            lightingInfo.aoBrightnessXYNN = block.getMixedBrightnessForBlock(world, x - 1, y, z);
            lightingInfo.aoBrightnessYZNN = block.getMixedBrightnessForBlock(world, x, y, z - 1);
            lightingInfo.aoBrightnessYZNP = block.getMixedBrightnessForBlock(world, x, y, z + 1);
            lightingInfo.aoBrightnessXYPN = block.getMixedBrightnessForBlock(world, x + 1, y, z);
            lightingInfo.aoLightValueScratchXYNN = world.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
            lightingInfo.aoLightValueScratchYZNN = world.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
            lightingInfo.aoLightValueScratchYZNP = world.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
            lightingInfo.aoLightValueScratchXYPN = world.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
            flag2 = world.getBlock(x + 1, y - 1, z).getCanBlockGrass();
            flag3 = world.getBlock(x - 1, y - 1, z).getCanBlockGrass();
            flag4 = world.getBlock(x, y - 1, z + 1).getCanBlockGrass();
            flag5 = world.getBlock(x, y - 1, z - 1).getCanBlockGrass();

            if (!flag5 && !flag3)
            {
                lightingInfo.aoLightValueScratchXYZNNN = lightingInfo.aoLightValueScratchXYNN;
                lightingInfo.aoBrightnessXYZNNN = lightingInfo.aoBrightnessXYNN;
            }
            else
            {
                lightingInfo.aoLightValueScratchXYZNNN = world.getBlock(x - 1, y, z - 1).getAmbientOcclusionLightValue();
                lightingInfo.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(world, x - 1, y, z - 1);
            }

            if (!flag4 && !flag3)
            {
                lightingInfo.aoLightValueScratchXYZNNP = lightingInfo.aoLightValueScratchXYNN;
                lightingInfo.aoBrightnessXYZNNP = lightingInfo.aoBrightnessXYNN;
            }
            else
            {
                lightingInfo.aoLightValueScratchXYZNNP = world.getBlock(x - 1, y, z + 1).getAmbientOcclusionLightValue();
                lightingInfo.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(world, x - 1, y, z + 1);
            }

            if (!flag5 && !flag2)
            {
                lightingInfo.aoLightValueScratchXYZPNN = lightingInfo.aoLightValueScratchXYPN;
                lightingInfo.aoBrightnessXYZPNN = lightingInfo.aoBrightnessXYPN;
            }
            else
            {
                lightingInfo.aoLightValueScratchXYZPNN = world.getBlock(x + 1, y, z - 1).getAmbientOcclusionLightValue();
                lightingInfo.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(world, x + 1, y, z - 1);
            }

            if (!flag4 && !flag2)
            {
                lightingInfo.aoLightValueScratchXYZPNP = lightingInfo.aoLightValueScratchXYPN;
                lightingInfo.aoBrightnessXYZPNP = lightingInfo.aoBrightnessXYPN;
            }
            else
            {
                lightingInfo.aoLightValueScratchXYZPNP = world.getBlock(x + 1, y, z + 1).getAmbientOcclusionLightValue();
                lightingInfo.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(world, x + 1, y, z + 1);
            }

            if (RenderBlocks.getInstance().renderMinY <= 0.0D)
            {
                ++y;
            }

            i1 = l;

            if (RenderBlocks.getInstance().renderMinY <= 0.0D || !world.getBlock(x, y - 1, z).isOpaqueCube())
            {
                i1 = block.getMixedBrightnessForBlock(world, x, y - 1, z);
            }

            f7 = world.getBlock(x, y - 1, z).getAmbientOcclusionLightValue();
            f3 = (lightingInfo.aoLightValueScratchXYZNNP + lightingInfo.aoLightValueScratchXYNN + lightingInfo.aoLightValueScratchYZNP + f7) / 4.0F;
            f6 = (lightingInfo.aoLightValueScratchYZNP + f7 + lightingInfo.aoLightValueScratchXYZPNP + lightingInfo.aoLightValueScratchXYPN) / 4.0F;
            f5 = (f7 + lightingInfo.aoLightValueScratchYZNN + lightingInfo.aoLightValueScratchXYPN + lightingInfo.aoLightValueScratchXYZPNN) / 4.0F;
            f4 = (lightingInfo.aoLightValueScratchXYNN + lightingInfo.aoLightValueScratchXYZNNN + f7 + lightingInfo.aoLightValueScratchYZNN) / 4.0F;
            lightingInfo.brightnessTopLeft = lightingInfo.getAoBrightness(lightingInfo.aoBrightnessXYZNNP, lightingInfo.aoBrightnessXYNN, lightingInfo.aoBrightnessYZNP, i1);
            lightingInfo.brightnessTopRight = lightingInfo.getAoBrightness(lightingInfo.aoBrightnessYZNP, lightingInfo.aoBrightnessXYZPNP, lightingInfo.aoBrightnessXYPN, i1);
            lightingInfo.brightnessBottomRight = lightingInfo.getAoBrightness(lightingInfo.aoBrightnessYZNN, lightingInfo.aoBrightnessXYPN, lightingInfo.aoBrightnessXYZPNN, i1);
            lightingInfo.brightnessBottomLeft = lightingInfo.getAoBrightness(lightingInfo.aoBrightnessXYNN, lightingInfo.aoBrightnessXYZNNN, lightingInfo.aoBrightnessYZNN, i1);

            lightingInfo.colorRedTopLeft = lightingInfo.colorRedBottomLeft = lightingInfo.colorRedBottomRight = lightingInfo.colorRedTopRight = 0.5F;
            lightingInfo.colorGreenTopLeft = lightingInfo.colorGreenBottomLeft = lightingInfo.colorGreenBottomRight = lightingInfo.colorGreenTopRight = 0.5F;
            lightingInfo.colorBlueTopLeft = lightingInfo.colorBlueBottomLeft = lightingInfo.colorBlueBottomRight = lightingInfo.colorBlueTopRight = 0.5F;

            lightingInfo.colorRedTopLeft *= f3;
            lightingInfo.colorGreenTopLeft *= f3;
            lightingInfo.colorBlueTopLeft *= f3;
            lightingInfo.colorRedBottomLeft *= f4;
            lightingInfo.colorGreenBottomLeft *= f4;
            lightingInfo.colorBlueBottomLeft *= f4;
            lightingInfo.colorRedBottomRight *= f5;
            lightingInfo.colorGreenBottomRight *= f5;
            lightingInfo.colorBlueBottomRight *= f5;
            lightingInfo.colorRedTopRight *= f6;
            lightingInfo.colorGreenTopRight *= f6;
            lightingInfo.colorBlueTopRight *= f6;
        }

        if(side==1)
        //			if(lightingInfo.renderAllFaces || block.shouldSideBeRendered(world, x, y + 1, z, 1))
        {
            //			if (RenderBlocks.getInstance().renderMaxY >= 1.0D)
            //			{
            //				++y;
            //			}

            lightingInfo.aoBrightnessXYNP = block.getMixedBrightnessForBlock(world, x - 1, y, z);
            lightingInfo.aoBrightnessXYPP = block.getMixedBrightnessForBlock(world, x + 1, y, z);
            lightingInfo.aoBrightnessYZPN = block.getMixedBrightnessForBlock(world, x, y, z - 1);
            lightingInfo.aoBrightnessYZPP = block.getMixedBrightnessForBlock(world, x, y, z + 1);
            lightingInfo.aoLightValueScratchXYNP = world.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
            lightingInfo.aoLightValueScratchXYPP = world.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
            lightingInfo.aoLightValueScratchYZPN = world.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
            lightingInfo.aoLightValueScratchYZPP = world.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
            flag2 = world.getBlock(x + 1, y + 1, z).getCanBlockGrass();
            flag3 = world.getBlock(x - 1, y + 1, z).getCanBlockGrass();
            flag4 = world.getBlock(x, y + 1, z + 1).getCanBlockGrass();
            flag5 = world.getBlock(x, y + 1, z - 1).getCanBlockGrass();

            if (!flag5 && !flag3)
            {
                lightingInfo.aoLightValueScratchXYZNPN = lightingInfo.aoLightValueScratchXYNP;
                lightingInfo.aoBrightnessXYZNPN = lightingInfo.aoBrightnessXYNP;
            }
            else
            {
                lightingInfo.aoLightValueScratchXYZNPN = world.getBlock(x - 1, y, z - 1).getAmbientOcclusionLightValue();
                lightingInfo.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(world, x - 1, y, z - 1);
            }

            if (!flag5 && !flag2)
            {
                lightingInfo.aoLightValueScratchXYZPPN = lightingInfo.aoLightValueScratchXYPP;
                lightingInfo.aoBrightnessXYZPPN = lightingInfo.aoBrightnessXYPP;
            }
            else
            {
                lightingInfo.aoLightValueScratchXYZPPN = world.getBlock(x + 1, y, z - 1).getAmbientOcclusionLightValue();
                lightingInfo.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(world, x + 1, y, z - 1);
            }

            if (!flag4 && !flag3)
            {
                lightingInfo.aoLightValueScratchXYZNPP = lightingInfo.aoLightValueScratchXYNP;
                lightingInfo.aoBrightnessXYZNPP = lightingInfo.aoBrightnessXYNP;
            }
            else
            {
                lightingInfo.aoLightValueScratchXYZNPP = world.getBlock(x - 1, y, z + 1).getAmbientOcclusionLightValue();
                lightingInfo.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(world, x - 1, y, z + 1);
            }

            if (!flag4 && !flag2)
            {
                lightingInfo.aoLightValueScratchXYZPPP = lightingInfo.aoLightValueScratchXYPP;
                lightingInfo.aoBrightnessXYZPPP = lightingInfo.aoBrightnessXYPP;
            }
            else
            {
                lightingInfo.aoLightValueScratchXYZPPP = world.getBlock(x + 1, y, z + 1).getAmbientOcclusionLightValue();
                lightingInfo.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(world, x + 1, y, z + 1);
            }

            if (RenderBlocks.getInstance().renderMaxY >= 1.0D)
            {
                --y;
            }

            i1 = l;

            if (RenderBlocks.getInstance().renderMaxY >= 1.0D || !world.getBlock(x, y + 1, z).isOpaqueCube())
            {
                i1 = block.getMixedBrightnessForBlock(world, x, y + 1, z);
            }

            f7 = world.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
            f6 = (lightingInfo.aoLightValueScratchXYZNPP + lightingInfo.aoLightValueScratchXYNP + lightingInfo.aoLightValueScratchYZPP + f7) / 4.0F;
            f3 = (lightingInfo.aoLightValueScratchYZPP + f7 + lightingInfo.aoLightValueScratchXYZPPP + lightingInfo.aoLightValueScratchXYPP) / 4.0F;
            f4 = (f7 + lightingInfo.aoLightValueScratchYZPN + lightingInfo.aoLightValueScratchXYPP + lightingInfo.aoLightValueScratchXYZPPN) / 4.0F;
            f5 = (lightingInfo.aoLightValueScratchXYNP + lightingInfo.aoLightValueScratchXYZNPN + f7 + lightingInfo.aoLightValueScratchYZPN) / 4.0F;
            lightingInfo.brightnessTopRight = lightingInfo.getAoBrightness(lightingInfo.aoBrightnessXYZNPP, lightingInfo.aoBrightnessXYNP, lightingInfo.aoBrightnessYZPP, i1);
            lightingInfo.brightnessTopLeft = lightingInfo.getAoBrightness(lightingInfo.aoBrightnessYZPP, lightingInfo.aoBrightnessXYZPPP, lightingInfo.aoBrightnessXYPP, i1);
            lightingInfo.brightnessBottomLeft = lightingInfo.getAoBrightness(lightingInfo.aoBrightnessYZPN, lightingInfo.aoBrightnessXYPP, lightingInfo.aoBrightnessXYZPPN, i1);
            lightingInfo.brightnessBottomRight = lightingInfo.getAoBrightness(lightingInfo.aoBrightnessXYNP, lightingInfo.aoBrightnessXYZNPN, lightingInfo.aoBrightnessYZPN, i1);
            lightingInfo.colorRedTopLeft = lightingInfo.colorRedBottomLeft = lightingInfo.colorRedBottomRight = lightingInfo.colorRedTopRight = colR;
            lightingInfo.colorGreenTopLeft = lightingInfo.colorGreenBottomLeft = lightingInfo.colorGreenBottomRight = lightingInfo.colorGreenTopRight = colG;
            lightingInfo.colorBlueTopLeft = lightingInfo.colorBlueBottomLeft = lightingInfo.colorBlueBottomRight = lightingInfo.colorBlueTopRight = colB;
            lightingInfo.colorRedTopLeft *= f3;
            lightingInfo.colorGreenTopLeft *= f3;
            lightingInfo.colorBlueTopLeft *= f3;
            lightingInfo.colorRedBottomLeft *= f4;
            lightingInfo.colorGreenBottomLeft *= f4;
            lightingInfo.colorBlueBottomLeft *= f4;
            lightingInfo.colorRedBottomRight *= f5;
            lightingInfo.colorGreenBottomRight *= f5;
            lightingInfo.colorBlueBottomRight *= f5;
            lightingInfo.colorRedTopRight *= f6;
            lightingInfo.colorGreenTopRight *= f6;
            lightingInfo.colorBlueTopRight *= f6;
        }

        float f8;
        float f9;
        float f10;
        float f11;
        int j1;
        int k1;
        int l1;
        int i2;

        if(side==2)
        //			if (lightingInfo.renderAllFaces || block.shouldSideBeRendered(world, x, y, z - 1, 2))
        {
            //			if (RenderBlocks.getInstance().renderMinZ <= 0.0D)
            //			{
            //				--z;
            //			}

            lightingInfo.aoLightValueScratchXZNN = world.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
            lightingInfo.aoLightValueScratchYZNN = world.getBlock(x, y - 1, z).getAmbientOcclusionLightValue();
            lightingInfo.aoLightValueScratchYZPN = world.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
            lightingInfo.aoLightValueScratchXZPN = world.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
            lightingInfo.aoBrightnessXZNN = block.getMixedBrightnessForBlock(world, x - 1, y, z);
            lightingInfo.aoBrightnessYZNN = block.getMixedBrightnessForBlock(world, x, y - 1, z);
            lightingInfo.aoBrightnessYZPN = block.getMixedBrightnessForBlock(world, x, y + 1, z);
            lightingInfo.aoBrightnessXZPN = block.getMixedBrightnessForBlock(world, x + 1, y, z);
            flag2 = world.getBlock(x + 1, y, z - 1).getCanBlockGrass();
            flag3 = world.getBlock(x - 1, y, z - 1).getCanBlockGrass();
            flag4 = world.getBlock(x, y + 1, z - 1).getCanBlockGrass();
            flag5 = world.getBlock(x, y - 1, z - 1).getCanBlockGrass();

            if (!flag3 && !flag5)
            {
                lightingInfo.aoLightValueScratchXYZNNN = lightingInfo.aoLightValueScratchXZNN;
                lightingInfo.aoBrightnessXYZNNN = lightingInfo.aoBrightnessXZNN;
            }
            else
            {
                lightingInfo.aoLightValueScratchXYZNNN = world.getBlock(x - 1, y - 1, z).getAmbientOcclusionLightValue();
                lightingInfo.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(world, x - 1, y - 1, z);
            }

            if (!flag3 && !flag4)
            {
                lightingInfo.aoLightValueScratchXYZNPN = lightingInfo.aoLightValueScratchXZNN;
                lightingInfo.aoBrightnessXYZNPN = lightingInfo.aoBrightnessXZNN;
            }
            else
            {
                lightingInfo.aoLightValueScratchXYZNPN = world.getBlock(x - 1, y + 1, z).getAmbientOcclusionLightValue();
                lightingInfo.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(world, x - 1, y + 1, z);
            }

            if (!flag2 && !flag5)
            {
                lightingInfo.aoLightValueScratchXYZPNN = lightingInfo.aoLightValueScratchXZPN;
                lightingInfo.aoBrightnessXYZPNN = lightingInfo.aoBrightnessXZPN;
            }
            else
            {
                lightingInfo.aoLightValueScratchXYZPNN = world.getBlock(x + 1, y - 1, z).getAmbientOcclusionLightValue();
                lightingInfo.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(world, x + 1, y - 1, z);
            }

            if (!flag2 && !flag4)
            {
                lightingInfo.aoLightValueScratchXYZPPN = lightingInfo.aoLightValueScratchXZPN;
                lightingInfo.aoBrightnessXYZPPN = lightingInfo.aoBrightnessXZPN;
            }
            else
            {
                lightingInfo.aoLightValueScratchXYZPPN = world.getBlock(x + 1, y + 1, z).getAmbientOcclusionLightValue();
                lightingInfo.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(world, x + 1, y + 1, z);
            }

            if (RenderBlocks.getInstance().renderMinZ <= 0.0D)
            {
                ++z;
            }

            i1 = l;

            if (RenderBlocks.getInstance().renderMinZ <= 0.0D || !world.getBlock(x, y, z - 1).isOpaqueCube())
            {
                i1 = block.getMixedBrightnessForBlock(world, x, y, z - 1);
            }

            f7 = world.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
            f8 = (lightingInfo.aoLightValueScratchXZNN + lightingInfo.aoLightValueScratchXYZNPN + f7 + lightingInfo.aoLightValueScratchYZPN) / 4.0F;
            f9 = (f7 + lightingInfo.aoLightValueScratchYZPN + lightingInfo.aoLightValueScratchXZPN + lightingInfo.aoLightValueScratchXYZPPN) / 4.0F;
            f10 = (lightingInfo.aoLightValueScratchYZNN + f7 + lightingInfo.aoLightValueScratchXYZPNN + lightingInfo.aoLightValueScratchXZPN) / 4.0F;
            f11 = (lightingInfo.aoLightValueScratchXYZNNN + lightingInfo.aoLightValueScratchXZNN + lightingInfo.aoLightValueScratchYZNN + f7) / 4.0F;
            f3 = (float)((double)f8 * RenderBlocks.getInstance().renderMaxY * (1.0D - RenderBlocks.getInstance().renderMinX) + (double)f9 * RenderBlocks.getInstance().renderMaxY * RenderBlocks.getInstance().renderMinX + (double)f10 * (1.0D - RenderBlocks.getInstance().renderMaxY) * RenderBlocks.getInstance().renderMinX + (double)f11 * (1.0D - RenderBlocks.getInstance().renderMaxY) * (1.0D - RenderBlocks.getInstance().renderMinX));
            f4 = (float)((double)f8 * RenderBlocks.getInstance().renderMaxY * (1.0D - RenderBlocks.getInstance().renderMaxX) + (double)f9 * RenderBlocks.getInstance().renderMaxY * RenderBlocks.getInstance().renderMaxX + (double)f10 * (1.0D - RenderBlocks.getInstance().renderMaxY) * RenderBlocks.getInstance().renderMaxX + (double)f11 * (1.0D - RenderBlocks.getInstance().renderMaxY) * (1.0D - RenderBlocks.getInstance().renderMaxX));
            f5 = (float)((double)f8 * RenderBlocks.getInstance().renderMinY * (1.0D - RenderBlocks.getInstance().renderMaxX) + (double)f9 * RenderBlocks.getInstance().renderMinY * RenderBlocks.getInstance().renderMaxX + (double)f10 * (1.0D - RenderBlocks.getInstance().renderMinY) * RenderBlocks.getInstance().renderMaxX + (double)f11 * (1.0D - RenderBlocks.getInstance().renderMinY) * (1.0D - RenderBlocks.getInstance().renderMaxX));
            f6 = (float)((double)f8 * RenderBlocks.getInstance().renderMinY * (1.0D - RenderBlocks.getInstance().renderMinX) + (double)f9 * RenderBlocks.getInstance().renderMinY * RenderBlocks.getInstance().renderMinX + (double)f10 * (1.0D - RenderBlocks.getInstance().renderMinY) * RenderBlocks.getInstance().renderMinX + (double)f11 * (1.0D - RenderBlocks.getInstance().renderMinY) * (1.0D - RenderBlocks.getInstance().renderMinX));
            j1 = lightingInfo.getAoBrightness(lightingInfo.aoBrightnessXZNN, lightingInfo.aoBrightnessXYZNPN, lightingInfo.aoBrightnessYZPN, i1);
            k1 = lightingInfo.getAoBrightness(lightingInfo.aoBrightnessYZPN, lightingInfo.aoBrightnessXZPN, lightingInfo.aoBrightnessXYZPPN, i1);
            l1 = lightingInfo.getAoBrightness(lightingInfo.aoBrightnessYZNN, lightingInfo.aoBrightnessXYZPNN, lightingInfo.aoBrightnessXZPN, i1);
            i2 = lightingInfo.getAoBrightness(lightingInfo.aoBrightnessXYZNNN, lightingInfo.aoBrightnessXZNN, lightingInfo.aoBrightnessYZNN, i1);
            lightingInfo.brightnessTopLeft = lightingInfo.mixAoBrightness(j1, k1, l1, i2, RenderBlocks.getInstance().renderMaxY * (1.0D - RenderBlocks.getInstance().renderMinX), RenderBlocks.getInstance().renderMaxY * RenderBlocks.getInstance().renderMinX, (1.0D - RenderBlocks.getInstance().renderMaxY) * RenderBlocks.getInstance().renderMinX, (1.0D - RenderBlocks.getInstance().renderMaxY) * (1.0D - RenderBlocks.getInstance().renderMinX));
            lightingInfo.brightnessBottomLeft = lightingInfo.mixAoBrightness(j1, k1, l1, i2, RenderBlocks.getInstance().renderMaxY * (1.0D - RenderBlocks.getInstance().renderMaxX), RenderBlocks.getInstance().renderMaxY * RenderBlocks.getInstance().renderMaxX, (1.0D - RenderBlocks.getInstance().renderMaxY) * RenderBlocks.getInstance().renderMaxX, (1.0D - RenderBlocks.getInstance().renderMaxY) * (1.0D - RenderBlocks.getInstance().renderMaxX));
            lightingInfo.brightnessBottomRight = lightingInfo.mixAoBrightness(j1, k1, l1, i2, RenderBlocks.getInstance().renderMinY * (1.0D - RenderBlocks.getInstance().renderMaxX), RenderBlocks.getInstance().renderMinY * RenderBlocks.getInstance().renderMaxX, (1.0D - RenderBlocks.getInstance().renderMinY) * RenderBlocks.getInstance().renderMaxX, (1.0D - RenderBlocks.getInstance().renderMinY) * (1.0D - RenderBlocks.getInstance().renderMaxX));
            lightingInfo.brightnessTopRight = lightingInfo.mixAoBrightness(j1, k1, l1, i2, RenderBlocks.getInstance().renderMinY * (1.0D - RenderBlocks.getInstance().renderMinX), RenderBlocks.getInstance().renderMinY * RenderBlocks.getInstance().renderMinX, (1.0D - RenderBlocks.getInstance().renderMinY) * RenderBlocks.getInstance().renderMinX, (1.0D - RenderBlocks.getInstance().renderMinY) * (1.0D - RenderBlocks.getInstance().renderMinX));

            lightingInfo.colorRedTopLeft = lightingInfo.colorRedBottomLeft = lightingInfo.colorRedBottomRight = lightingInfo.colorRedTopRight = 0.8F;
            lightingInfo.colorGreenTopLeft = lightingInfo.colorGreenBottomLeft = lightingInfo.colorGreenBottomRight = lightingInfo.colorGreenTopRight = 0.8F;
            lightingInfo.colorBlueTopLeft = lightingInfo.colorBlueBottomLeft = lightingInfo.colorBlueBottomRight = lightingInfo.colorBlueTopRight = 0.8F;

            lightingInfo.colorRedTopLeft *= f3;
            lightingInfo.colorGreenTopLeft *= f3;
            lightingInfo.colorBlueTopLeft *= f3;
            lightingInfo.colorRedBottomLeft *= f4;
            lightingInfo.colorGreenBottomLeft *= f4;
            lightingInfo.colorBlueBottomLeft *= f4;
            lightingInfo.colorRedBottomRight *= f5;
            lightingInfo.colorGreenBottomRight *= f5;
            lightingInfo.colorBlueBottomRight *= f5;
            lightingInfo.colorRedTopRight *= f6;
            lightingInfo.colorGreenTopRight *= f6;
            lightingInfo.colorBlueTopRight *= f6;
        }

        if(side==3)
        //		if (lightingInfo.renderAllFaces || block.shouldSideBeRendered(world, x, y, z + 1, 3))
        {
            if (RenderBlocks.getInstance().renderMaxZ >= 1.0D)
            {
                ++z;
            }

            lightingInfo.aoLightValueScratchXZNP = world.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
            lightingInfo.aoLightValueScratchXZPP = world.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
            lightingInfo.aoLightValueScratchYZNP = world.getBlock(x, y - 1, z).getAmbientOcclusionLightValue();
            lightingInfo.aoLightValueScratchYZPP = world.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
            lightingInfo.aoBrightnessXZNP = block.getMixedBrightnessForBlock(world, x - 1, y, z);
            lightingInfo.aoBrightnessXZPP = block.getMixedBrightnessForBlock(world, x + 1, y, z);
            lightingInfo.aoBrightnessYZNP = block.getMixedBrightnessForBlock(world, x, y - 1, z);
            lightingInfo.aoBrightnessYZPP = block.getMixedBrightnessForBlock(world, x, y + 1, z);
            flag2 = world.getBlock(x + 1, y, z + 1).getCanBlockGrass();
            flag3 = world.getBlock(x - 1, y, z + 1).getCanBlockGrass();
            flag4 = world.getBlock(x, y + 1, z + 1).getCanBlockGrass();
            flag5 = world.getBlock(x, y - 1, z + 1).getCanBlockGrass();

            if (!flag3 && !flag5)
            {
                lightingInfo.aoLightValueScratchXYZNNP = lightingInfo.aoLightValueScratchXZNP;
                lightingInfo.aoBrightnessXYZNNP = lightingInfo.aoBrightnessXZNP;
            }
            else
            {
                lightingInfo.aoLightValueScratchXYZNNP = world.getBlock(x - 1, y - 1, z).getAmbientOcclusionLightValue();
                lightingInfo.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(world, x - 1, y - 1, z);
            }

            if (!flag3 && !flag4)
            {
                lightingInfo.aoLightValueScratchXYZNPP = lightingInfo.aoLightValueScratchXZNP;
                lightingInfo.aoBrightnessXYZNPP = lightingInfo.aoBrightnessXZNP;
            }
            else
            {
                lightingInfo.aoLightValueScratchXYZNPP = world.getBlock(x - 1, y + 1, z).getAmbientOcclusionLightValue();
                lightingInfo.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(world, x - 1, y + 1, z);
            }

            if (!flag2 && !flag5)
            {
                lightingInfo.aoLightValueScratchXYZPNP = lightingInfo.aoLightValueScratchXZPP;
                lightingInfo.aoBrightnessXYZPNP = lightingInfo.aoBrightnessXZPP;
            }
            else
            {
                lightingInfo.aoLightValueScratchXYZPNP = world.getBlock(x + 1, y - 1, z).getAmbientOcclusionLightValue();
                lightingInfo.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(world, x + 1, y - 1, z);
            }

            if (!flag2 && !flag4)
            {
                lightingInfo.aoLightValueScratchXYZPPP = lightingInfo.aoLightValueScratchXZPP;
                lightingInfo.aoBrightnessXYZPPP = lightingInfo.aoBrightnessXZPP;
            }
            else
            {
                lightingInfo.aoLightValueScratchXYZPPP = world.getBlock(x + 1, y + 1, z).getAmbientOcclusionLightValue();
                lightingInfo.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(world, x + 1, y + 1, z);
            }

            if (RenderBlocks.getInstance().renderMaxZ >= 1.0D)
            {
                --z;
            }

            i1 = l;

            if (RenderBlocks.getInstance().renderMaxZ >= 1.0D || !world.getBlock(x, y, z + 1).isOpaqueCube())
            {
                i1 = block.getMixedBrightnessForBlock(world, x, y, z + 1);
            }

            f7 = world.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
            f8 = (lightingInfo.aoLightValueScratchXZNP + lightingInfo.aoLightValueScratchXYZNPP + f7 + lightingInfo.aoLightValueScratchYZPP) / 4.0F;
            f9 = (f7 + lightingInfo.aoLightValueScratchYZPP + lightingInfo.aoLightValueScratchXZPP + lightingInfo.aoLightValueScratchXYZPPP) / 4.0F;
            f10 = (lightingInfo.aoLightValueScratchYZNP + f7 + lightingInfo.aoLightValueScratchXYZPNP + lightingInfo.aoLightValueScratchXZPP) / 4.0F;
            f11 = (lightingInfo.aoLightValueScratchXYZNNP + lightingInfo.aoLightValueScratchXZNP + lightingInfo.aoLightValueScratchYZNP + f7) / 4.0F;
            f3 = (float)((double)f8 * RenderBlocks.getInstance().renderMaxY * (1.0D - RenderBlocks.getInstance().renderMinX) + (double)f9 * RenderBlocks.getInstance().renderMaxY * RenderBlocks.getInstance().renderMinX + (double)f10 * (1.0D - RenderBlocks.getInstance().renderMaxY) * RenderBlocks.getInstance().renderMinX + (double)f11 * (1.0D - RenderBlocks.getInstance().renderMaxY) * (1.0D - RenderBlocks.getInstance().renderMinX));
            f4 = (float)((double)f8 * RenderBlocks.getInstance().renderMinY * (1.0D - RenderBlocks.getInstance().renderMinX) + (double)f9 * RenderBlocks.getInstance().renderMinY * RenderBlocks.getInstance().renderMinX + (double)f10 * (1.0D - RenderBlocks.getInstance().renderMinY) * RenderBlocks.getInstance().renderMinX + (double)f11 * (1.0D - RenderBlocks.getInstance().renderMinY) * (1.0D - RenderBlocks.getInstance().renderMinX));
            f5 = (float)((double)f8 * RenderBlocks.getInstance().renderMinY * (1.0D - RenderBlocks.getInstance().renderMaxX) + (double)f9 * RenderBlocks.getInstance().renderMinY * RenderBlocks.getInstance().renderMaxX + (double)f10 * (1.0D - RenderBlocks.getInstance().renderMinY) * RenderBlocks.getInstance().renderMaxX + (double)f11 * (1.0D - RenderBlocks.getInstance().renderMinY) * (1.0D - RenderBlocks.getInstance().renderMaxX));
            f6 = (float)((double)f8 * RenderBlocks.getInstance().renderMaxY * (1.0D - RenderBlocks.getInstance().renderMaxX) + (double)f9 * RenderBlocks.getInstance().renderMaxY * RenderBlocks.getInstance().renderMaxX + (double)f10 * (1.0D - RenderBlocks.getInstance().renderMaxY) * RenderBlocks.getInstance().renderMaxX + (double)f11 * (1.0D - RenderBlocks.getInstance().renderMaxY) * (1.0D - RenderBlocks.getInstance().renderMaxX));
            j1 = lightingInfo.getAoBrightness(lightingInfo.aoBrightnessXZNP, lightingInfo.aoBrightnessXYZNPP, lightingInfo.aoBrightnessYZPP, i1);
            k1 = lightingInfo.getAoBrightness(lightingInfo.aoBrightnessYZPP, lightingInfo.aoBrightnessXZPP, lightingInfo.aoBrightnessXYZPPP, i1);
            l1 = lightingInfo.getAoBrightness(lightingInfo.aoBrightnessYZNP, lightingInfo.aoBrightnessXYZPNP, lightingInfo.aoBrightnessXZPP, i1);
            i2 = lightingInfo.getAoBrightness(lightingInfo.aoBrightnessXYZNNP, lightingInfo.aoBrightnessXZNP, lightingInfo.aoBrightnessYZNP, i1);
            lightingInfo.brightnessTopLeft = lightingInfo.mixAoBrightness(j1, i2, l1, k1, RenderBlocks.getInstance().renderMaxY * (1.0D - RenderBlocks.getInstance().renderMinX), (1.0D - RenderBlocks.getInstance().renderMaxY) * (1.0D - RenderBlocks.getInstance().renderMinX), (1.0D - RenderBlocks.getInstance().renderMaxY) * RenderBlocks.getInstance().renderMinX, RenderBlocks.getInstance().renderMaxY * RenderBlocks.getInstance().renderMinX);
            lightingInfo.brightnessBottomLeft = lightingInfo.mixAoBrightness(j1, i2, l1, k1, RenderBlocks.getInstance().renderMinY * (1.0D - RenderBlocks.getInstance().renderMinX), (1.0D - RenderBlocks.getInstance().renderMinY) * (1.0D - RenderBlocks.getInstance().renderMinX), (1.0D - RenderBlocks.getInstance().renderMinY) * RenderBlocks.getInstance().renderMinX, RenderBlocks.getInstance().renderMinY * RenderBlocks.getInstance().renderMinX);
            lightingInfo.brightnessBottomRight = lightingInfo.mixAoBrightness(j1, i2, l1, k1, RenderBlocks.getInstance().renderMinY * (1.0D - RenderBlocks.getInstance().renderMaxX), (1.0D - RenderBlocks.getInstance().renderMinY) * (1.0D - RenderBlocks.getInstance().renderMaxX), (1.0D - RenderBlocks.getInstance().renderMinY) * RenderBlocks.getInstance().renderMaxX, RenderBlocks.getInstance().renderMinY * RenderBlocks.getInstance().renderMaxX);
            lightingInfo.brightnessTopRight = lightingInfo.mixAoBrightness(j1, i2, l1, k1, RenderBlocks.getInstance().renderMaxY * (1.0D - RenderBlocks.getInstance().renderMaxX), (1.0D - RenderBlocks.getInstance().renderMaxY) * (1.0D - RenderBlocks.getInstance().renderMaxX), (1.0D - RenderBlocks.getInstance().renderMaxY) * RenderBlocks.getInstance().renderMaxX, RenderBlocks.getInstance().renderMaxY * RenderBlocks.getInstance().renderMaxX);

            lightingInfo.colorRedTopLeft = lightingInfo.colorRedBottomLeft = lightingInfo.colorRedBottomRight = lightingInfo.colorRedTopRight = 0.8F;
            lightingInfo.colorGreenTopLeft = lightingInfo.colorGreenBottomLeft = lightingInfo.colorGreenBottomRight = lightingInfo.colorGreenTopRight = 0.8F;
            lightingInfo.colorBlueTopLeft = lightingInfo.colorBlueBottomLeft = lightingInfo.colorBlueBottomRight = lightingInfo.colorBlueTopRight = 0.8F;

            lightingInfo.colorRedTopLeft *= f3;
            lightingInfo.colorGreenTopLeft *= f3;
            lightingInfo.colorBlueTopLeft *= f3;
            lightingInfo.colorRedBottomLeft *= f4;
            lightingInfo.colorGreenBottomLeft *= f4;
            lightingInfo.colorBlueBottomLeft *= f4;
            lightingInfo.colorRedBottomRight *= f5;
            lightingInfo.colorGreenBottomRight *= f5;
            lightingInfo.colorBlueBottomRight *= f5;
            lightingInfo.colorRedTopRight *= f6;
            lightingInfo.colorGreenTopRight *= f6;
            lightingInfo.colorBlueTopRight *= f6;
        }

        if(side==4)
        //		if (lightingInfo.renderAllFaces || block.shouldSideBeRendered(world, x - 1, y, z, 4))
        {
            if (RenderBlocks.getInstance().renderMinX <= 0.0D)
            {
                --x;
            }

            lightingInfo.aoLightValueScratchXYNN = world.getBlock(x, y - 1, z).getAmbientOcclusionLightValue();
            lightingInfo.aoLightValueScratchXZNN = world.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
            lightingInfo.aoLightValueScratchXZNP = world.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
            lightingInfo.aoLightValueScratchXYNP = world.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
            lightingInfo.aoBrightnessXYNN = block.getMixedBrightnessForBlock(world, x, y - 1, z);
            lightingInfo.aoBrightnessXZNN = block.getMixedBrightnessForBlock(world, x, y, z - 1);
            lightingInfo.aoBrightnessXZNP = block.getMixedBrightnessForBlock(world, x, y, z + 1);
            lightingInfo.aoBrightnessXYNP = block.getMixedBrightnessForBlock(world, x, y + 1, z);
            flag2 = world.getBlock(x - 1, y + 1, z).getCanBlockGrass();
            flag3 = world.getBlock(x - 1, y - 1, z).getCanBlockGrass();
            flag4 = world.getBlock(x - 1, y, z - 1).getCanBlockGrass();
            flag5 = world.getBlock(x - 1, y, z + 1).getCanBlockGrass();

            if (!flag4 && !flag3)
            {
                lightingInfo.aoLightValueScratchXYZNNN = lightingInfo.aoLightValueScratchXZNN;
                lightingInfo.aoBrightnessXYZNNN = lightingInfo.aoBrightnessXZNN;
            }
            else
            {
                lightingInfo.aoLightValueScratchXYZNNN = world.getBlock(x, y - 1, z - 1).getAmbientOcclusionLightValue();
                lightingInfo.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(world, x, y - 1, z - 1);
            }

            if (!flag5 && !flag3)
            {
                lightingInfo.aoLightValueScratchXYZNNP = lightingInfo.aoLightValueScratchXZNP;
                lightingInfo.aoBrightnessXYZNNP = lightingInfo.aoBrightnessXZNP;
            }
            else
            {
                lightingInfo.aoLightValueScratchXYZNNP = world.getBlock(x, y - 1, z + 1).getAmbientOcclusionLightValue();
                lightingInfo.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(world, x, y - 1, z + 1);
            }

            if (!flag4 && !flag2)
            {
                lightingInfo.aoLightValueScratchXYZNPN = lightingInfo.aoLightValueScratchXZNN;
                lightingInfo.aoBrightnessXYZNPN = lightingInfo.aoBrightnessXZNN;
            }
            else
            {
                lightingInfo.aoLightValueScratchXYZNPN = world.getBlock(x, y + 1, z - 1).getAmbientOcclusionLightValue();
                lightingInfo.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(world, x, y + 1, z - 1);
            }

            if (!flag5 && !flag2)
            {
                lightingInfo.aoLightValueScratchXYZNPP = lightingInfo.aoLightValueScratchXZNP;
                lightingInfo.aoBrightnessXYZNPP = lightingInfo.aoBrightnessXZNP;
            }
            else
            {
                lightingInfo.aoLightValueScratchXYZNPP = world.getBlock(x, y + 1, z + 1).getAmbientOcclusionLightValue();
                lightingInfo.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(world, x, y + 1, z + 1);
            }

            if (RenderBlocks.getInstance().renderMinX <= 0.0D)
            {
                ++x;
            }

            i1 = l;

            if (RenderBlocks.getInstance().renderMinX <= 0.0D || !world.getBlock(x - 1, y, z).isOpaqueCube())
            {
                i1 = block.getMixedBrightnessForBlock(world, x - 1, y, z);
            }

            f7 = world.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
            f8 = (lightingInfo.aoLightValueScratchXYNN + lightingInfo.aoLightValueScratchXYZNNP + f7 + lightingInfo.aoLightValueScratchXZNP) / 4.0F;
            f9 = (f7 + lightingInfo.aoLightValueScratchXZNP + lightingInfo.aoLightValueScratchXYNP + lightingInfo.aoLightValueScratchXYZNPP) / 4.0F;
            f10 = (lightingInfo.aoLightValueScratchXZNN + f7 + lightingInfo.aoLightValueScratchXYZNPN + lightingInfo.aoLightValueScratchXYNP) / 4.0F;
            f11 = (lightingInfo.aoLightValueScratchXYZNNN + lightingInfo.aoLightValueScratchXYNN + lightingInfo.aoLightValueScratchXZNN + f7) / 4.0F;
            f3 = (float)((double)f9 * RenderBlocks.getInstance().renderMaxY * RenderBlocks.getInstance().renderMaxZ + (double)f10 * RenderBlocks.getInstance().renderMaxY * (1.0D - RenderBlocks.getInstance().renderMaxZ) + (double)f11 * (1.0D - RenderBlocks.getInstance().renderMaxY) * (1.0D - RenderBlocks.getInstance().renderMaxZ) + (double)f8 * (1.0D - RenderBlocks.getInstance().renderMaxY) * RenderBlocks.getInstance().renderMaxZ);
            f4 = (float)((double)f9 * RenderBlocks.getInstance().renderMaxY * RenderBlocks.getInstance().renderMinZ + (double)f10 * RenderBlocks.getInstance().renderMaxY * (1.0D - RenderBlocks.getInstance().renderMinZ) + (double)f11 * (1.0D - RenderBlocks.getInstance().renderMaxY) * (1.0D - RenderBlocks.getInstance().renderMinZ) + (double)f8 * (1.0D - RenderBlocks.getInstance().renderMaxY) * RenderBlocks.getInstance().renderMinZ);
            f5 = (float)((double)f9 * RenderBlocks.getInstance().renderMinY * RenderBlocks.getInstance().renderMinZ + (double)f10 * RenderBlocks.getInstance().renderMinY * (1.0D - RenderBlocks.getInstance().renderMinZ) + (double)f11 * (1.0D - RenderBlocks.getInstance().renderMinY) * (1.0D - RenderBlocks.getInstance().renderMinZ) + (double)f8 * (1.0D - RenderBlocks.getInstance().renderMinY) * RenderBlocks.getInstance().renderMinZ);
            f6 = (float)((double)f9 * RenderBlocks.getInstance().renderMinY * RenderBlocks.getInstance().renderMaxZ + (double)f10 * RenderBlocks.getInstance().renderMinY * (1.0D - RenderBlocks.getInstance().renderMaxZ) + (double)f11 * (1.0D - RenderBlocks.getInstance().renderMinY) * (1.0D - RenderBlocks.getInstance().renderMaxZ) + (double)f8 * (1.0D - RenderBlocks.getInstance().renderMinY) * RenderBlocks.getInstance().renderMaxZ);
            j1 = lightingInfo.getAoBrightness(lightingInfo.aoBrightnessXYNN, lightingInfo.aoBrightnessXYZNNP, lightingInfo.aoBrightnessXZNP, i1);
            k1 = lightingInfo.getAoBrightness(lightingInfo.aoBrightnessXZNP, lightingInfo.aoBrightnessXYNP, lightingInfo.aoBrightnessXYZNPP, i1);
            l1 = lightingInfo.getAoBrightness(lightingInfo.aoBrightnessXZNN, lightingInfo.aoBrightnessXYZNPN, lightingInfo.aoBrightnessXYNP, i1);
            i2 = lightingInfo.getAoBrightness(lightingInfo.aoBrightnessXYZNNN, lightingInfo.aoBrightnessXYNN, lightingInfo.aoBrightnessXZNN, i1);
            lightingInfo.brightnessTopLeft = lightingInfo.mixAoBrightness(k1, l1, i2, j1, RenderBlocks.getInstance().renderMaxY * RenderBlocks.getInstance().renderMaxZ, RenderBlocks.getInstance().renderMaxY * (1.0D - RenderBlocks.getInstance().renderMaxZ), (1.0D - RenderBlocks.getInstance().renderMaxY) * (1.0D - RenderBlocks.getInstance().renderMaxZ), (1.0D - RenderBlocks.getInstance().renderMaxY) * RenderBlocks.getInstance().renderMaxZ);
            lightingInfo.brightnessBottomLeft = lightingInfo.mixAoBrightness(k1, l1, i2, j1, RenderBlocks.getInstance().renderMaxY * RenderBlocks.getInstance().renderMinZ, RenderBlocks.getInstance().renderMaxY * (1.0D - RenderBlocks.getInstance().renderMinZ), (1.0D - RenderBlocks.getInstance().renderMaxY) * (1.0D - RenderBlocks.getInstance().renderMinZ), (1.0D - RenderBlocks.getInstance().renderMaxY) * RenderBlocks.getInstance().renderMinZ);
            lightingInfo.brightnessBottomRight = lightingInfo.mixAoBrightness(k1, l1, i2, j1, RenderBlocks.getInstance().renderMinY * RenderBlocks.getInstance().renderMinZ, RenderBlocks.getInstance().renderMinY * (1.0D - RenderBlocks.getInstance().renderMinZ), (1.0D - RenderBlocks.getInstance().renderMinY) * (1.0D - RenderBlocks.getInstance().renderMinZ), (1.0D - RenderBlocks.getInstance().renderMinY) * RenderBlocks.getInstance().renderMinZ);
            lightingInfo.brightnessTopRight = lightingInfo.mixAoBrightness(k1, l1, i2, j1, RenderBlocks.getInstance().renderMinY * RenderBlocks.getInstance().renderMaxZ, RenderBlocks.getInstance().renderMinY * (1.0D - RenderBlocks.getInstance().renderMaxZ), (1.0D - RenderBlocks.getInstance().renderMinY) * (1.0D - RenderBlocks.getInstance().renderMaxZ), (1.0D - RenderBlocks.getInstance().renderMinY) * RenderBlocks.getInstance().renderMaxZ);

            lightingInfo.colorRedTopLeft = lightingInfo.colorRedBottomLeft = lightingInfo.colorRedBottomRight = lightingInfo.colorRedTopRight = 0.6F;
            lightingInfo.colorGreenTopLeft = lightingInfo.colorGreenBottomLeft = lightingInfo.colorGreenBottomRight = lightingInfo.colorGreenTopRight = 0.6F;
            lightingInfo.colorBlueTopLeft = lightingInfo.colorBlueBottomLeft = lightingInfo.colorBlueBottomRight = lightingInfo.colorBlueTopRight = 0.6F;

            lightingInfo.colorRedTopLeft *= f3;
            lightingInfo.colorGreenTopLeft *= f3;
            lightingInfo.colorBlueTopLeft *= f3;
            lightingInfo.colorRedBottomLeft *= f4;
            lightingInfo.colorGreenBottomLeft *= f4;
            lightingInfo.colorBlueBottomLeft *= f4;
            lightingInfo.colorRedBottomRight *= f5;
            lightingInfo.colorGreenBottomRight *= f5;
            lightingInfo.colorBlueBottomRight *= f5;
            lightingInfo.colorRedTopRight *= f6;
            lightingInfo.colorGreenTopRight *= f6;
            lightingInfo.colorBlueTopRight *= f6;
        }

        if(side==5)
        //		if (lightingInfo.renderAllFaces || block.shouldSideBeRendered(world, x + 1, y, z, 5))
        {
            if (RenderBlocks.getInstance().renderMaxX >= 1.0D)
            {
                ++x;
            }

            lightingInfo.aoLightValueScratchXYPN = world.getBlock(x, y - 1, z).getAmbientOcclusionLightValue();
            lightingInfo.aoLightValueScratchXZPN = world.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
            lightingInfo.aoLightValueScratchXZPP = world.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
            lightingInfo.aoLightValueScratchXYPP = world.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
            lightingInfo.aoBrightnessXYPN = block.getMixedBrightnessForBlock(world, x, y - 1, z);
            lightingInfo.aoBrightnessXZPN = block.getMixedBrightnessForBlock(world, x, y, z - 1);
            lightingInfo.aoBrightnessXZPP = block.getMixedBrightnessForBlock(world, x, y, z + 1);
            lightingInfo.aoBrightnessXYPP = block.getMixedBrightnessForBlock(world, x, y + 1, z);
            flag2 = world.getBlock(x + 1, y + 1, z).getCanBlockGrass();
            flag3 = world.getBlock(x + 1, y - 1, z).getCanBlockGrass();
            flag4 = world.getBlock(x + 1, y, z + 1).getCanBlockGrass();
            flag5 = world.getBlock(x + 1, y, z - 1).getCanBlockGrass();

            if (!flag3 && !flag5)
            {
                lightingInfo.aoLightValueScratchXYZPNN = lightingInfo.aoLightValueScratchXZPN;
                lightingInfo.aoBrightnessXYZPNN = lightingInfo.aoBrightnessXZPN;
            }
            else
            {
                lightingInfo.aoLightValueScratchXYZPNN = world.getBlock(x, y - 1, z - 1).getAmbientOcclusionLightValue();
                lightingInfo.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(world, x, y - 1, z - 1);
            }

            if (!flag3 && !flag4)
            {
                lightingInfo.aoLightValueScratchXYZPNP = lightingInfo.aoLightValueScratchXZPP;
                lightingInfo.aoBrightnessXYZPNP = lightingInfo.aoBrightnessXZPP;
            }
            else
            {
                lightingInfo.aoLightValueScratchXYZPNP = world.getBlock(x, y - 1, z + 1).getAmbientOcclusionLightValue();
                lightingInfo.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(world, x, y - 1, z + 1);
            }

            if (!flag2 && !flag5)
            {
                lightingInfo.aoLightValueScratchXYZPPN = lightingInfo.aoLightValueScratchXZPN;
                lightingInfo.aoBrightnessXYZPPN = lightingInfo.aoBrightnessXZPN;
            }
            else
            {
                lightingInfo.aoLightValueScratchXYZPPN = world.getBlock(x, y + 1, z - 1).getAmbientOcclusionLightValue();
                lightingInfo.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(world, x, y + 1, z - 1);
            }

            if (!flag2 && !flag4)
            {
                lightingInfo.aoLightValueScratchXYZPPP = lightingInfo.aoLightValueScratchXZPP;
                lightingInfo.aoBrightnessXYZPPP = lightingInfo.aoBrightnessXZPP;
            }
            else
            {
                lightingInfo.aoLightValueScratchXYZPPP = world.getBlock(x, y + 1, z + 1).getAmbientOcclusionLightValue();
                lightingInfo.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(world, x, y + 1, z + 1);
            }

            if (RenderBlocks.getInstance().renderMaxX >= 1.0D)
            {
                --x;
            }

            i1 = l;

            if (RenderBlocks.getInstance().renderMaxX >= 1.0D || !world.getBlock(x + 1, y, z).isOpaqueCube())
            {
                i1 = block.getMixedBrightnessForBlock(world, x + 1, y, z);
            }

            f7 = world.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
            f8 = (lightingInfo.aoLightValueScratchXYPN + lightingInfo.aoLightValueScratchXYZPNP + f7 + lightingInfo.aoLightValueScratchXZPP) / 4.0F;
            f9 = (lightingInfo.aoLightValueScratchXYZPNN + lightingInfo.aoLightValueScratchXYPN + lightingInfo.aoLightValueScratchXZPN + f7) / 4.0F;
            f10 = (lightingInfo.aoLightValueScratchXZPN + f7 + lightingInfo.aoLightValueScratchXYZPPN + lightingInfo.aoLightValueScratchXYPP) / 4.0F;
            f11 = (f7 + lightingInfo.aoLightValueScratchXZPP + lightingInfo.aoLightValueScratchXYPP + lightingInfo.aoLightValueScratchXYZPPP) / 4.0F;
            f3 = (float)((double)f8 * (1.0D - RenderBlocks.getInstance().renderMinY) * RenderBlocks.getInstance().renderMaxZ + (double)f9 * (1.0D - RenderBlocks.getInstance().renderMinY) * (1.0D - RenderBlocks.getInstance().renderMaxZ) + (double)f10 * RenderBlocks.getInstance().renderMinY * (1.0D - RenderBlocks.getInstance().renderMaxZ) + (double)f11 * RenderBlocks.getInstance().renderMinY * RenderBlocks.getInstance().renderMaxZ);
            f4 = (float)((double)f8 * (1.0D - RenderBlocks.getInstance().renderMinY) * RenderBlocks.getInstance().renderMinZ + (double)f9 * (1.0D - RenderBlocks.getInstance().renderMinY) * (1.0D - RenderBlocks.getInstance().renderMinZ) + (double)f10 * RenderBlocks.getInstance().renderMinY * (1.0D - RenderBlocks.getInstance().renderMinZ) + (double)f11 * RenderBlocks.getInstance().renderMinY * RenderBlocks.getInstance().renderMinZ);
            f5 = (float)((double)f8 * (1.0D - RenderBlocks.getInstance().renderMaxY) * RenderBlocks.getInstance().renderMinZ + (double)f9 * (1.0D - RenderBlocks.getInstance().renderMaxY) * (1.0D - RenderBlocks.getInstance().renderMinZ) + (double)f10 * RenderBlocks.getInstance().renderMaxY * (1.0D - RenderBlocks.getInstance().renderMinZ) + (double)f11 * RenderBlocks.getInstance().renderMaxY * RenderBlocks.getInstance().renderMinZ);
            f6 = (float)((double)f8 * (1.0D - RenderBlocks.getInstance().renderMaxY) * RenderBlocks.getInstance().renderMaxZ + (double)f9 * (1.0D - RenderBlocks.getInstance().renderMaxY) * (1.0D - RenderBlocks.getInstance().renderMaxZ) + (double)f10 * RenderBlocks.getInstance().renderMaxY * (1.0D - RenderBlocks.getInstance().renderMaxZ) + (double)f11 * RenderBlocks.getInstance().renderMaxY * RenderBlocks.getInstance().renderMaxZ);
            j1 = lightingInfo.getAoBrightness(lightingInfo.aoBrightnessXYPN, lightingInfo.aoBrightnessXYZPNP, lightingInfo.aoBrightnessXZPP, i1);
            k1 = lightingInfo.getAoBrightness(lightingInfo.aoBrightnessXZPP, lightingInfo.aoBrightnessXYPP, lightingInfo.aoBrightnessXYZPPP, i1);
            l1 = lightingInfo.getAoBrightness(lightingInfo.aoBrightnessXZPN, lightingInfo.aoBrightnessXYZPPN, lightingInfo.aoBrightnessXYPP, i1);
            i2 = lightingInfo.getAoBrightness(lightingInfo.aoBrightnessXYZPNN, lightingInfo.aoBrightnessXYPN, lightingInfo.aoBrightnessXZPN, i1);
            lightingInfo.brightnessTopLeft = lightingInfo.mixAoBrightness(j1, i2, l1, k1, (1.0D - RenderBlocks.getInstance().renderMinY) * RenderBlocks.getInstance().renderMaxZ, (1.0D - RenderBlocks.getInstance().renderMinY) * (1.0D - RenderBlocks.getInstance().renderMaxZ), RenderBlocks.getInstance().renderMinY * (1.0D - RenderBlocks.getInstance().renderMaxZ), RenderBlocks.getInstance().renderMinY * RenderBlocks.getInstance().renderMaxZ);
            lightingInfo.brightnessBottomLeft = lightingInfo.mixAoBrightness(j1, i2, l1, k1, (1.0D - RenderBlocks.getInstance().renderMinY) * RenderBlocks.getInstance().renderMinZ, (1.0D - RenderBlocks.getInstance().renderMinY) * (1.0D - RenderBlocks.getInstance().renderMinZ), RenderBlocks.getInstance().renderMinY * (1.0D - RenderBlocks.getInstance().renderMinZ), RenderBlocks.getInstance().renderMinY * RenderBlocks.getInstance().renderMinZ);
            lightingInfo.brightnessBottomRight = lightingInfo.mixAoBrightness(j1, i2, l1, k1, (1.0D - RenderBlocks.getInstance().renderMaxY) * RenderBlocks.getInstance().renderMinZ, (1.0D - RenderBlocks.getInstance().renderMaxY) * (1.0D - RenderBlocks.getInstance().renderMinZ), RenderBlocks.getInstance().renderMaxY * (1.0D - RenderBlocks.getInstance().renderMinZ), RenderBlocks.getInstance().renderMaxY * RenderBlocks.getInstance().renderMinZ);
            lightingInfo.brightnessTopRight = lightingInfo.mixAoBrightness(j1, i2, l1, k1, (1.0D - RenderBlocks.getInstance().renderMaxY) * RenderBlocks.getInstance().renderMaxZ, (1.0D - RenderBlocks.getInstance().renderMaxY) * (1.0D - RenderBlocks.getInstance().renderMaxZ), RenderBlocks.getInstance().renderMaxY * (1.0D - RenderBlocks.getInstance().renderMaxZ), RenderBlocks.getInstance().renderMaxY * RenderBlocks.getInstance().renderMaxZ);

            lightingInfo.colorRedTopLeft = lightingInfo.colorRedBottomLeft = lightingInfo.colorRedBottomRight = lightingInfo.colorRedTopRight = 0.6F;
            lightingInfo.colorGreenTopLeft = lightingInfo.colorGreenBottomLeft = lightingInfo.colorGreenBottomRight = lightingInfo.colorGreenTopRight = 0.6F;
            lightingInfo.colorBlueTopLeft = lightingInfo.colorBlueBottomLeft = lightingInfo.colorBlueBottomRight = lightingInfo.colorBlueTopRight = 0.6F;

            lightingInfo.colorRedTopLeft *= f3;
            lightingInfo.colorGreenTopLeft *= f3;
            lightingInfo.colorBlueTopLeft *= f3;
            lightingInfo.colorRedBottomLeft *= f4;
            lightingInfo.colorGreenBottomLeft *= f4;
            lightingInfo.colorBlueBottomLeft *= f4;
            lightingInfo.colorRedBottomRight *= f5;
            lightingInfo.colorGreenBottomRight *= f5;
            lightingInfo.colorBlueBottomRight *= f5;
            lightingInfo.colorRedTopRight *= f6;
            lightingInfo.colorGreenTopRight *= f6;
            lightingInfo.colorBlueTopRight *= f6;
        }
        return lightingInfo;
    }

    public static class BlockLightingInfo
    {
        public int aoBrightnessXYNN;
        public int aoBrightnessYZNN;
        public int aoBrightnessYZNP;
        public int aoBrightnessXYPN;
        public float aoLightValueScratchXYNN;
        public float aoLightValueScratchYZNN;
        public float aoLightValueScratchYZNP;
        public float aoLightValueScratchXYPN;
        public float aoLightValueScratchXYZNNN;
        public int aoBrightnessXYZNNN;
        public float aoLightValueScratchXYZNNP;
        public int aoBrightnessXYZNNP;
        public float aoLightValueScratchXYZPNN;
        public int aoBrightnessXYZPNN;
        public float aoLightValueScratchXYZPNP;
        public int aoBrightnessXYZPNP;
        public int brightnessTopLeft;
        public int brightnessTopRight;
        public int brightnessBottomRight;
        public int brightnessBottomLeft;
        public float colorRedTopLeft;
        public float colorGreenTopLeft;
        public float colorBlueTopLeft;
        public float colorRedBottomLeft;
        public float colorRedBottomRight;
        public float colorRedTopRight;
        public float colorGreenTopRight;
        public float colorBlueTopRight;
        public float colorGreenBottomRight;
        public float colorBlueBottomRight;
        public float colorGreenBottomLeft;
        public float colorBlueBottomLeft;

        public int aoBrightnessXYNP;
        public int aoBrightnessXYPP;
        public int aoBrightnessYZPN;
        public int aoBrightnessYZPP;
        public float aoLightValueScratchXYNP;
        public float aoLightValueScratchXYPP;
        public float aoLightValueScratchYZPN;
        public float aoLightValueScratchYZPP;
        public float aoLightValueScratchXYZNPN;
        public int aoBrightnessXYZNPN;
        public float aoLightValueScratchXYZPPN;
        public int aoBrightnessXYZPPN;
        public float aoLightValueScratchXYZNPP;
        public int aoBrightnessXYZNPP;
        public float aoLightValueScratchXYZPPP;
        public int aoBrightnessXYZPPP;

        public float aoLightValueScratchXZNN;
        public float aoLightValueScratchXZPN;
        public int aoBrightnessXZNN;
        public int aoBrightnessXZPN;

        public float aoLightValueScratchXZNP;
        public float aoLightValueScratchXZPP;
        public int aoBrightnessXZNP;
        public int aoBrightnessXZPP;

        public int getAoBrightness(int par0, int par1, int par2, int par3)
        {
            if (par0 == 0)
                par0 = par3;
            if (par1 == 0)
                par1 = par3;
            if (par2 == 0)
                par2 = par3;
            return par0 + par1 + par2 + par3 >> 2 & 16711935;
        }
        public int mixAoBrightness(int par0, int par1, int par2, int par3, double par4, double par5, double par6, double par7)
        {
            int i1 = (int)((double)(par0 >> 16 & 255) * par4 + (double)(par1 >> 16 & 255) * par5 + (double)(par2 >> 16 & 255) * par6 + (double)(par3 >> 16 & 255) * par7) & 255;
            int j1 = (int)((double)(par0 & 255) * par4 + (double)(par1 & 255) * par5 + (double)(par2 & 255) * par6 + (double)(par3 & 255) * par7) & 255;
            return i1 << 16 | j1;
        }
    }
}
