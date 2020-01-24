package nl.shizleshizle.hediumcore.objects;

import nl.shizleshizle.hediumcore.Main;
import nl.shizleshizle.hediumcore.commands.Warp;
import nl.shizleshizle.hediumcore.commands.Wild;
import nl.shizleshizle.hediumcore.commands.utils.VanishUtils;
import nl.shizleshizle.hediumcore.commands.utils.WarpUtils;
import nl.shizleshizle.hediumcore.permissions.Perm;
import nl.shizleshizle.hediumcore.permissions.PermGroup;
import nl.shizleshizle.hediumcore.utils.CI;
import nl.shizleshizle.hediumcore.utils.Cooldowns;
import nl.shizleshizle.hediumcore.utils.NickNameManager;
import nl.shizleshizle.hediumcore.utils.Numbers;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;

import java.net.InetSocketAddress;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        String name = getName();
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
        String name = getName();
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
        String nick = getName();
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
        return Perm.getGroup(getName());
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
        if (NickNameManager.nicks.containsKey(getUniqueId())) {
            return ChatColor.translateAlternateColorCodes('&', NickNameManager.nicks.get(getUniqueId()));
        }
        return getName();
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

    /*
     * Gives a player a cooldown
     * If a player already has a cooldown, the time entered will add up.
     *
     * @param time           Time in seconds
     *
     */
    public void giveCooldown(int time) {
        if (hasCooldown()) {
            Cooldowns.cooldown.put(getUniqueId(), Cooldowns.cooldown.get(getUniqueId()) + time);
        } else {
            Cooldowns.cooldown.put(getUniqueId(), time);
        }
    }

    public boolean hasBack() {
        return Main.back.containsKey(getName());
    }

    public boolean hasCooldown() {
        return Cooldowns.cooldown.containsKey(getUniqueId());
    }

    public boolean hasNick() {
        try {
            Main.sql.getReady();
            PreparedStatement query = Main.sql.getConnection().prepareStatement("SELECT * FROM Nickname WHERE uuid=?;");
            query.setString(1, getUniqueId().toString());
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                query.close();
                rs.close();
                return true;
            } else {
                query.close();
                rs.close();
            }
        } catch (SQLException e) {
            Bukkit.getLogger().info("Hedium Core: SQL Nick >> Error: " + e);
        }
        return false;
    }

    public boolean hasPlayedBefore() {
        return user.hasPlayedBefore();
    }

    public boolean hasPotionEffect(PotionEffectType potionEffectType) {
        return user.hasPotionEffect(potionEffectType);
    }

    public boolean hasTeleportDisabled() {
        return Main.teleportToggle.contains(getName());
    }

    public void heal(double health, int food) {
        if (health <= 20) {
            setHealthScale(20D);
        } else {
            setHealthScale(health);
        }
        setHealth(health);
        setFoodLevel(food);
        setFireTicks(0);
        for (PotionEffect effect : getActivePotionEffects()) {
            removePotionEffect(effect.getType());
        }
    }

    public void hideUser(User u) {
        user.hidePlayer(Main.p, u.getUser());
    }

    public boolean isAfk() {
        return Main.afk.contains(getName());
    }

    public boolean isBanned() {
        return user.isBanned();
    }

    public boolean isCustomNameVisible() {
        return user.isCustomNameVisible();
    }

    public boolean isDead() {
        return user.isDead();
    }

    public boolean isFlying() {
        return user.isFlying();
    }

    public boolean isFrozen() {
        return Main.frozen.contains(getName());
    }

    public boolean isGod() {
        return Main.godMode.contains(getName());
    }

    public boolean isMuted() {
        return Main.muted.containsKey(getName());
    }

    public boolean isOnGround() {
        return user.isOnGround();
    }

    public boolean isOnline() {
        return user.isOnline();
    }

    public boolean isOp() {
        return user.isOp();
    }

    public boolean isSneaking() {
        return user.isSneaking();
    }

    public boolean isSprinting() {
        return user.isSprinting();
    }

    public boolean isVanished() {
        return Main.vanished.contains(getName());
    }

    public boolean isWhitelisted() {
        return user.isWhitelisted();
    }

    public void kickUser(String reason) {
        user.kickPlayer(reason);
    }

    public void loadNick() {
        String nickname = getName();
        try {
            Main.sql.getReady();
            PreparedStatement query = Main.sql.getConnection().prepareStatement("SELECT * FROM Nickname WHERE uuid=?;");
            query.setString(1, getUniqueId().toString());
            ResultSet rs = query.executeQuery();
            if (rs.next()) {
                nickname = rs.getString("nickname");
            }
            query.close();
            rs.close();
        } catch (SQLException e) {
            Bukkit.getServer().getLogger().info("Hedium Core: SQL Nicknames >> Could not load nicknames.");
        }
        setNick(nickname);
    }

    public InventoryView openInventory(Inventory inv) {
        return user.openInventory(inv);
    }

    public void openInventory(InventoryView invView) {
        user.openInventory(invView);
    }

    public InventoryView openWorkbench(Location loc, boolean open) {
        return user.openWorkbench(loc, open);
    }

    public void removeAttachment(PermissionAttachment pa) {
        user.removeAttachment(pa);
    }

    public void removeBack() {
        Main.back.remove(getName());
    }

    public void removeCooldown() {
        if (hasCooldown()) {
            Cooldowns.cooldown.remove(getUniqueId());
        }
    }

    public void removeNick() {
        setNick(ChatColor.RESET + getName());
        setDisplayName(ChatColor.RED + getName());
        setUserListName(ChatColor.RESET + getName());
        try {
            Main.sql.getReady();
            PreparedStatement query = Main.sql.getConnection().prepareStatement("DELETE FROM Nickname WHERE uuid=?;");
            query.setString(1, getUniqueId().toString());
            query.execute();
            query.close();
        } catch (SQLException e) {
            Bukkit.getLogger().info("Hedium Core: SQL Nick >> Could not remove nickname.");
        }
    }

    public void removePotionEffect(PotionEffectType potionEffectType) {
        user.removePotionEffect(potionEffectType);
    }

    public void repairInMainHand() {
        ItemStack item = getItemInMainHand();
        Damageable dmg = (Damageable) item.getItemMeta();
        if (dmg != null) {
            dmg.setDamage(0);
        }
        item.setItemMeta((ItemMeta) dmg);
    }

    public void repairAll() {
        for (ItemStack item : getInventory().getContents()) {
            Damageable dmg = (Damageable) item.getItemMeta();
            if (dmg != null) {
                dmg.setDamage(0);
            }
            item.setItemMeta((ItemMeta) dmg);
        }
    }

    public void repairArmor() {
        for (ItemStack armor : getInventory().getArmorContents()) {
            Damageable dmg = (Damageable) armor.getItemMeta();
            if (dmg != null) {
                dmg.setDamage(0);
            }
            armor.setItemMeta((ItemMeta) dmg);
        }
    }

    public void resetUserTime() {
        user.resetPlayerTime();
    }

    public void resetUserWeather() {
        user.resetPlayerWeather();
    }

    public void saveNick() {
        try {
            PreparedStatement query;
            Main.sql.getReady();
            if (hasNick()) {
                query = Main.sql.getConnection().prepareStatement("UPDATE Nickname SET nickname=? WHERE uuid=?;");
                query.setString(2, getUniqueId().toString());
                query.setString(1, NickNameManager.nicks.get(getUniqueId()));
            } else {
                query = Main.sql.getConnection().prepareStatement("INSERT INTO Nickname VALUES (?, ?);");
                query.setString(1, getUniqueId().toString());
                query.setString(2, NickNameManager.nicks.get(getUniqueId()));
            }
            query.close();
            Bukkit.getLogger().info("Hedium Core: SQL Nick >> Saved nickname.");
        } catch (SQLException e) {
            Bukkit.getLogger().info("Hedium Core: SQL Nick >> Could not save nickname.");
        }
    }

    public void sendMessage(String message) {
        user.sendMessage(message);
    }

    public void setAfk(boolean afk) {
        if (afk) {
            Main.afk.add(getName());
            Bukkit.broadcastMessage(getDisplayName() + ChatColor.GRAY + " is now AFK.");
        } else {
            Main.afk.remove(getName());
            Bukkit.broadcastMessage(getDisplayName() + ChatColor.GRAY + " is no longer AFK");
        }
    }

    public void setAllowFlight(boolean allowFlight) {
        user.setAllowFlight(allowFlight);
    }

    public void setBack(Location loc) {
        Main.back.remove(getName());
        Main.back.put(getName(), loc);
    }

    public void setCanPickupItems(boolean canPickup) {
        user.setCanPickupItems(canPickup);
    }

    public void setCustomName(String name) {
        user.setCustomName(name);
    }

    public void setDay(boolean allworlds) {
        if (allworlds) {
            for (World w : getServer().getWorlds()) {
                w.setTime(0);
            }
        } else {
            getWorld().setTime(0);
        }
    }

    public void setDisplayName(String displayName) {
        user.setDisplayName(displayName);
    }

    public void setFallDistance(float fall) {
        user.setFallDistance(fall);
    }

    public void setFireTicks(int fireTicks) {
        user.setFireTicks(fireTicks);
    }

    public void setFly(boolean fly) {
        if (fly) {
            setAllowFlight(true);
            setFlySpeed(0.2F);
        } else {
            setAllowFlight(false);
            setFlying(false);
        }
    }

    public void setFlying(boolean fly) {
        user.setFlying(fly);
    }

    public void setFlySpeed(float speed) {
        user.setFlySpeed(speed);
    }

    public void setFoodLevel(int foodLevel) {
        user.setFoodLevel(foodLevel);
    }

    public void setGameMode(GameMode gm) {
        user.setGameMode(gm);
    }

    public void setGod(boolean god) {
        if (god) {
            if (!Main.godMode.contains(getName())) {
                Main.godMode.add(getName());
            }
        } else {
            Main.godMode.remove(getName());
        }
    }

    public void setGroup(PermGroup group) {
        Perm.updateGroup(getName(), group);
    }

    public void setHat(ItemStack hat) {
        if (getInventory().getHelmet() != null) {
            getInventory().addItem(getInventory().getHelmet());
            getInventory().setHelmet(null);
        }
        getInventory().setHelmet(hat);
    }

    public void setHealth(double health) {
        user.setHealth(health);
    }

    public void setHealthScale(double scale) {
        user.setHealthScale(scale);
    }

    public void setHealthScaled(boolean scaled) {
        user.setHealthScaled(scaled);
    }

    public void setItemInMainHand(ItemStack item) {
        getInventory().setItemInMainHand(item);
    }

    public void setItemInOffHand(ItemStack item) {
        getInventory().setItemInOffHand(item);
    }

    public void setItemOnCursor(ItemStack item) {
        user.setItemOnCursor(item);
    }

    public void setLevel(int level) {
        user.setLevel(level);
    }

    public void setNick(String nick) {
        NickNameManager.nicks.put(getUniqueId(), nick);
        nick = ChatColor.translateAlternateColorCodes('&', nick);
        setDisplayName(nick + ChatColor.RESET);
        setUserListName(nick + ChatColor.RESET);
        setCustomName(nick + ChatColor.RESET);
        saveNick();
    }

    public void setNight(boolean allworlds) {
        if (allworlds) {
            for (World w : getServer().getWorlds()) {
                w.setTime(13000);
            }
        } else {
            getWorld().setTime(13000);
        }
    }

    public void setOp(boolean op) {
        user.setOp(op);
    }

    public void setScoreboard(Scoreboard board) {
        user.setScoreboard(board);
    }

    public void setSneaking(boolean sneak) {
        user.setSneaking(sneak);
    }

    public void setSprinting(boolean sprint) {
        user.setSprinting(sprint);
    }

    public void setTeleportDisabled(boolean disabled) {
        if (disabled) {
            Main.teleportToggle.remove(getName());
        } else {
            Main.teleportToggle.add(getName());
        }
    }

    public void setUserListName(String name) {
        user.setPlayerListName(name);
    }

    public void setUserTime(long time, boolean set) {
        user.setPlayerTime(time, set);
    }

    public void setUserWeather(WeatherType weather) {
        user.setPlayerWeather(weather);
    }

    /**
     * Vanish or unvanish a player.
     *
     * @param vanish When true the player will be vanished, when false the player will be unvanished.
     */
    public void setVanished(boolean vanish) {
        if (vanish) {
            Main.vanished.add(getName());
            VanishUtils.pInv.put(getName(), getInventory().getContents());
            getInventory().clear();
            getInventory().addItem(new ItemStack(CI.createItem(Material.COMPASS, 1, -1, ChatColor.AQUA + "Player Selector")));
            setGod(true);
            setAllowFlight(true);
            setFlySpeed(0.3F);
            setFireTicks(0);
            setCanPickupItems(false);
            for (Player x : Bukkit.getOnlinePlayers()) {
                User ap = new User(x);
                if (!canSee(ap) || !Perm.hasPerm(ap, PermGroup.ADMIN)) {
                    ap.getUser().hidePlayer(Main.p, user);
                }
            }
        } else {
            if (Main.vanished.contains(getName())) {
                Main.vanished.remove(getName());
                getInventory().clear();
                if (VanishUtils.pInv.get(getName()) != null) {
                    getInventory().setContents(VanishUtils.pInv.get(getName()));
                    VanishUtils.pInv.remove(getName());
                }
                setGod(false);
                if (!getGameMode().equals(GameMode.CREATIVE) && !isFlying()) {
                    setAllowFlight(false);
                    setFlying(false);
                }
                if (hasNick()) {
                    setUserListName(getNick());
                } else {
                    setUserListName(getName());
                }
                toBack();
                removeBack();
                setFallDistance(0);
                setCanPickupItems(true);
                for (Player x : Bukkit.getOnlinePlayers()) {
                    x.showPlayer(Main.p, user);
                }
            }
        }
    }

    public void showUser(User u) {
        user.showPlayer(Main.p, u.getUser());
    }

    public void setWalkSpeed(float speed) {
        user.setWalkSpeed(speed);
    }

    public void setWhitelisted(boolean whitelisted) {
        user.setWhitelisted(whitelisted);
    }

    public Player.Spigot spigot() {
        return user.spigot();
    }

    public void teleport(Entity to) {
        user.teleport(to);
    }

    public void teleport(Location loc) {
        user.teleport(loc);
    }

    /**
     * Teleports the player to his back location
     */
    public void toBack() {
        if (Main.back.containsKey(getName())) {
            teleport(Main.back.get(getName()));
        }
    }

    /**
     * Teleports the player to the spawn.
     */
    public void toSpawn() {
        String worldUID = getWorld().getUID().toString();
        double x = 0, y = 80, z = 0, yaw = 0, pitch = 0;
        try {
            Main.sql.getReady();
            Statement query = Main.sql.getConnection().createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM Spawn WHERE worldId='" + worldUID + "';");
            if (rs.next()) {
                x = rs.getDouble("posX");
                y = rs.getDouble("posY");
                z = rs.getDouble("posZ");
                yaw = rs.getDouble("yaw");
                pitch = rs.getDouble("pitch");
            }
            rs.close();
            query.close();
            sendMessage(Main.prefix + "An error occurred, please tell shizleshizle or Electrogamez.");
        } catch (SQLException e) {
            Bukkit.getLogger().info("Hedium Core: Spawn Teleportation >> Could not teleport to spawn, error: " + e);
        }
        Location spawn = new Location(getWorld(), x, y, z, (float) yaw, (float) pitch);
        teleport(spawn);
    }

    public void updateInventory() {
        user.updateInventory();
    }

    /**
     * Teleports the player to certain warp.
     *
     * @param warp The warp the player will be teleported to.
     */
    public void warp(String warp) {
        if (WarpUtils.exists(warp)) {
            double x = 0, y = 80, z = 0;
            float yaw = 0, pitch = 0;
            String worldId = "";
            World w = null;
            try {
                Main.sql.getReady();
                Statement query = Main.sql.getConnection().createStatement();
                ResultSet rs = query.executeQuery("SELECT * FROM Warp WHERE name='" + warp + "';");
                if (rs.next()) {
                    x = rs.getDouble("posX");
                    y = rs.getDouble("posY");
                    z = rs.getDouble("posZ");
                    yaw = rs.getFloat("yaw");
                    pitch = rs.getFloat("pitch");
                    worldId = rs.getString("worldId");
                }
                rs.close();
                query.close();
            } catch (SQLException e) {
                Bukkit.getLogger().info("Hedium Core: SQL Warp >> Error: " + e);
            }
            for (World world : Bukkit.getWorlds()) {
                if (world.getUID().toString().equals(worldId)) {
                    w = world;
                }
            }
            Location warpLoc = new Location(w, x, y, z, yaw, pitch);
            teleport(warpLoc);
            sendMessage(Warp.prefix + "Warped to " + ChatColor.GOLD + warp + ChatColor.YELLOW + "!");
        } else {
            sendMessage(Warp.prefix + "Warp " + ChatColor.GOLD + warp + ChatColor.YELLOW + " does not exist!");
        }
    }

    /**
     * Teleports the player to a random location between 800 and 1500 blocks from their original location.
     */
    public void wild() {
        Location currentLocation = getLocation();
        Location targetLocation = null;
        int x = Numbers.getRandom(800, 1500);
        int y = 150;
        int z = Numbers.getRandom(800, 1500);
        boolean isOnLand = false;
        while (!isOnLand) {
            if (y <= 0) {
                teleport(getWorld().getSpawnLocation());
                break;
            }
            targetLocation = new Location(getWorld(), x, y, z);
            if (targetLocation.getBlock().getType() != Material.AIR) {
                if (targetLocation.getBlock().isLiquid()) {
                    targetLocation.getBlock().setType(Material.GRASS);
                    Wild.comfortSpawn.add(getName());
                }
                isOnLand = true;
            } else {
                y--;
            }
        }
        teleport(new Location(targetLocation.getWorld(), targetLocation.getX(), targetLocation.getY() + 1, targetLocation.getZ()));
        sendMessage(Wild.prefix + "You have been teleported " + ChatColor.GOLD + (int) targetLocation.distance(currentLocation) + ChatColor.YELLOW + " blocks away!");
    }
}
