package nl.shizleshizle.hediumcore.sql;

import org.bukkit.Bukkit;

import java.sql.SQLException;
import java.sql.Statement;

public class SQLManager extends MySQL {
    private MySQL database;

    private static SQLManager instance = new SQLManager();

    public static SQLManager getInstance() {
        return instance;
    }

    public void setup() {
        try {
            this.database = new MySQL();
            openConnection();
            if (getConnection() != null) {
                Statement account = database.getConnection().createStatement();
                Statement permissionGroup = database.getConnection().createStatement();
                Statement permission = database.getConnection().createStatement();
                Statement playerGroup = database.getConnection().createStatement();
                account.execute("CREATE TABLE IF NOT EXISTS Account (uuid varchar(100), username varchar(150), nickname varchar(150), PRIMARY KEY (uuid));");
                permissionGroup.execute("CREATE TABLE IF NOT EXISTS PermissionGroup (groupId int, groupName varchar(150), PRIMARY KEY (groupId));");
                permission.execute("CREATE TABLE IF NOT EXISTS Permission (groupId int, permission varchar(300), PRIMARY KEY (groupId, permission));");
                playerGroup.execute("CREATE TABLE IF NOT EXISTS PlayerGroup (groupId int, uuid varchar(100), PRIMARY KEY (groupId, uuid), "
                        + "FOREIGN KEY (groupId) REFERENCES PermissionGroup(groupId), FOREIGN KEY (uuid) REFERENCES Account(uuid));");
                account.close();
                permissionGroup.close();
                permission.close();
                playerGroup.close();
            }
        } catch (SQLException sql) {
            Bukkit.getServer().getLogger().info("Hedium Core: Database >> Error: " + sql);
        }
    }

    public void getReady() {
        if (database.getConnection() == null) {
            database.openConnection();
        }
    }
}
