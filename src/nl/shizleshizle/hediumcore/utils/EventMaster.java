package nl.shizleshizle.hediumcore.utils;

import nl.shizleshizle.hediumcore.Main;
import nl.shizleshizle.hediumcore.listeners.PlayerChat;
import nl.shizleshizle.hediumcore.listeners.PlayerJoin;
import nl.shizleshizle.hediumcore.listeners.PlayerQuit;
import nl.shizleshizle.hediumcore.listeners.PlayerTeleport;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class EventMaster {
    private static EventMaster instance;

    public static EventMaster getInstance() {
        return instance;
    }

    public void register() {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new PlayerChat(), Main.p);
        pm.registerEvents(new PlayerJoin(), Main.p);
        pm.registerEvents(new PlayerQuit(), Main.p);
        pm.registerEvents(new PlayerTeleport(), Main.p);
    }
}
