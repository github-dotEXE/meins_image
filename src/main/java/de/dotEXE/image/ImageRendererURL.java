package de.dotEXE.image;

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

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public class ImageRendererURL extends MapRenderer {

    private final String imageURL;

    public ImageRendererURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public void render(@NotNull MapView map, @NotNull MapCanvas canvas, @NotNull Player player) {
        try {
            URL url = new URL(imageURL);
            canvas.drawImage(0,0, MapPalette.resizeImage(ImageIO.read(url)));
        } catch (IOException e) {
            PlayerInventory inv = player.getInventory();
            ItemStack[] contents = inv.getContents();
            for(ItemStack content : contents){
                if(content != null && content.getType() == Material.FILLED_MAP&& ((MapMeta) content.getItemMeta()).getMapView() != null && ((MapMeta) content.getItemMeta()).getMapView().equals(map)) {
                    inv.remove(content);
                    break;
                }
            }
            player.sendMessage("§cCouldn't load Image!");
        }
    }
}
