package de.dotEXE.image.ImageManagers;

import de.dotEXE.image.ImageRenderers.ImageRendererShader;
import de.dotEXE.image.Main;
import de.ender.core.CConfig;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.map.MapView;

import java.util.HashSet;

public class ImageManagerShader implements Listener {

    private static ImageManagerShader instance = null;

    public static ImageManagerShader getInstance() {
        if (instance == null)
            instance = new ImageManagerShader();
        return instance;
    }

    private final CConfig dataFile = new CConfig("ShaderMaps", Main.getPlugin());

    private final HashSet<Integer> savedImages = new HashSet<Integer>() {};

    public void init() {
        Bukkit.getServer().getPluginManager().registerEvents(this, Main.getPlugin());
        loadImages();
    }


    @EventHandler
    public void onMapInitEvent(MapInitializeEvent event) {
        if (hasImage(event.getMap().getId())) {
            MapView view = event.getMap();
            view.getRenderers().clear();
            view.addRenderer(new ImageRendererShader());
            view.setScale(MapView.Scale.FARTHEST);
            view.setTrackingPosition(false);
        }
    }


    public void saveImage(Integer id) {
        getData().set("ids." + id, true);
        saveData();
    }


    private void loadImages() {
        if (getData().contains("ids"))
            getData().getConfigurationSection("ids").getKeys(false).forEach(id -> savedImages.add(Integer.parseInt(id)));
    }


    public boolean hasImage(int id) {
        return savedImages.contains(id);
    }


    public FileConfiguration getData() {
        return dataFile.getCustomConfig();
    }


    public void saveData() {
        dataFile.save();
    }
}
