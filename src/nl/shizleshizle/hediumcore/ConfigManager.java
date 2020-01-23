package nl.shizleshizle.hediumcore;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    public static boolean isSetup = false;
    private static ConfigManager instance = new ConfigManager();
    private FileConfiguration config;
    private File cfile;

    public static ConfigManager getInstance() {
        return instance;
    }

    void setup(Plugin p) {
        if (!p.getDataFolder().exists()) {
            if (p.getDataFolder().mkdir()) {
                Bukkit.getServer().getLogger().info("Hedium Core: Config >> Datafolder created!");
            } else {
                Bukkit.getServer().getLogger().info("Hedium Core: Config >> Datafolder creation failed.");
            }
        }
        cfile = new File(p.getDataFolder(), "config.yml");
        if (!cfile.exists()) {
            try {
                if (cfile.createNewFile()) {
                    Bukkit.getServer().getLogger().info("Hedium Core: Config >> Config.yml created!");
                }
                config = YamlConfiguration.loadConfiguration(cfile);
                isSetup = true;
            } catch (IOException e) {
                Bukkit.getServer().getLogger().info("Hedium Core: Config >> Config.yml creation failed.");
            }
        } else {
            config = p.getConfig();
            config.options().copyDefaults(true);
            reloadConfig();
            setupConfig();
            saveConfig();
            isSetup = true;
        }
    }

    private void setupConfig() {
        if (!config.contains("settings.general.teleportWaitTime")) {
            config.set("settings.general.teleportWaitTime", 5);
        }
        if (!config.contains("settings.database.host")) {
            config.set("settings.database.host", "localhost");
        }
        if (!config.contains("settings.database.port")) {
            config.set("settings.database.port", 3306);
        }
        if (!config.contains("settings.database.name")) {
            config.set("settings.database.name", "mydb");
        }
        if (!config.contains("settings.database.userName")) {
            config.set("settings.database.userName", "root");
        }
        if (!config.contains("settings.database.password")) {
            config.set("settings.database.password", "");
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void saveConfig() {
        try {
            config.save(cfile);
        } catch (IOException e) {
            Bukkit.getServer().getLogger().info("Hedium Core: Config >> Could not save config.yml.");
        }
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(cfile);
        Main.setupUtils();
    }
}
