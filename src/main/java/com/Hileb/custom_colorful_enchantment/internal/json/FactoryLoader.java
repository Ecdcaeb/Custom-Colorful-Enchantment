package com.Hileb.custom_colorful_enchantment.internal.json;

import com.Hileb.custom_colorful_enchantment.ColorfulEnchantmentConfig;
import com.Hileb.custom_colorful_enchantment.api.registry.ColorFactoryRegistry;
import com.Hileb.custom_colorful_enchantment.api.registry.ColorInstancesRegistry;
import com.Hileb.custom_colorful_enchantment.api.colors.IColorFactory;
import com.google.gson.JsonObject;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

/**
 * @Project Colorful Enchantment
 * @Author Hileb
 * @Date 2023/9/24 10:48
 **/
public class FactoryLoader{
    public static void load(ColorfulEnchantmentConfig.Config config){
        ColorFactoryRegistry.post();
        for(JsonObject json:config.colors){
            ResourceLocation name=new ResourceLocation(JsonUtils.getString(json,"type"));
            IColorFactory factory= ColorFactoryRegistry.REGISTRY.get(name);
            ColorInstancesRegistry.REGISTRY.put(new ResourceLocation(name.toString(),String.valueOf(json.toString().hashCode())),factory.getInstance(json));
        }
        ColorInstancesRegistry.post();
    }
}
