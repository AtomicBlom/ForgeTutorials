package com.example.examplemod;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends Proxy {
    public void init() {
        RenderingRegistry.registerBlockHandler(TutorialBlockRenderer.getInstance().getRenderId(),
                TutorialBlockRenderer.getInstance()
        );
    }
}
