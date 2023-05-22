package de.dotEXE.image.ImageManagers;

import de.dotEXE.image.ImageRenderers.ImageRendererRR;
import de.ender.core.CConfig;
import de.dotEXE.image.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.map.MapView;

import java.util.ArrayList;

public class ImageManagerRR implements Listener {

    private static ImageManagerRR instance = null;
    private final ArrayList<Integer> savedMaps = new ArrayList<>();

    public static ImageManagerRR getInstance() {
        if (instance == null)
            instance = new ImageManagerRR();
        return instance;
    }

    private final CConfig dataFile = new CConfig("RRMaps", Main.getPlugin());

    public void init() {
        Bukkit.getServer().getPluginManager().registerEvents(this, Main.getPlugin());
        loadImages();
    }


    @EventHandler
    public void onMapInitEvent(MapInitializeEvent event) {
        if (hasImage(event.getMap().getId())) {
            MapView view = event.getMap();
            view.getRenderers().clear();
            view.addRenderer(new ImageRendererRR());
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
            getData().getConfigurationSection("ids").getKeys(false).forEach(id -> savedMaps.add(Integer.parseInt(id)));
    }


    public boolean hasImage(int id) {
        return savedMaps.contains(id);
    }


    public FileConfiguration getData() {
        return dataFile.getCustomConfig();
    }


    public void saveData() {
        dataFile.save();
    }
}
