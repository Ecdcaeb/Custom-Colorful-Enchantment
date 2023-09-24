package com.Hileb.custom_colorful_enchantment.internal.instances;

import com.Hileb.custom_colorful_enchantment.ColorfulEnchantmentConfig;
import com.Hileb.custom_colorful_enchantment.internal.json.FactoryLoader;
import com.google.common.eventbus.Subscribe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * @Project Colorful Enchantment
 * @Author Hileb
 * @Date 2023/9/24 11:20
 **/
public class DeferredBusHandler {
    public DeferredBusHandler(){}
    @Subscribe
    public void handle(FMLPreInitializationEvent event){
        ColorfulEnchantmentConfig.initConfig();
        MinecraftForge.EVENT_BUS.register(EventHandler.class);
        FactoryLoader.load(ColorfulEnchantmentConfig.instance);
    }
}
