package nl.shizleshizle.hediumcore;

import nl.shizleshizle.hediumcore.permissions.PermGroup;
import nl.shizleshizle.hediumcore.permissions.Permissions;
import nl.shizleshizle.hediumcore.sql.SQLManager;
import nl.shizleshizle.hediumcore.utils.CommandMaster;
import nl.shizleshizle.hediumcore.utils.Cooldowns;
import nl.shizleshizle.hediumcore.utils.EventMaster;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends JavaPlugin {
    // Prefix
    public static String prefix = ChatColor.GOLD + "<<{ " + ChatColor.YELLOW + "Hedium" + ChatColor.GOLD + " }>> "
            + ChatColor.YELLOW;

    // Utilities
    public static JavaPlugin p;
    public static SQLManager sql;
    public static ConfigManager cm;
    public static CommandMaster cmd = new CommandMaster();
    public static EventMaster events = new EventMaster();

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
    public static HashMap<String, Long> muted = new HashMap<>();
    public static HashMap<String, Location> back = new HashMap<>();

    public void onEnable() {
        long startTime = System.currentTimeMillis();
        System.out.println("Hedium Core >> Starting core...");
        p = this;
        cm = ConfigManager.getInstance();
        cm.setup(p);
        setupUtils();
        sql = SQLManager.getInstance();
        sql.setup();
        PermGroup.updateToDatabase();
        Permissions.addPermissions(PermGroup.MEMBER, PermGroup.RANKED);
        Permissions.addPermissions(PermGroup.RANKED, PermGroup.MODERATOR);
        Permissions.addPermissions(PermGroup.MODERATOR, PermGroup.ADMIN);
        Permissions.addPermissions(PermGroup.ADMIN, PermGroup.LEAD_DEVELOPER);
        cmd.register();
        events.register();
        Cooldowns.runCooldown();
        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Hedium Core >> Startup has been completed (" + totalTime + " ms)");
    }

    public void onDisable() {
        long startTime = System.currentTimeMillis();
        System.out.println("Hedium Core >> Disabling core...");
        try {
            sql.closeConnection();
        } catch (SQLException e) {
            Bukkit.getLogger().info("Hedium Core: SQL Connection >> Could not close connection: " + e);
        }
        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Hedium Core >> Core has been disabled (" + totalTime + " ms)");
    }

    public static void setupUtils() {
        teleportTime = cm.getConfig().getInt("settings.teleportWaitTime");
        databaseHost = cm.getConfig().getString("settings.database.host");
        databasePort = cm.getConfig().getInt("settings.database.port");
        databaseName = cm.getConfig().getString("settings.database.name");
        databaseUsername = cm.getConfig().getString("settings.database.userName");
        databasePassword = cm.getConfig().getString("settings.database.password");
    }
}
