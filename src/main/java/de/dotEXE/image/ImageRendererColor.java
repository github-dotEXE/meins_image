package de.dotEXE.image;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class ImageRendererColor extends MapRenderer {
    Color color;
    public ImageRendererColor(Color color) {
        this.color = color;
    }

    @Override
    public void render(@NotNull MapView map, @NotNull MapCanvas canvas, @NotNull Player player) {
        for(int x =0;x <=128;x++) {
            for(int y =0;y <=128;y++) {
                canvas.setPixelColor(x, y, color);
            }
        }
    }
}
