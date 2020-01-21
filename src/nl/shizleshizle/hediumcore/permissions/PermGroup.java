package nl.shizleshizle.hediumcore.permissions;

import static org.bukkit.ChatColor.*;

public enum PermGroup {

    LEAD_DEVELOPER(500, GRAY + "(" + DARK_AQUA + "Lead-Developer" + GRAY + ") " + AQUA, "Lead-Developer"),
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

    public static int getAmount() {
        int amount = 0;
        for (int i = 0; i < values().length; i++) {
            amount++;
        }
        return amount;
    }
}
