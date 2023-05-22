package de.dotEXE.image;

import de.dotEXE.image.ImageManagers.ImageManagerColor;
import de.dotEXE.image.ImageManagers.ImageManagerRR;
import de.dotEXE.image.ImageManagers.ImageManagerScreen;
import de.dotEXE.image.ImageManagers.ImageManagerURL;
import de.dotEXE.image.ImageRenderers.ImageRendererColor;
import de.dotEXE.image.ImageRenderers.ImageRendererRR;
import de.dotEXE.image.ImageRenderers.ImageRendererScreen;
import de.dotEXE.image.ImageRenderers.ImageRendererURL;
import de.ender.core.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;

public class Image implements CommandExecutor{


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;
        boolean success = false;

        Player player = (Player) sender;

        if(!player.hasPermission("image.command")) return false;

        ItemStack map = new ItemBuilder(Material.FILLED_MAP,1).setName("§bImage").build();
        MapMeta mapMeta = (MapMeta) map.getItemMeta();
        MapView mapView = Bukkit.createMap(player.getWorld());

        mapView.getRenderers().clear();
        MapRenderer renderer = null;
        int id = mapView.getId();
        switch (args[0]) {
            case "text":

                break;
            case "rickroll":
                renderer = new ImageRendererRR();
                success = true;
                ImageManagerRR.getInstance().saveImage(id);
                break;
            case "screen":
                ImageManagerScreen.getInstance().saveImage(id);
                renderer = new ImageRendererScreen();
                success = true;
                break;

            case "color":
                ImageManagerColor.getInstance().saveImage(id,args[1]);
                renderer = new ImageRendererColor(args[1]);
                success = true;
                break;

            case "url":
            default:
                String imageURL = "https://avatars.githubusercontent.com/u/107443157?s=128&v=4";
                if(args.length == 1 && !args[0].isEmpty()) imageURL = args[0];
                if(args.length == 2 && !args[1].isEmpty()) imageURL = args[1];
                ImageManagerURL.getInstance().saveImage(id,imageURL);
                renderer = new ImageRendererURL(imageURL);
                success = true;
                break;
        }

        if(success) {
            mapView.addRenderer(renderer);

            mapMeta.setMapView(mapView);
            map.setItemMeta(mapMeta);

            player.getInventory().addItem(map);
        }
        return true;
    }
}
