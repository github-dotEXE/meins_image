package de.dotEXE.image;

import de.ender.core.Log;
import de.ender.core.TabCompleteManager;
import de.ender.core.UpdateChecker;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Log.log(ChatColor.AQUA + "Enabling meins_image...");
        String version = this.getDescription().getVersion();
        UpdateChecker.check(version,"github-dotEXE","meins_image");

        //initialization of commands
        getCommand("image").setExecutor(new Image());
        getCommand("image").setTabCompleter(new TabCompleteManager().addArgsXComps(0,
                new String[]{"url","text","color","screen"}).addArgsXComps(1,new String[]{"[url]","[text]","[Color]"}));
    }

    @Override
    public void onDisable() {
        Log.log(ChatColor.AQUA + "Disabling meins_image...");
    }
}
