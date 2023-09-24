package com.Hileb.custom_colorful_enchantment;

import com.Hileb.custom_colorful_enchantment.internal.instances.DeferredBusHandler;
import com.google.common.eventbus.EventBus;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Project Colorful Enchantment
 * @Author Hileb
 * @Date 2023/9/24 10:00
 **/
@IFMLLoadingPlugin.MCVersion(ForgeVersion.mcVersion)
@IFMLLoadingPlugin.Name(ColorfulEnchantment.NAME)
public class ColorfulEnchantment implements IEarlyMixinLoader, IFMLLoadingPlugin {
    public static final String MODID="custom_colorful_enchantment";
    public static final String NAME="Custom Colorful Enchantment";
    public static final String VERSION="1.0.0";
    public ColorfulEnchantment(){
    }
    @Override
    public List<String> getMixinConfigs() {
        return Collections.singletonList("mixin.custom_colorful_enchantment.early.json");
    }
    @Override
    public String getModContainerClass() {
        return "com.Hileb.custom_colorful_enchantment.ColorfulEnchantment$Container";
    }
    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }
    @Override
    public void injectData(Map<String, Object> data) {

    }
    @Override
    public String getAccessTransformerClass() {
        return null;
    }
    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }
    @SuppressWarnings("unused")
    public static class Container extends DummyModContainer {
        public Container() {
            super(new ModMetadata());
            ModMetadata meta = this.getMetadata();
            meta.modId = MODID;
            meta.name =  NAME;
            meta.description = "a mod that enable modder and player adjust the color of enchantment.";
            meta.version = VERSION;
            meta.url="https://legacy.curseforge.com/minecraft/mc-mods/colorful-enchantment";
            meta.authorList.add("Hileb");
        }

        public boolean registerBus(EventBus bus, LoadController controller) {
            bus.register(this);
            bus.register(new DeferredBusHandler());
            return true;
        }
    }
}
