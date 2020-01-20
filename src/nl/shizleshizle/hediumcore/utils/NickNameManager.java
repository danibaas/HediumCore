package nl.shizleshizle.hediumcore.utils;

import nl.shizleshizle.hediumcore.Main;
import nl.shizleshizle.hediumcore.objects.User;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.UUID;

public class NickNameManager {
    public static HashMap<UUID, String> nicks = new HashMap<>();

    public boolean isLoaded(User p) {
        return nicks.containsKey(p.getUniqueId());
    }

    public static void saveAll() {
        for (UUID playerId : nicks.keySet()) {
            User p = new User(Bukkit.getOfflinePlayer(playerId).getPlayer());
            p.saveNick();
        }
    }

    public static String getPlayerFromNick(String nick) {
        String toReturn = "";
        try {
            Statement query = Main.sql.getConnection().createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM Nickname WHERE nickname='" + nick + "';");
            String playerId = "";
            if (rs.next()) {
                playerId = rs.getString("uuid");
            }
            UUID id = UUID.fromString(playerId);
            toReturn = Bukkit.getOfflinePlayer(id).getName();
        } catch(SQLException e) {
            Bukkit.getLogger().info("Hedium Core: Player From Nick >> Could not get nickname: " + e);
        }
        return toReturn;
    }
}
