package com.Hileb.custom_colorful_enchantment.internal.instances.colors;

import com.Hileb.custom_colorful_enchantment.ColorfulEnchantment;
import com.Hileb.custom_colorful_enchantment.api.colors.IColorFactory;
import com.Hileb.custom_colorful_enchantment.api.colors.IColorInstance;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.JsonContext;

/**
 * @Project Colorful Enchantment
 * @Author Hileb
 * @Date 2023/9/24 11:12
 **/
public class ColorColorItem {
    public static final JsonContext CONTEXT=new JsonContext(ColorfulEnchantment.MODID);
    public static class Factory implements IColorFactory {
        public Factory(){

        }
        @Override
        public IColorInstance getInstance(JsonObject object) {
            return new ColorColorItem.Instance(
                    object,
                    JsonUtils.getInt(object,"color",0)
            );
        }
    }
    public static class Instance implements IColorInstance{
        public final Ingredient ingredient;
        public boolean isAll;
        public final int color;
        public Instance(JsonObject in,int colorIn){
            if (in.has("accept_all")) {
                isAll = true;
                ingredient=null;
            }
            else{
                isAll=false;
                ingredient=CraftingHelper.getIngredient(in.get("ingredient"),CONTEXT);
            }
            color=colorIn;
        }
        @Override
        public int getRGBColor(ItemStack stack) {
            return color;
        }
        @Override
        public boolean hasColor(ItemStack stack) {
            return ingredient==null || ingredient.apply(stack);
        }
    }
}
