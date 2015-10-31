package com.example.examplemod.client;

import com.example.examplemod.common.Proxy;
import com.example.examplemod.client.rendering.TutorialBlockRenderer;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends Proxy {
    public void init() {
        RenderingRegistry.registerBlockHandler(TutorialBlockRenderer.getInstance().getRenderId(),
                TutorialBlockRenderer.getInstance()
        );
    }
}
