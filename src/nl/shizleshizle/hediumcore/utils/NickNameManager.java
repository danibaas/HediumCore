package nl.shizleshizle.hediumcore.utils;

import nl.shizleshizle.hediumcore.Main;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class NickNameManager {

    public static String getPlayerFromNick(String nick) {
        String toReturn = "";
        try {
            Main.sql.getReady();
            Statement query = Main.sql.getConnection().createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM Account WHERE nickname='" + nick + "';");
            String playerId = "";
            if (rs.next()) {
                playerId = rs.getString("uuid");
            }
            rs.close();
            query.close();
            UUID id = UUID.fromString(playerId);
            toReturn = Bukkit.getOfflinePlayer(id).getName();
        } catch (SQLException e) {
            Bukkit.getLogger().info("Hedium Core: Player From Nick >> Could not get nickname: " + e);
        }
        return toReturn;
    }
}
