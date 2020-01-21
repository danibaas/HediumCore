package nl.shizleshizle.hediumcore.permissions;

import nl.shizleshizle.hediumcore.Main;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Permissions {

    public static void addPermission(PermGroup pg, String perm) {
        try {
            if (!hasPermission(pg, perm)) {
                Main.sql.getReady();
                Statement query = Main.sql.getConnection().createStatement();
                query.execute("INSERT INTO Permission VALUES ('" + pg.getId() + "', '" + perm + "');");
                query.close();
            }
        } catch (SQLException e) {
            Bukkit.getLogger().info("Hedium Core: SQL Perms >> Error: " + e);
        }
    }

    public static void removePermission(PermGroup pg, String perm) {
        try {
            if (hasPermission(pg, perm)) {
                Main.sql.getReady();
                Statement query = Main.sql.getConnection().createStatement();
                query.execute("DELETE FROM Permission WHERE groupId='" + pg.getId() + "' AND permission='" + perm + "';");
                query.close();
            }
        } catch (SQLException e) {
            Bukkit.getLogger().info("Hedium Core: SQL Perms >> Error: " + e);
        }
    }

    public static boolean hasPermission(PermGroup pg, String perm) {
        try {
            Main.sql.getReady();
            Statement query = Main.sql.getConnection().createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM Permission WHERE groupId='" + pg.getId() + "';");
            while (rs.next()) {
                if (rs.getString("permission").equals(perm)) {
                    return true;
                }
            }
            rs.close();
            query.close();
        } catch (SQLException e) {
            Bukkit.getLogger().info("Hedium Core: SQL Perms >> Error: " + e);
        }
        return false;
    }

    public static void addPermissions(PermGroup from, PermGroup to) {
        ArrayList<String> permsFrom = new ArrayList<>();
        try {
            Main.sql.getReady();
            Statement query = Main.sql.getConnection().createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM Permission WHERE groupId='" + from.getId() + "';");
            while (rs.next()) {
                permsFrom.add(rs.getString("permission"));
            }
            query.close();
            rs.close();
            for (String permission : permsFrom) {
                if (!hasPermission(to, permission)) {
                    Statement queries = Main.sql.getConnection().createStatement();
                    queries.execute("INSERT INTO Permission VALUES ('" + to.getId() + "', '" + permission + "');");
                    queries.close();
                }
            }
        } catch (SQLException e) {
            Bukkit.getLogger().info("Hedium Core: SQL Perms >> Error: " + e);
        }
    }

    public static ArrayList<String> getPermissions(PermGroup pg) {
        ArrayList<String> permissions = new ArrayList<>();
        try {
            Main.sql.getReady();
            Statement query = Main.sql.getConnection().createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM Permission WHERE groupId='" + pg.getId() + "';");
            while (rs.next()) {
                permissions.add(rs.getString("permission"));
            }
            rs.close();
            query.close();
        } catch (SQLException e) {
            Bukkit.getLogger().info("Hedium Core: SQL Perms >> Error: " + e);
        }
        return permissions;
    }
}
