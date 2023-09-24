package com.Hileb.custom_colorful_enchantment.api.registry;

import com.Hileb.custom_colorful_enchantment.api.colors.IColorInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.HashMap;

/**
 * @Project Colorful Enchantment
 * @Author Hileb
 * @Date 2023/9/24 10:47
 **/
public class ColorInstancesRegistry {
    public static HashMap<ResourceLocation, IColorInstance> REGISTRY=new HashMap<>();
    public static class RegistryEvent extends Event {
        public void register(ResourceLocation name,IColorInstance factory){
            REGISTRY.put(name,factory);
        }
        @Override
        public boolean isCancelable() {
            return false;
        }
    }
    public static void post(){
        MinecraftForge.EVENT_BUS.post(new RegistryEvent());
    }
}
