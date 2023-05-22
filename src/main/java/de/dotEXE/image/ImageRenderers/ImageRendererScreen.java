package de.dotEXE.image.ImageRenderers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageRendererScreen extends MapRenderer {
    public ImageRendererScreen() {
    }
    @Override
    public void render(@NotNull MapView map, @NotNull MapCanvas canvas, @NotNull Player player) {
        try {
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            canvas.drawImage(0,0, MapPalette.resizeImage(image));
        } catch (AWTException e) {
            player.sendMessage("§cCouldn't load Image!");
            PlayerInventory inv = player.getInventory();
            ItemStack[] contents = inv.getContents();
            for(ItemStack content : contents){
                if(content != null && content.getType() == Material.FILLED_MAP&& ((MapMeta) content.getItemMeta()).getMapView() != null && ((MapMeta) content.getItemMeta()).getMapView().equals(map)) {
                    inv.remove(content);
                    break;
                }
            }
        }
    }
}
