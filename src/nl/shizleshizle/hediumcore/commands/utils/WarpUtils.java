package nl.shizleshizle.hediumcore.commands.utils;

import nl.shizleshizle.hediumcore.Main;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WarpUtils {

    public static boolean exists(String name) {
        try {
            Statement query = Main.sql.getConnection().createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM Warp WHERE name='" + name + "';");
            if (rs.next()) {
                rs.close();
                query.close();
                return true;
            }
            rs.close();
            query.close();
        } catch (SQLException e) {
            Bukkit.getLogger().info("Hedium Core: Warp >> Error: " + e);
        }
        return false;
    }
}
