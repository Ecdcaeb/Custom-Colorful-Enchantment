package com.Hileb.custom_colorful_enchantment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.minecraft.launchwrapper.Launch;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project Redirectionor
 * @Author Hileb
 * @Date 2023/9/9 18:14
 **/
public class ColorfulEnchantmentConfig {
    public static final Gson GSON=(new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
    public static Config instance=new Config();
    public static void initConfig(){
        File gameRunRoot= Launch.minecraftHome;
        File config=new File(gameRunRoot,"config");
        if (!config.exists()){
            config.mkdir();
        }
        File file=new File(config,ColorfulEnchantment.MODID+"_cfg.json");
        if (!file.exists()){
            try {
                file.createNewFile();
                PrintWriter pw = new PrintWriter(file,"UTF-8");
                pw.println(GSON.toJson(instance));
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Config cConfig=GSON.fromJson(new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8),Config.class);
            instance=cConfig;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static class Config{
        public List<JsonObject> colors=new ArrayList<>();
        public Config(){

        }
    }
}
