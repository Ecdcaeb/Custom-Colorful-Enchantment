package com.Hileb.custom_colorful_enchantment.internal;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.*;

import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * @Project Custom-Colorful-Enchantment
 * @Author Hileb
 * @Date 2023/10/2 20:18
 **/
public class RenderItemTransformer implements IClassTransformer {
    public RenderItemTransformer() {
    }

    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (!"net.minecraft.client.renderer.RenderItem".equals(transformedName)) {
            return basicClass;
        } else {
            ClassReader classReader = new ClassReader(basicClass);
            ClassNode cn = new ClassNode();
            classReader.accept(cn, 0);

            Iterator var6;
            FieldNode fn;
            for(var6 = cn.fields.iterator(); var6.hasNext(); fn.access = getTrueAccess(fn.access)) {
                fn = (FieldNode)var6.next();
            }

            var6 = cn.methods.iterator();

            while(var6.hasNext()) {
                MethodNode mn = (MethodNode)var6.next();
                mn.access = getTrueAccess(mn.access);
                if ("func_180454_a".equals(mn.name)) {
                    renderItemASM(mn);
                } else if ("func_191966_a".equals(mn.name)) {
                    renderEffectASM(mn);
                }

                if ("a".equals(mn.name)) {
                    if ("(Laip;Lcfy;)V".equals(mn.desc)) {
                        renderItemASM(mn);
                    } else if ("(Lcfy;)V".equals(mn.desc)) {
                        renderEffectASM(mn);
                    }
                }

                if ("renderItem".equals(mn.name) && "(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V".equals(mn.desc)) {
                    renderItemASM(mn);
                }

                if ("renderEffect".equals(mn.name)) {
                    renderEffectASM(mn);
                }
            }

            ClassWriter classWriter = new ClassWriter(classReader, 3);
            cn.accept(classWriter);
            return classWriter.toByteArray();
        }
    }

    public static void renderItemASM(MethodNode mn) {
        AbstractInsnNode returnNote = null;
        ListIterator<AbstractInsnNode> iterator = mn.instructions.iterator();

        while(iterator.hasNext()) {
            returnNote = (AbstractInsnNode)iterator.next();
            if (returnNote.getOpcode() == 177) {
                break;
            }
        }

        InsnList list = new InsnList();
        list.add(new VarInsnNode(25, 0));
        list.add(new VarInsnNode(25, 1));
        list.add(new VarInsnNode(25, 2));
        list.add(new MethodInsnNode(184, "com/Hileb/custom_colorful_enchantment/internal/utils/RenderUtil", "onRender", "(Lnet/minecraft/client/renderer/RenderItem;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V"));
        mn.instructions.insertBefore(returnNote, list);
    }

    public static void renderEffectASM(MethodNode mn) {
        mn.instructions.clear();
        mn.instructions.add(new InsnNode(177));
    }

    public static int getTrueAccess(int mod) {
        int a = mod;
        if (Modifier.isPrivate(mod)) {
            a = mod & -3;
            a |= 1;
        } else if (Modifier.isProtected(mod)) {
            a = mod & -5;
            a |= 1;
        }

        if (Modifier.isFinal(a)) {
            a &= -17;
        }

        if (!Modifier.isPublic(a)) {
            a |= 1;
        }

        return a;
    }
}