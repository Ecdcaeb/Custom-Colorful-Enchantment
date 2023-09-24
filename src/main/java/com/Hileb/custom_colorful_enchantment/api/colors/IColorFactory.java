package com.Hileb.custom_colorful_enchantment.api.colors;

import com.google.gson.JsonObject;

/**
 * @Project Colorful Enchantment
 * @Author Hileb
 * @Date 2023/9/24 10:40
 **/
public interface IColorFactory {
    IColorInstance getInstance(JsonObject object);
}
