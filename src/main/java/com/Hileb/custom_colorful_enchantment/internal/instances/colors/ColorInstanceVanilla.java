package com.Hileb.custom_colorful_enchantment.internal.instances.colors;

import com.Hileb.custom_colorful_enchantment.api.colors.IColorInstance;
import net.minecraft.item.ItemStack;

/**
 * @Project Colorful Enchantment
 * @Author Hileb
 * @Date 2023/9/24 10:58
 **/
public class ColorInstanceVanilla implements IColorInstance {

    @Override
    public int getRGBColor(ItemStack stack) {
        return 0x8040CB;
    }

    @Override
    public boolean hasColor(ItemStack stack) {
        return stack.hasEffect();
    }
}
