package com.Hileb.custom_colorful_enchantment;

import com.Hileb.custom_colorful_enchantment.internal.instances.DeferredBusHandler;
import cpw.mods.modlauncher.api.IEnvironment;
import cpw.mods.modlauncher.api.ITransformationService;
import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.IncompatibleEnvironmentException;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

/**
 * @Project Custom-Colorful-Enchantment
 * @Author Hileb
 * @Date 2023/10/2 20:32
 **/
public class CCECoreService implements ITransformationService {
    public static final String NAME="Custom Colorful Enchantment";
    @Override
    public @NotNull String name() {
        return NAME;
    }

    @Override
    public void initialize(IEnvironment environment) {
        DeferredBusHandler.handle();
    }

    @Override
    public void onLoad(IEnvironment env, Set<String> otherServices) throws IncompatibleEnvironmentException {

    }

    @Override
    public @NotNull List<ITransformer> transformers() {
        return null;
    }
}
