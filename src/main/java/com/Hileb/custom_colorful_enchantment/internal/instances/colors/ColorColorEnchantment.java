package com.Hileb.custom_colorful_enchantment.internal.instances.colors;

import com.Hileb.custom_colorful_enchantment.api.colors.IColorFactory;
import com.Hileb.custom_colorful_enchantment.api.colors.IColorInstance;
import com.google.gson.JsonObject;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

/**
 * @Project Colorful Enchantment
 * @Author Hileb
 * @Date 2023/9/24 11:00
 **/
public class ColorColorEnchantment{
    public static class Factory implements IColorFactory{
        public Factory(){

        }
        @Override
        public IColorInstance getInstance(JsonObject object) {
            return new Instance(object,
                    JsonUtils.getInt(object,"color",0)
            );
        }
    }
    public static class Instance implements IColorInstance{
        public final Enchantment enchantment;
        public boolean isAll;
        public final int color;
        public Instance(JsonObject oin,int colorIn){
            if (oin.has("accept_all")) {
                isAll = true;
                enchantment=null;
            }
            else{
                isAll=false;
                enchantment=ForgeRegistries.ENCHANTMENTS.getValue( new ResourceLocation(JsonUtils.getString(oin,"enchantment",null)));
            }
            color=colorIn;
        }
        @Override
        public int getRGBColor(ItemStack stack) {
            return color;
        }
        @Override
        public boolean hasColor(ItemStack stack) {
            return EnchantmentHelper.getEnchantments(stack).containsKey(enchantment) || isAll&&stack.isItemEnchanted();
        }
    }
}
