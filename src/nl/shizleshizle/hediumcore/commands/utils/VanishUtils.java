package nl.shizleshizle.hediumcore.commands.utils;

import nl.shizleshizle.hediumcore.ConfigManager;
import nl.shizleshizle.hediumcore.objects.User;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VanishUtils {
    private static ConfigManager c = ConfigManager.getInstance();
    private static List<String> canSee = c.getConfig().getStringList("settings.canSeeVanished");
    public static HashMap<String, ItemStack[]> pInv = new HashMap<>();
    private static Map<String, Inventory> invs = new HashMap<>();

    public static void addInvs(User p, Inventory i) {
        invs.put(p.getName(), i);
    }

    public static Inventory getInvs(User p) {
        if (containsInvs(p)) {
            return invs.get(p.getName());
        } else {
            return null;
        }
    }

    public static boolean containsInvs(User p) {
        return invs.containsKey(p.getName());
    }

    public static void addSee(User p) {
        if (!canSee.contains(p.getName())) {
            canSee.add(p.getName());
            c.getConfig().set("settings.canSeeVanished", null);
            c.getConfig().set("settings.canSeeVanished", canSee);
            c.saveConfig();
        }
    }

    public static void removeSee(User p) {
        if (canSee.contains(p.getName())) {
            canSee.remove(p.getName()); //settings.canSeeVanished
            c.getConfig().set("settings.canSeeVanished", null);
            c.getConfig().set("settings.canSeeVanished", canSee);
            c.saveConfig();
        }
    }

    public static boolean canSee(User p) {
        return canSee.contains(p.getName());
    }
}
