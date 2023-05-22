package de.dotEXE.image.ImageManagers;

import de.dotEXE.image.ImageRenderers.ImageRendererColor;
import de.dotEXE.image.Main;
import de.ender.core.CConfig;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.map.MapView;

import java.util.HashMap;
import java.util.Map;

public class ImageManagerColor implements Listener {

    private static ImageManagerColor instance = null;

    public static ImageManagerColor getInstance() {
        if (instance == null)
            instance = new ImageManagerColor();
        return instance;
    }

    private final CConfig dataFile = new CConfig("ColorMaps", Main.getPlugin());

    private final Map<Integer, String> savedImages = new HashMap<>();

    public void init() {
        Bukkit.getServer().getPluginManager().registerEvents(this, Main.getPlugin());
        loadImages();
    }


    @EventHandler
    public void onMapInitEvent(MapInitializeEvent event) {
        if (hasImage(event.getMap().getId())) {
            MapView view = event.getMap();
            view.getRenderers().clear();
            view.addRenderer(new ImageRendererColor(getArgs(view.getId())));
            view.setScale(MapView.Scale.FARTHEST);
            view.setTrackingPosition(false);
        }
    }


    public void saveImage(Integer id, String args) {
        getData().set("ids." + id, args);
        saveData();
    }


    private void loadImages() {
        if (getData().contains("ids"))
            getData().getConfigurationSection("ids").getKeys(false).forEach(id -> savedImages.put(Integer.parseInt(id), getData().getString("ids." + id)));
    }


    public boolean hasImage(int id) {
        return savedImages.containsKey(id);
    }


    public String getArgs(int id) {
        return savedImages.get(id);
    }


    public FileConfiguration getData() {
        return dataFile.getCustomConfig();
    }


    public void saveData() {
        dataFile.save();
    }
}
