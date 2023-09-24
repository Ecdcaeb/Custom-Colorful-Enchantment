package com.Hileb.custom_colorful_enchantment.api.colors;

import net.minecraft.item.ItemStack;

/**
 * @Project Colorful Enchantment
 * @Author Hileb
 * @Date 2023/9/24 10:39
 **/
public interface IColorInstance {
    int getRGBColor(ItemStack stack);
    boolean hasColor(ItemStack stack);
}
