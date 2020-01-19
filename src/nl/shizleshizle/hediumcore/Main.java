package nl.shizleshizle.hediumcore;

import nl.shizleshizle.hediumcore.sql.SQLManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends JavaPlugin {
    // Prefix
    public static String prefix = ChatColor.GOLD + "<<{ " + ChatColor.YELLOW + "Hedium" + ChatColor.GOLD + " }>> "
            + ChatColor.YELLOW;

    // Utilities
    public static Plugin p;
    public static SQLManager sql;
    public static ConfigManager cm;

    // Database vars
    public static String databaseHost;
    public static int databasePort;
    public static String databaseName;
    public static String databaseUsername;
    public static String databasePassword;

    // Misc vars
    public static int teleportTime;

    // arrays & lists
    public static ArrayList<String> frozen = new ArrayList<>();
    public static ArrayList<String> teleportToggle = new ArrayList<>();
    public static ArrayList<String> afk = new ArrayList<>();
    public static ArrayList<String> godMode = new ArrayList<>();
    public static ArrayList<String> vanished = new ArrayList<>();
    public static HashMap<String, Location> back = new HashMap<>();

    public void onEnable() {
        long startTime = System.currentTimeMillis();
        System.out.println("Hedium Core >> Starting core...");
        p = this;
        sql = SQLManager.getInstance();
        sql.setup();
        cm = ConfigManager.getInstance();
        cm.setup(p);
        setupUtils();
        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Hedium Core >> Startup has been completed (" + totalTime + " ms)");
    }

    public void onDisable() {

    }

    public void registerCommands() {

    }

    public void registerEvents() {

    }

    public static void setupUtils() {
        teleportTime = cm.getConfig().getInt("settings.teleportWaitTime");
    }
}
