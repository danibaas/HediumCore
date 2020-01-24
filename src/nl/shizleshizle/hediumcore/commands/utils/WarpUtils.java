package nl.shizleshizle.hediumcore.commands.utils;

import nl.shizleshizle.hediumcore.Main;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

    public static ArrayList<String> listWarps() {
        ArrayList<String> warps = new ArrayList<>();
        try {
            Statement query = Main.sql.getConnection().createStatement();
            ResultSet rs = query.executeQuery("SELECT name FROM Warp");
            while (rs.next()) {
                warps.add(rs.getString("name"));
            }
            rs.close();
            query.close();
        } catch (SQLException e) {
            Bukkit.getLogger().info("Hedium Core: SQL List Warp >> Error: " + e);
        }
        return warps;
    }

    public static void addWarp(double posX, double posY, double posZ, double yaw, double pitch, String worldId, String name) {
        try {
            Statement query = Main.sql.getConnection().createStatement();
            query.execute("INSERT INTO Warp VALUES ('" + posX + "', '" + posY + "', '" + posZ + "', '" + yaw + "', '" + pitch + "', '" + worldId + "', '" + name + "');");
            query.close();
        } catch (SQLException e) {
            Bukkit.getLogger().info("Hedium Core: SQL List Warp >> Error: " + e);
        }
    }

    public static void removeWarp(String name) {
        try {
            Statement query = Main.sql.getConnection().createStatement();
            query.execute("DELETE FROM Warp WHERE name='" + name + "';");
            query.close();
        } catch (SQLException e) {
            Bukkit.getLogger().info("Hedium Core: SQL List Warp >> Error: " + e);
        }
    }
}
