package com.Hileb.custom_colorful_enchantment.internal;

import cpw.mods.modlauncher.serviceapi.ILaunchPluginService;
import org.objectweb.asm.Type;

import java.util.EnumSet;

/**
 * @Project Custom-Colorful-Enchantment
 * @Author Hileb
 * @Date 2023/10/2 20:37
 **/
public class CEERenderTransformer implements ILaunchPluginService {
    public static Logger LOGGER = LogManager.getLogger(NPOPLaunchPluginService.class);

    @Override
    public String name() {
        return "NPOP LaunchPluginService";
    }

    public NPOPLaunchPluginService(){
        LOGGER.debug("If you have installed NPOP mod(modid:noprivateorprotected),you will see this.");
    }

    @Override
    public EnumSet<Phase> handlesClass(Type classType, final boolean isEmpty) {
        return EnumSet.of(Phase.AFTER);
    }

    @Override
    public boolean processClass(final Phase phase, ClassNode classNode, final Type classType, String reason) {
        if (phase==Phase.BEFORE){
}
