package com.Hileb.custom_colorful_enchantment.internal.utils;

import com.Hileb.custom_colorful_enchantment.api.colors.IColorInstance;
import com.Hileb.custom_colorful_enchantment.api.registry.ColorInstancesRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RenderUtil {
    public static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");

    public static int getColor(ItemStack stack){
        long sumColor=0;
        int colorCount=0;
        for(Map.Entry<ResourceLocation,IColorInstance> e: ColorInstancesRegistry.REGISTRY.entrySet()){
            IColorInstance iColorInstance=e.getValue();
            if (iColorInstance.hasColor(stack)){
                sumColor+=iColorInstance.getRGBColor(stack);
                colorCount++;
            }
        }
        if (stack.hasEffect() && colorCount>=2){
            colorCount--;
            sumColor-=0x8040CB;
        }
        if (colorCount==0)return 0;
        else return (int)sumColor/colorCount;
    }

    @SuppressWarnings("unused")
    public static void onRender(RenderItem renderItem,ItemStack stack, IBakedModel model){
        if (!model.isBuiltInRenderer())
        {
            int color= getColor(stack);
            if (color!=0){
                renderEffect(renderItem,model,color);
            }
        }
    }

    public static void renderEffect(RenderItem renderItem,IBakedModel model,int color)
    {
        //System.out.println("aaa3");
        GlStateManager.pushMatrix();
        GlStateManager.translate(-0.5F, -0.5F, -0.5F);
        GlStateManager.depthMask(false);
        GlStateManager.depthFunc(514);
        GlStateManager.disableLighting();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_COLOR, GlStateManager.DestFactor.ONE);
        //at :
        //public+f net.minecraft.client.renderer.RenderItem field_175057_n #textureManager
        renderItem.textureManager.bindTexture(RES_ITEM_GLINT);

        GlStateManager.matrixMode(5890);
        GlStateManager.pushMatrix();
        GlStateManager.scale(8.0F, 8.0F, 8.0F);
        float f = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F / 8.0F;
        GlStateManager.translate(f, 0.0F, 0.0F);
        GlStateManager.rotate(-50.0F, 0.0F, 0.0F, 1.0F);
        //at:
        //public net.minecraft.client.renderer.RenderItem func_191967_a(Lnet.minecraft.client.renderer.block.model.IBakedModel;ILnet.minecraft.item.ItemStack;)V #net.minecraft.client.renderer.RenderItem.renderModel(Lnet.minecraft.client.renderer.block.model.IBakedModel;ILnet.minecraft.item.ItemStack;)V

        renderItem.renderModel(model, color|-0xFFFFFF,ItemStack.EMPTY);//因为是光所以是EMPTY
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.scale(8.0F, 8.0F, 8.0F);
        float f1 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F / 8.0F;
        GlStateManager.translate(-f1, 0.0F, 0.0F);
        GlStateManager.rotate(10.0F, 0.0F, 0.0F, 1.0F);
        //at:
        //public net.minecraft.client.renderer.RenderItem func_191967_a(Lnet.minecraft.client.renderer.block.model.IBakedModel;ILnet.minecraft.item.ItemStack;)V #net.minecraft.client.renderer.RenderItem.renderModel(Lnet.minecraft.client.renderer.block.model.IBakedModel;ILnet.minecraft.item.ItemStack;)V
        renderItem.renderModel(model, color|-0xFFFFFF,ItemStack.EMPTY);
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(5888);
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.enableLighting();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);
        //at :
        //public+f net.minecraft.client.renderer.RenderItem field_175057_n #textureManager
        renderItem.textureManager.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        GlStateManager.popMatrix();
    }
}
