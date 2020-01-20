package nl.shizleshizle.hediumcore.listeners;

import nl.shizleshizle.hediumcore.Main;
import nl.shizleshizle.hediumcore.commands.Mute;
import nl.shizleshizle.hediumcore.objects.User;
import nl.shizleshizle.hediumcore.permissions.Perm;
import nl.shizleshizle.hediumcore.permissions.PermGroup;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String msg = event.getMessage();
        String msgToSend = msg;
        User p = new User(event.getPlayer());
        event.setCancelled(true);
        if (!p.isMuted()) {
            if (Perm.hasPerm(p, PermGroup.RANKED)) {
                msgToSend = ChatColor.translateAlternateColorCodes('&', msg);
            }
        } else {
            long seconds = System.currentTimeMillis() - Main.muted.get(p.getName());
            seconds /= 1000;
            p.sendMessage(Mute.prefix + "You have been muted, you can not talk for another " + ChatColor.GOLD + seconds + ChatColor.YELLOW + " seconds.");
        }
        Bukkit.broadcastMessage(msgToSend);
    }
}
