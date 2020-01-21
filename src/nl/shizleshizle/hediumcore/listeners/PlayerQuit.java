package nl.shizleshizle.hediumcore.listeners;

import nl.shizleshizle.hediumcore.objects.User;
import nl.shizleshizle.hediumcore.permissions.Perm;
import nl.shizleshizle.hediumcore.permissions.PermAttachments;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        User p = new User(event.getPlayer());
        event.setQuitMessage(ChatColor.GOLD + "Hedium" + ChatColor.DARK_AQUA + " << " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " has left the game.");
        PermAttachments.removePerms(p);
        Perm.logoutPlayer(p.getName());
    }
}
