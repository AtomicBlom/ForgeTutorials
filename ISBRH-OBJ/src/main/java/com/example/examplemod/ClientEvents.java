package com.example.examplemod;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.TextureStitchEvent;

/**
 * Created by codew on 1/11/2015.
 */
public class ClientEvents {
    private static ClientEvents instance;

    public static ClientEvents getInstance() {
        if (instance == null) {
            instance = new ClientEvents();
        }
        return instance;
    }

    private ClientEvents() {}

    @SubscribeEvent
    public void preTextureStitch(TextureStitchEvent.Pre event)
    {
        if(event.map.getTextureType()==0) {
            final IIcon cupola = event.map.registerIcon("examplemod:cupola");

            final TutorialBlockRenderer instance = TutorialBlockRenderer.getInstance();
            instance.setIcon(cupola);

        }
    }

    @SubscribeEvent
    public void postTextureStitch(TextureStitchEvent.Post event)
    {
        if(event.map.getTextureType()==0) {
            TutorialBlockRenderer.getInstance().remapTextures();
        }
    }
}
