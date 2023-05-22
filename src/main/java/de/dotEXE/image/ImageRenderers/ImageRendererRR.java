package de.dotEXE.image.ImageRenderers;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImageRendererRR extends MapRenderer {
    ArrayList<BufferedImage> frames = new ArrayList<>();
    int frame = 0;
    public ImageRendererRR(){
        File file = new File(System.getProperty("user.dir")+ "/plugins/meins_image/gif.gif");
        try {
            getFrames(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void render(@NotNull MapView map, @NotNull MapCanvas canvas, @NotNull Player player) {
        canvas.drawImage(0,0, MapPalette.resizeImage(frames.get(frame)));
        if(frame >= frames.size()-1) this.frame = 0;
        else frame++;
    }
    private void getFrames(File vid) throws IOException {
        try {
            ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();
            ImageInputStream stream = ImageIO.createImageInputStream(vid);
            reader.setInput(stream);

            int count = reader.getNumImages(true);
            for (int index = 0; index < count; index++) {
                BufferedImage frame = reader.read(index);
                frames.add(frame);
            }
        } catch (IOException ex) {
            // An I/O problem has occurred
        }

    }
}
