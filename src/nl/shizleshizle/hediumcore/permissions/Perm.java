package nl.shizleshizle.hediumcore.permissions;

import nl.shizleshizle.hediumcore.Main;
import nl.shizleshizle.hediumcore.objects.User;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Perm {
    private static Map<String, PermGroup> pPerms = new HashMap();

    public static boolean hasPerm(User p, PermGroup g) {
        return hasPerm(p.getName(), g);
    }

    public static boolean hasPerm(String name, PermGroup g) {
        return (g.getId() <= getGroup(name).getId());
    }

    public static PermGroup getGroup(User p) {
        return getGroup(p.getName());
    }

    public static PermGroup getGroup(String name) {
        if (pPerms.containsKey(name)) {
            return pPerms.get(name);
        } else {
            return getGroupFromDatabase(name);
        }
    }

    public static PermGroup getGroupFromDatabase(String name) {
        User p = new User(Bukkit.getPlayer(name));
        return getGroupFromDatabase(p);
    }

    public static PermGroup getGroupFromDatabase(User p) {
        try {
            PreparedStatement query = Main.sql.getConnection().prepareStatement("SELECT * FROM PlayerGroup INNER JOIN PermissionGroup ON PlayerGroup.groupId = PermissionGroup.groupId "
                    + " WHERE uuid=?;");
            query.setString(1, p.getUniqueId().toString());
            ResultSet rs = query.executeQuery();
            String groupName = "";
            if (rs.next()) {
                groupName = rs.getString("groupName");
            }
            return PermGroup.get(groupName);
        } catch (SQLException e) {
            System.out.println("Hedium Core: Group from Database >> Error: " + e);
        }
        return null;
    }

    public static void loginPlayer(String name) {
        pPerms.put(name, getGroup(name));
    }

    public static void logoutPlayer(String name) {
        pPerms.remove(name);
    }
}
