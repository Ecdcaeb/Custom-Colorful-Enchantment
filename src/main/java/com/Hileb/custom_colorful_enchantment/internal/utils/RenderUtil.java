package com.Hileb.custom_colorful_enchantment.internal.utils;

import com.Hileb.custom_colorful_enchantment.api.colors.IColorInstance;
import com.Hileb.custom_colorful_enchantment.api.registry.ColorInstancesRegistry;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Project Custom-Colorful-Enchantment
 * @Author Hileb
 * @Date 2023/10/2 20:30
 **/
public class RenderUtil {
    public static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");


    public RenderUtil() {
    }

    public static int getColor(ItemStack stack) {
        List<Integer> colors = new ArrayList();
        Iterator var2 = ColorInstancesRegistry.REGISTRY.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<ResourceLocation, IColorInstance> e = (Map.Entry)var2.next();
            IColorInstance iColorInstance = (IColorInstance)e.getValue();
            if (iColorInstance.hasColor(stack)) {
                colors.add(iColorInstance.getRGBColor(stack));
            }
        }

        if (colors.size() == 0) {
            return 0;
        } else {
            if (stack.func_77948_v() && colors.size() >= 2) {
                colors.remove(new Integer(8405195));
            }

            int color = 0;
            int size = colors.size();

            for(int i = 0; i < size; ++i) {
                color += (Integer)colors.get(i) / size;
            }

            return color;
        }
    }

    public static void onRender(RenderItem renderItem, ItemStack stack, IBakedModel model) {
        if (!model.func_188618_c()) {
            int color = getColor(stack);
            if (color != 0) {
                renderEffect(renderItem, model, color);
            }
        }

    }

    public static void renderEffect(RenderItem renderItem, IBakedModel model, int color) {
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b(-0.5F, -0.5F, -0.5F);
        GlStateManager.func_179132_a(false);
        GlStateManager.func_179143_c(514);
        GlStateManager.func_179140_f();
        GlStateManager.func_187401_a(SourceFactor.SRC_COLOR, DestFactor.ONE);
        renderItem.field_175057_n.func_110577_a(RES_ITEM_GLINT);
        GlStateManager.func_179128_n(5890);
        GlStateManager.func_179094_E();
        GlStateManager.func_179152_a(8.0F, 8.0F, 8.0F);
        float f = (float)(Minecraft.func_71386_F() % 3000L) / 3000.0F / 8.0F;
        GlStateManager.func_179109_b(f, 0.0F, 0.0F);
        GlStateManager.func_179114_b(-50.0F, 0.0F, 0.0F, 1.0F);
        renderItem.func_191967_a(model, color | -16777215, ItemStack.field_190927_a);
        GlStateManager.func_179121_F();
        GlStateManager.func_179094_E();
        GlStateManager.func_179152_a(8.0F, 8.0F, 8.0F);
        float f1 = (float)(Minecraft.func_71386_F() % 4873L) / 4873.0F / 8.0F;
        GlStateManager.func_179109_b(-f1, 0.0F, 0.0F);
        GlStateManager.func_179114_b(10.0F, 0.0F, 0.0F, 1.0F);
        renderItem.func_191967_a(model, color | -16777215, ItemStack.field_190927_a);
        GlStateManager.func_179121_F();
        GlStateManager.func_179128_n(5888);
        GlStateManager.func_187401_a(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.func_179145_e();
        GlStateManager.func_179143_c(515);
        GlStateManager.func_179132_a(true);
        renderItem.field_175057_n.func_110577_a(TextureMap.field_110575_b);
        GlStateManager.func_179121_F();
    }
}
