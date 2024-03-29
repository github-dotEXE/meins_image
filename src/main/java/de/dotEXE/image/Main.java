package de.dotEXE.image;

import de.dotEXE.image.ImageManagers.*;
import de.ender.core.Log;
import de.ender.core.TabCompleteManager;
import de.ender.core.UpdateChecker;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main plugin;

    public static Main getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        Log.log(ChatColor.AQUA + "Enabling meins_image...");
        String version = this.getDescription().getVersion();
        new UpdateChecker(this,"master").check().downloadLatestMeins();

        //initialization of commands
        getCommand("image").setExecutor(new Image());
        getCommand("image").setTabCompleter(new TabCompleteManager().addArgsXComps(0,
                new String[]{"url","text","color","screen","rickroll","shader"}).addArgsXComps(1,new String[]{"[url]","[text]","[Color]"}));

        ImageManagerScreen.getInstance().init();
        ImageManagerURL.getInstance().init();
        ImageManagerColor.getInstance().init();
        ImageManagerRR.getInstance().init();
        ImageManagerShader.getInstance().init();
    }

    @Override
    public void onDisable() {
        Log.log(ChatColor.AQUA + "Disabling meins_image...");
    }
}
