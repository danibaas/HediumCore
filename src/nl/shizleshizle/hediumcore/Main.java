package nl.shizleshizle.hediumcore;

import nl.shizleshizle.hediumcore.sql.SQLManager;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends JavaPlugin {
    // Database vars
    public static String databaseHost;
    public static int databasePort;
    public static String databaseName;
    public static String databaseUsername;
    public static String databasePassword;

    // Misc vars
    public static SQLManager sql;

    // arrays & lists
    public static ArrayList<String> frozen = new ArrayList<>();
    public static HashMap<String, Location> back = new HashMap<>();

    public void onEnable() {
        long startTime = System.currentTimeMillis();
        System.out.println("Hedium Core >> Starting core...");
        sql = SQLManager.getInstance();
        sql.setup();
        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Hedium Core >> Startup has been completed (" + totalTime + " ms)");
    }

    public void onDisable() {

    }

    public void registerCommands() {

    }

    public void registerEvents() {

    }
}
