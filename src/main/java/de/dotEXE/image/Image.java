package de.dotEXE.image;

import de.ender.core.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.lang.reflect.Field;

public class Image implements CommandExecutor{


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;
        boolean success = false;

        Player player = (Player) sender;

        ItemStack map = new ItemBuilder(Material.FILLED_MAP,1).setName("§bImage").build();
        MapMeta mapMeta = (MapMeta) map.getItemMeta();
        MapView mapView = Bukkit.createMap(player.getWorld());

        mapView.getRenderers().clear();

        switch (args[0]) {
            case "text":

                break;
            case "screen":
                mapView.addRenderer(new ImageRendererScreen());
                success = true;
                break;

            case "color":
                Color color = getColorFromString(args[1]);
                if(color == null) {
                    player.sendMessage("§cInvalid Color! Valid Colors: red,white,black,yellow,blue,cyan,darkGray,gray,lightGray,magenta,orange,pink");
                    break;
                }
                mapView.addRenderer(new ImageRendererColor(color));
                success = true;
                break;

            case "url":
            default:
                String imageURL = "https://avatars.githubusercontent.com/u/107443157?s=128&v=4";
                if(args.length == 1 && !args[0].isEmpty()) imageURL = args[0];
                if(args.length == 2 && !args[1].isEmpty()) imageURL = args[1];
                mapView.addRenderer(new ImageRendererURL(imageURL));
                success = true;
                break;
        }

        if(success) {
            mapMeta.setMapView(mapView);
            map.setItemMeta(mapMeta);

            player.getInventory().addItem(map);
        }
        return true;
    }

    private Color getColorFromString(String colorStr){
        Color color;
        try {
            Field field = Class.forName("java.awt.Color").getField(colorStr);
            color = (Color)field.get(null);
        } catch (Exception e) {
            color = null;
        }
        return color;
    }
}
