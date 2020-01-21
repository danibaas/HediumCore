package nl.shizleshizle.hediumcore.listeners;

import nl.shizleshizle.hediumcore.objects.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerTeleport implements Listener {

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        User p = new User(event.getPlayer());
        p.setBack(event.getFrom());
    }
}
