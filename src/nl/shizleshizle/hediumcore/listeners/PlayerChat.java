package nl.shizleshizle.hediumcore.listeners;

import nl.shizleshizle.hediumcore.objects.User;
import nl.shizleshizle.hediumcore.permissions.Perm;
import nl.shizleshizle.hediumcore.permissions.PermGroup;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

public class PlayerChat implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String msg = event.getMessage();
        User p = new User(event.getPlayer());
        event.setCancelled(true);
        if (Perm.hasPerm(p, PermGroup.RANKED)) {
            if (!p.isMuted()) {

            }
        }
    }
}
