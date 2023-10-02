package com.Hileb.custom_colorful_enchantment.internal.instances;

import com.Hileb.custom_colorful_enchantment.ColorfulEnchantmentConfig;
import com.Hileb.custom_colorful_enchantment.internal.json.FactoryLoader;
import net.minecraftforge.common.MinecraftForge;

/**
 * @Project Custom-Colorful-Enchantment
 * @Author Hileb
 * @Date 2023/10/2 20:17
 **/
public class DeferredBusHandler {

    public static void handle() {
        ColorfulEnchantmentConfig.initConfig();
        MinecraftForge.EVENT_BUS.register(EventHandler.class);
        FactoryLoader.load(ColorfulEnchantmentConfig.instance);
    }
}
