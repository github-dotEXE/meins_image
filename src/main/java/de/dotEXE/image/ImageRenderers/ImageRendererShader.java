package de.dotEXE.image.ImageRenderers;

import de.dotEXE.image.shader.RainbowShader;
import de.dotEXE.image.shader.Shader;
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
import java.util.Vector;

public class ImageRendererShader extends MapRenderer {

    private int iTime = 0;

    @Override
    public void render(@NotNull MapView map, @NotNull MapCanvas canvas, @NotNull Player player) {
        Shader shader = new RainbowShader();
        for (int i = 0; i <= 128; i++) {
            for (int j = 0; j <= 128; j++) {
                Vector<Integer> fragCoord = new Vector<>();
                fragCoord.set(0,i);
                fragCoord.set(1,j);
                canvas.setPixelColor(i,j,shader.main(fragCoord,iTime));
                iTime+=1;
            }
        }
    }
}
