package com.example.examplemod;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.obj.*;

import java.util.HashMap;

/**
 * Created by codew on 1/11/2015.
 */
public class RenderingUtils {
    public static int currentRenderPass;

    public static void renderStaticWavefrontModel(TileEntity tile, WavefrontObject model, Tessellator tes, Matrix4 transform, int offsetLighting, boolean invertFaces, String... renderedParts) {
        renderStaticWavefrontModel(tile.xCoord, tile.yCoord, tile.zCoord, tile.getWorldObj(), model, tes, transform, offsetLighting, invertFaces, renderedParts);
    }

    /**
     * A big "Thank you!" to AtomicBlom and Rorax for helping me figure this one out =P
     */
    public static void renderStaticWavefrontModel(int x, int y, int z, IBlockAccess world, WavefrontObject model, Tessellator tes, Matrix4 transform, int offsetLighting, boolean invertFaces, String... renderedParts)
    {
        Block block = null;

        if(world!=null)
        {
            int lb = world.getLightBrightnessForSkyBlocks(x, y, z, 0);
            int lb_j = lb % 65536;
            int lb_k = lb / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) lb_j / 1.0F, (float) lb_k / 1.0F);
            block = world.getBlock(x, y, z);
        }
        Vertex vertexCopy = new Vertex(0,0,0);
        Vertex normalCopy = new Vertex(0,0,0);

        for(GroupObject groupObject : model.groupObjects)
        {
            boolean render = false;
            if(renderedParts==null || renderedParts.length<1) {
                render = true;
            }
            else {
                for (String s : renderedParts) {
                    if (groupObject.name.equalsIgnoreCase(s)) {
                        render = true;
                    }
                }
            }
            if(render) {
                for (Face face : groupObject.faces) {
                    if (face.faceNormal == null) {
                        face.faceNormal = face.calculateFaceNormal();
                    }

                    normalCopy.x = face.faceNormal.x;
                    normalCopy.y = face.faceNormal.y;
                    normalCopy.z = face.faceNormal.z;
                    transform.apply(normalCopy);

                    float biggestNormal = Math.max(Math.abs(normalCopy.y), Math.max(Math.abs(normalCopy.x), Math.abs(normalCopy.z)));
                    int side = biggestNormal == Math.abs(normalCopy.y) ? (normalCopy.y < 0 ? 0 : 1) : biggestNormal == Math.abs(normalCopy.z) ? (normalCopy.z < 0 ? 2 : 3) : (normalCopy.x < 0 ? 4 : 5);

                    HashMap<String, LightingUtils.BlockLightingInfo> light = new HashMap<String, LightingUtils.BlockLightingInfo>();
                    LightingUtils.BlockLightingInfo completeLight = null;
                    if (offsetLighting == 0 && world != null)
                        completeLight = LightingUtils.calculateBlockLighting(side, world, block, x, y, z, 1, 1, 1);

                    tes.setNormal(face.faceNormal.x, face.faceNormal.y, face.faceNormal.z);

                    final int vertexCount = face.vertices.length;
                    for (int i = 0; i < vertexCount; ++i) {
                        int target = !invertFaces ? i : (vertexCount - 1 - i);
                        int corner = (int) (target / (float) vertexCount * 4);
                        Vertex vertex = face.vertices[target];
                        vertexCopy.x = vertex.x;
                        vertexCopy.y = vertex.y;
                        vertexCopy.z = vertex.z;
                        transform.apply(vertexCopy);

                        if (offsetLighting == 1 && world != null) {
                            String key = Math.round(x + vertex.x) + ";" + Math.round(y + vertex.y) + ";" + Math.round(z + vertex.z);
                            LightingUtils.BlockLightingInfo info = light.get(key);
                            if (info == null) {
                                info = LightingUtils.calculateBlockLighting(side, world, block, (int) Math.round(x + vertex.x), (int) Math.round(y + vertex.y), (int) Math.round(z + vertex.z), 1, 1, 1);
                                light.put(key, info);
                            }
                            tes.setBrightness(corner == 0 ? info.brightnessTopLeft : corner == 1 ? info.brightnessBottomLeft : corner == 2 ? info.brightnessBottomRight : info.brightnessTopRight);
                            float r = corner == 0 ? info.colorRedTopLeft : corner == 1 ? info.colorRedBottomLeft : corner == 2 ? info.colorRedBottomRight : info.colorRedTopRight;
                            float g = corner == 0 ? info.colorGreenTopLeft : corner == 1 ? info.colorGreenBottomLeft : corner == 2 ? info.colorGreenBottomRight : info.colorGreenTopRight;
                            float b = corner == 0 ? info.colorBlueTopLeft : corner == 1 ? info.colorBlueBottomLeft : corner == 2 ? info.colorBlueBottomRight : info.colorBlueTopRight;
                            tes.setColorOpaque_F(r, g, b);
                        } else if (offsetLighting == 0 && world != null && completeLight != null) {
                            tes.setBrightness(corner == 0 ? completeLight.brightnessTopLeft : corner == 1 ? completeLight.brightnessBottomLeft : corner == 2 ? completeLight.brightnessBottomRight : completeLight.brightnessTopRight);
                            float r = corner == 0 ? completeLight.colorRedTopLeft : corner == 1 ? completeLight.colorRedBottomLeft : corner == 2 ? completeLight.colorRedBottomRight : completeLight.colorRedTopRight;
                            float g = corner == 0 ? completeLight.colorGreenTopLeft : corner == 1 ? completeLight.colorGreenBottomLeft : corner == 2 ? completeLight.colorGreenBottomRight : completeLight.colorGreenTopRight;
                            float b = corner == 0 ? completeLight.colorBlueTopLeft : corner == 1 ? completeLight.colorBlueBottomLeft : corner == 2 ? completeLight.colorBlueBottomRight : completeLight.colorBlueTopRight;
                            tes.setColorOpaque_F(r, g, b);
                        }

                        if (face.textureCoordinates != null && face.textureCoordinates.length > 0) {
                            TextureCoordinate textureCoordinate = face.textureCoordinates[target];
                            tes.addVertexWithUV(vertexCopy.x, vertexCopy.y, vertexCopy.z, textureCoordinate.u, textureCoordinate.v);
                        } else {
                            tes.addVertex(vertexCopy.x, vertexCopy.y, vertexCopy.z);
                        }

                        if (vertexCount == 3 && i == 2) {
                            if (face.textureCoordinates != null && face.textureCoordinates.length > 0) {
                                TextureCoordinate textureCoordinate = face.textureCoordinates[target];
                                tes.addVertexWithUV(vertexCopy.x, vertexCopy.y, vertexCopy.z, textureCoordinate.u, textureCoordinate.v);
                            } else {
                                tes.addVertex(vertexCopy.x, vertexCopy.y, vertexCopy.z);
                            }
                        }
                    }
                }
            }
        }
    }
}
