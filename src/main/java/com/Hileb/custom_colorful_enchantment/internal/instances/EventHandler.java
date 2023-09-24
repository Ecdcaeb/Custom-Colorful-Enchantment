package com.Hileb.custom_colorful_enchantment.internal.instances;

import com.Hileb.custom_colorful_enchantment.ColorfulEnchantment;
import com.Hileb.custom_colorful_enchantment.api.registry.ColorFactoryRegistry;
import com.Hileb.custom_colorful_enchantment.api.registry.ColorInstancesRegistry;
import com.Hileb.custom_colorful_enchantment.internal.instances.colors.ColorColorEnchantment;
import com.Hileb.custom_colorful_enchantment.internal.instances.colors.ColorColorItem;
import com.Hileb.custom_colorful_enchantment.internal.instances.colors.ColorInstanceNBT;
import com.Hileb.custom_colorful_enchantment.internal.instances.colors.ColorInstanceVanilla;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @Project Colorful Enchantment
 * @Author Hileb
 * @Date 2023/9/24 10:56
 **/

public class EventHandler{
    @SubscribeEvent
    public static void onPreRegister(ColorFactoryRegistry.RegistryEvent event){
        event.register(new ResourceLocation(ColorfulEnchantment.MODID,"enchantment"),
                new ColorColorEnchantment.Factory());
        event.register(new ResourceLocation(ColorfulEnchantment.MODID,"ingredient"),
                new ColorColorItem.Factory());
    }
    @SubscribeEvent
    public static void onPostRegister(ColorInstancesRegistry.RegistryEvent event){
        event.register(new ResourceLocation(ColorfulEnchantment.MODID,"nbt"),new ColorInstanceNBT());
        event.register(new ResourceLocation("minecraft","enchantment"),new ColorInstanceVanilla());
    }
}
