package com.Hileb.custom_colorful_enchantment.internal.mixin;

import com.Hileb.custom_colorful_enchantment.internal.utils.RenderUtil;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/8/27 11:02
 **/
@Mixin(RenderItem.class)
public class MixinRenderItem{
    @Inject(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V",at=@At("RETURN"))
    public void onRenderEffect(ItemStack stack, IBakedModel model, CallbackInfo ci){
        if (!model.isBuiltInRenderer())
        {
            RenderUtil.onRender((RenderItem)(Object)this,stack,model);
        }
    }
//    @Inject(method = "renderModel(Lnet/minecraft/client/renderer/block/model/IBakedModel;Lnet/minecraft/item/ItemStack;)V",at=@At("RETURN"))
//    public void onRenderEffect(IBakedModel model, ItemStack stack, CallbackInfo ci){
//        RenderUtil.onRender((RenderItem)(Object)this,stack,model);
//    }

    @Inject(method = "renderEffect",at =@At("HEAD"),cancellable = true)
    public void blockRenderEffect(IBakedModel model, CallbackInfo ci){
        ci.cancel();
    }
}
