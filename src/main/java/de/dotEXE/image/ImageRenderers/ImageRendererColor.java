package de.dotEXE.image.ImageRenderers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.lang.reflect.Field;

public class ImageRendererColor extends MapRenderer {
    Color color;
    public ImageRendererColor(String color) {
        this.color = getColorFromString(color);
    }

    @Override
    public void render(@NotNull MapView map, @NotNull MapCanvas canvas, @NotNull Player player) {
        if(color == null) {
            PlayerInventory inv = player.getInventory();
            ItemStack[] contents = inv.getContents();
            for (ItemStack content : contents) {
                if (content != null && content.getType() == Material.FILLED_MAP && ((MapMeta) content.getItemMeta()).getMapView() != null && ((MapMeta) content.getItemMeta()).getMapView().equals(map)) {
                    inv.remove(content);
                    break;
                }
            }
            player.sendMessage("§cCouldn't load Image!");
            return;
        }
        for(int x =0;x <=128;x++) {
            for(int y =0;y <=128;y++) {
                canvas.setPixelColor(x, y, color);
            }
        }
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
