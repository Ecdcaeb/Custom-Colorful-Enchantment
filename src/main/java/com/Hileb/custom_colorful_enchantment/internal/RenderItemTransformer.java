package com.Hileb.custom_colorful_enchantment.internal;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.util.ListIterator;

/**
 * @Project Colorful Enchantment
 * @Author Hileb
 * @Date 2023/9/30 15:24
 **/
@SuppressWarnings("unused")
public class RenderItemTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if ("net.minecraft.client.renderer.RenderItem".equals(transformedName)){
            ClassReader classReader=new ClassReader(basicClass);
            ClassNode cn=new ClassNode();
            classReader.accept(cn,0);
            for(MethodNode mn:cn.methods){
                //in srg
                {
                    if ("func_180454_a".equals(mn.name)){
                        //renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V
                        renderItemASM(mn);
                    }else if ("func_191966_a".equals(mn.name)){//renderE
                        //renderEffect
                        renderEffectASM(mn);
                    }
                }
                //in notch
                {
                    if ("a".equals(mn.name)){
                        if ("(Laip;Lcfy;)V".equals(mn.desc)){
                            renderItemASM(mn);
                        }else if ("(Lcfy;)V".equals(mn.desc)){
                            renderEffectASM(mn);
                        }
                    }
                }
                //in mcp
                {
                    //renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V
                    if ("renderItem".equals(mn.name) && "(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V".equals(mn.desc)){
                        renderItemASM(mn);
                    }
                    //renderEffect
                    if ("renderEffect".equals(mn.name)){
                        renderEffectASM(mn);
                    }
                }
            }
            ClassWriter classWriter=new ClassWriter(classReader,ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            cn.accept(classWriter);
            return classWriter.toByteArray();
        }
        else return basicClass;
    }
    public static void renderItemASM(MethodNode mn){

        /*
            ALOAD 0
    ALOAD 1
    ALOAD 2
    INVOKESTATIC com/Hileb/custom_colorful_enchantment/internal/utils/RenderUtil.onRender (Lnet/minecraft/client/renderer/RenderItem;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V
        */
        AbstractInsnNode returnNote=null;
        ListIterator<AbstractInsnNode> iterator= mn.instructions.iterator();
        while (iterator.hasNext()){
            returnNote=iterator.next();
            if (returnNote.getOpcode()== Opcodes.RETURN)break;
        }
        InsnList list=new InsnList();
        list.add(new VarInsnNode(Opcodes.ALOAD,0));
        list.add(new VarInsnNode(Opcodes.ALOAD,1));
        list.add(new VarInsnNode(Opcodes.ALOAD,2));
        list.add(new MethodInsnNode(Opcodes.INVOKESTATIC,"com/Hileb/custom_colorful_enchantment/internal/utils/RenderUtil","onRender","(Lnet/minecraft/client/renderer/RenderItem;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V"));
        mn.instructions.insertBefore(returnNote,list);
    }
    public static void renderEffectASM(MethodNode mn){
        mn.instructions.clear();
        mn.instructions.add(new InsnNode(Opcodes.RETURN));
    }
}
