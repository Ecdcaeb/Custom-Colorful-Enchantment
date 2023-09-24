package com.Hileb.custom_colorful_enchantment.internal.instances.colors;

import com.Hileb.custom_colorful_enchantment.api.colors.IColorInstance;
import net.minecraft.item.ItemStack;

/**
 * @Project Colorful Enchantment
 * @Author Hileb
 * @Date 2023/9/24 10:58
 **/
public class ColorInstanceNBT implements IColorInstance {
    public static final String KEY="enchantment_color";

    @Override
    public int getRGBColor(ItemStack stack) {
        return stack.getTagCompound().getInteger(KEY);
    }

    @Override
    public boolean hasColor(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().hasKey(KEY);
    }
}
