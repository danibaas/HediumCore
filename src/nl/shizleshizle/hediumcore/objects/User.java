package nl.shizleshizle.hediumcore.objects;

import nl.shizleshizle.hediumcore.Main;
import nl.shizleshizle.hediumcore.permissions.Perm;
import nl.shizleshizle.hediumcore.permissions.PermGroup;
import org.bukkit.*;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

import java.net.InetSocketAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class User {
    // User is a better function of player
    Player user;

    public User(Player player) {
        user = player;
    }

    public Player getUser() {
        return user;
    }

    public PermissionAttachment addAttachment(Plugin plugin) {
        return user.addAttachment(plugin);
    }

    public void setUser(Player player) {
        user = player;
    }

    public void addItem(ItemStack item) {
        user.getInventory().addItem(item);
    }

    public void addPotionEffect(PotionEffect e) {
        user.addPotionEffect(e);
    }

    public void addPotionEffects(Collection<PotionEffect> e) {
        user.addPotionEffects(e);
    }

    public boolean canSee(User u) {
        return user.canSee(u.getUser());
    }

    public void clearInventory() {
        user.getInventory().clear();
        user.getInventory().setArmorContents(null);
    }

    public void closeInventory() {
        user.closeInventory();
    }

    public void freezeUser(boolean freeze) {
        String name = user.getName();
        if (freeze) {
            if (!Main.frozen.contains(name)) {
                Main.frozen.add(name);
            }
        } else {
            Main.frozen.remove(name);
        }
    }

    public Collection<PotionEffect> getActivePotionEffects() {
        return user.getActivePotionEffects();
    }

    public InetSocketAddress getAddress() {
        return user.getAddress();
    }

    public boolean getAllowFlight() {
        return user.getAllowFlight();
    }

    public Location getBack() {
        Location loc = null;
        String name = user.getName();
        if (Main.back.containsKey(name)) {
            loc = Main.back.get(name);
        }
        return loc;
    }

    public Location getBedSpawnLocation() {
        return user.getBedSpawnLocation();
    }

    public String getCustomName() {
        return user.getCustomName();
    }

    public String getDisplayName() {
        String nick = user.getName();
        if (hasNick()) {
            nick = getNick().trim();
        }
        return nick;
    }

    public Inventory getEnderChest() {
        return user.getEnderChest();
    }

    public float getExp() {
        return user.getExp();
    }

    public float getFallDistance() {
        return user.getFallDistance();
    }

    public int getFireTicks() {
        return user.getFireTicks();
    }

    public long getFirstPlayed() {
        return user.getFirstPlayed();
    }

    public float getFlySpeed() {
        return user.getFlySpeed();
    }

    public int getFoodLevel() {
        return user.getFoodLevel();
    }

    public GameMode getGameMode() {
        return user.getGameMode();
    }

    public PermGroup getGroup() {
        return Perm.getGroup(user.getName());
    }

    public double getHealth() {
        return user.getHealth();
    }

    public double getHealthScale() {
        return user.getHealthScale();
    }

    public PlayerInventory getInventory() {
        return user.getInventory();
    }

    public ItemStack getItemInMainHand() {
        return user.getInventory().getItemInMainHand();
    }

    public ItemStack getItemInOffHand() {
        return user.getInventory().getItemInOffHand();
    }

    public ItemStack getItemOnCursor() {
        return user.getItemOnCursor();
    }

    public User getKiller() {
        return new User(user.getKiller());
    }

    public int getLevel() {
        return user.getLevel();
    }

    public Location getLocation() {
        return user.getLocation();
    }

    public Attribute getMaxHealth() {
        return Attribute.GENERIC_MAX_HEALTH;
    }

    public String getName() {
        return user.getName();
    }

    public String getNick() {
        if (NickNameManager.nicks.containsKey(user.getName())) {
            return ChatColor.translateAlternateColorCodes('&', NickNameManager.nicks.get(user.getName()));
        }
        return null;
    }

    public InventoryView getOpenInventory() {
        return user.getOpenInventory();
    }

    public int getRemainingCooldownTime() {
        int time = 0;
        if (hasCooldown()) {
            time = Cooldowns.cooldown.get(getUniqueId());
        }
        return time;
    }

    public Server getServer() {
        return user.getServer();
    }

    public int getTotalExperience() {
        return user.getTotalExperience();
    }

    public String getUserListName() {
        return user.getPlayerListName();
    }

    public long getUserTime() {
        return user.getPlayerTime();
    }

    public WeatherType getUserWeather() {
        return user.getPlayerWeather();
    }

    public UUID getUniqueId() {
        return user.getUniqueId();
    }

    public float getWalkSpeed() {
        return user.getWalkSpeed();
    }

    public World getWorld() {
        return user.getWorld();
    }


}
