package nl.shizleshizle.hediumcore.permissions;

import nl.shizleshizle.hediumcore.Main;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.bukkit.ChatColor.*;

public enum PermGroup {

    LEAD_DEVELOPER(500, GRAY + "(" + DARK_AQUA + "Lead" + GRAY + "-" + DARK_AQUA + "Developer" + GRAY + ") " + AQUA, "Lead-Developer"),
    ADMIN(400, DARK_RED + "Admin " + RED, "Admin"),
    MODERATOR(300, DARK_BLUE + "Moderator " + BLUE, "Moderator"),
    RANKED(200, DARK_GRAY + "Ranked " + GRAY, "Ranked"),
    MEMBER(100, DARK_GREEN + "Member " + GREEN, "Member");

    private int id;
    private String prefix;
    private String name;

    PermGroup(int id, String prefix, String name) {
        this.id = id;
        this.prefix = prefix;
        this.name = name;
    }

    public static PermGroup get(int i) {
        for (PermGroup pg : values()) {
            if (pg.id == i) {
                return pg;
            }
        }
        return null;
    }

    public static PermGroup get(String name) {
        for (PermGroup pg : values()) {
            if (pg.name.equals(name)) {
                return pg;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getName() {
        return name;
    }

    public static int getAmount() {
        int amount = 0;
        for (int i = 0; i < values().length; i++) {
            amount++;
        }
        return amount;
    }

    public static void updateToDatabase() {
        for (PermGroup pg : values()) {
            try {
                Statement query = Main.sql.getConnection().createStatement();
                ResultSet rs = query.executeQuery("SELECT * FROM PermissionGroup WHERE groupId='" + pg.getId() + "';");
                if (!rs.next()) {
                    Statement insert = Main.sql.getConnection().createStatement();
                    insert.execute("INSERT INTO PermissionGroup VALUES ('" + pg.getId() + "', '" + pg.getName() + "');");
                    insert.close();
                }
                rs.close();
                query.close();
            } catch (SQLException e) {
                Bukkit.getLogger().info("Hedium Core: SQL Update Groups >> Error: " + e);
            }
        }
    }
}
