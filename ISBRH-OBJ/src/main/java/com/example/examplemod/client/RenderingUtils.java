package com.example.examplemod.client;

import com.example.examplemod.util.Matrix4;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.obj.*;

import java.util.HashMap;

/**
 * Created by codew on 1/11/2015.
 */
public class RenderingUtils {
    public static int currentRenderPass;

    public static void renderStaticWavefrontModel(TileEntity tile, WavefrontObject model, Matrix4 transform, LightingMode offsetLighting, boolean invertFaces, String... renderedParts) {
        renderStaticWavefrontModel(tile.xCoord, tile.yCoord, tile.zCoord, tile.getWorldObj(), model, transform, offsetLighting, invertFaces, renderedParts);
    }

    public static void renderStaticWavefrontModel(WavefrontObject model, Matrix4 transform, LightingMode offsetLighting, boolean invertFaces, String... renderedParts) {
        renderStaticWavefrontModel(0, 0, 0, null, model, transform, offsetLighting, invertFaces, renderedParts);
    }

    /**
     * A big "Thank you!" to AtomicBlom and Rorax for helping me figure this one out =P
     */
    public static void renderStaticWavefrontModel(int x, int y, int z, IBlockAccess world, WavefrontObject model, Matrix4 transform, LightingMode lightingMode, boolean invertFaces, String... renderedParts)
    {
        Tessellator tessellator = Tessellator.instance;

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
                    if (lightingMode == LightingMode.NONE && world != null)
                        completeLight = LightingUtils.calculateBlockLighting(side, world, block, x, y, z, 1, 1, 1);

                    tessellator.setNormal(face.faceNormal.x, face.faceNormal.y, face.faceNormal.z);

                    final int vertexCount = face.vertices.length;
                    for (int i = 0; i < vertexCount; ++i) {
                        int target = !invertFaces ? i : (vertexCount - 1 - i);
                        int corner = (int) (target / (float) vertexCount * 4);
                        Vertex vertex = face.vertices[target];
                        vertexCopy.x = vertex.x;
                        vertexCopy.y = vertex.y;
                        vertexCopy.z = vertex.z;
                        transform.apply(vertexCopy);

                        if (lightingMode == LightingMode.DEFAULT && world != null) {
                            String key = Math.round(x + vertex.x) + ";" + Math.round(y + vertex.y) + ";" + Math.round(z + vertex.z);
                            LightingUtils.BlockLightingInfo info = light.get(key);
                            if (info == null) {
                                info = LightingUtils.calculateBlockLighting(side, world, block, (int) Math.round(x + vertex.x), (int) Math.round(y + vertex.y), (int) Math.round(z + vertex.z), 1, 1, 1);
                                light.put(key, info);
                            }
                            tessellator.setBrightness(corner == 0 ? info.brightnessTopLeft : corner == 1 ? info.brightnessBottomLeft : corner == 2 ? info.brightnessBottomRight : info.brightnessTopRight);
                            float r = corner == 0 ? info.colorRedTopLeft : corner == 1 ? info.colorRedBottomLeft : corner == 2 ? info.colorRedBottomRight : info.colorRedTopRight;
                            float g = corner == 0 ? info.colorGreenTopLeft : corner == 1 ? info.colorGreenBottomLeft : corner == 2 ? info.colorGreenBottomRight : info.colorGreenTopRight;
                            float b = corner == 0 ? info.colorBlueTopLeft : corner == 1 ? info.colorBlueBottomLeft : corner == 2 ? info.colorBlueBottomRight : info.colorBlueTopRight;
                            tessellator.setColorOpaque_F(r, g, b);
                        } else if (lightingMode == LightingMode.NONE && world != null && completeLight != null) {
                            tessellator.setBrightness(corner == 0 ? completeLight.brightnessTopLeft : corner == 1 ? completeLight.brightnessBottomLeft : corner == 2 ? completeLight.brightnessBottomRight : completeLight.brightnessTopRight);
                            float r = corner == 0 ? completeLight.colorRedTopLeft : corner == 1 ? completeLight.colorRedBottomLeft : corner == 2 ? completeLight.colorRedBottomRight : completeLight.colorRedTopRight;
                            float g = corner == 0 ? completeLight.colorGreenTopLeft : corner == 1 ? completeLight.colorGreenBottomLeft : corner == 2 ? completeLight.colorGreenBottomRight : completeLight.colorGreenTopRight;
                            float b = corner == 0 ? completeLight.colorBlueTopLeft : corner == 1 ? completeLight.colorBlueBottomLeft : corner == 2 ? completeLight.colorBlueBottomRight : completeLight.colorBlueTopRight;
                            tessellator.setColorOpaque_F(r, g, b);
                        }

                        if (face.textureCoordinates != null && face.textureCoordinates.length > 0) {
                            TextureCoordinate textureCoordinate = face.textureCoordinates[target];
                            tessellator.addVertexWithUV(vertexCopy.x, vertexCopy.y, vertexCopy.z, textureCoordinate.u, textureCoordinate.v);
                        } else {
                            tessellator.addVertex(vertexCopy.x, vertexCopy.y, vertexCopy.z);
                        }

                        if (vertexCount == 3 && i == 2) {
                            if (face.textureCoordinates != null && face.textureCoordinates.length > 0) {
                                TextureCoordinate textureCoordinate = face.textureCoordinates[target];
                                tessellator.addVertexWithUV(vertexCopy.x, vertexCopy.y, vertexCopy.z, textureCoordinate.u, textureCoordinate.v);
                            } else {
                                tessellator.addVertex(vertexCopy.x, vertexCopy.y, vertexCopy.z);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void remapUVs(Iterable<GroupObject> groupObjects, IIcon icon) {
        for (GroupObject groupObject : groupObjects) {
            remapUVs(groupObject, icon);
        }
    }

    public static void remapUVs(GroupObject object, IIcon icon) {
        if(icon==null)
            return;

        float minU = icon.getInterpolatedU(0);
        float sizeU = icon.getInterpolatedU(16) - minU;
        float minV = icon.getInterpolatedV(0);
        float sizeV = icon.getInterpolatedV(16) - minV;
        float baseOffsetU = (16f/icon.getIconWidth())*.0005F;
        float baseOffsetV = (16f/icon.getIconHeight())*.0005F;
        for(Face face : object.faces)
        {
            float averageU = 0F;
            float averageV = 0F;
            if(face.textureCoordinates!=null && face.textureCoordinates.length>0)
            {
                for(int i=0; i<face.textureCoordinates.length; ++i)
                {
                    averageU += face.textureCoordinates[i].u;
                    averageV += face.textureCoordinates[i].v;
                }
                averageU = averageU / face.textureCoordinates.length;
                averageV = averageV / face.textureCoordinates.length;
            }

            //TextureCoordinate[] oldUVs = new TextureCoordinate[face.textureCoordinates.length];
            for(int v=0; v<face.vertices.length; ++v)
            {
                float offsetU, offsetV;
                offsetU = baseOffsetU;
                offsetV = baseOffsetV;
                if (face.textureCoordinates[v].u > averageU)
                    offsetU = -offsetU;
                if (face.textureCoordinates[v].v > averageV)
                    offsetV = -offsetV;

                //oldUVs[v] = face.textureCoordinates[v];
                TextureCoordinate textureCoordinate = face.textureCoordinates[v];
                face.textureCoordinates[v] = new TextureCoordinate(
                        minU + sizeU * (textureCoordinate.u+offsetU),
                        minV + sizeV * (textureCoordinate.v+offsetV)
                );
            }

            /*for(int v=0; v<face.vertices.length; ++v)
                face.textureCoordinates[v] = new TextureCoordinate(oldUVs[v].u,oldUVs[v].v);*/
        }
    }

    public enum LightingMode {
        NONE,
        DEFAULT,
        VERTEX
    }
}
