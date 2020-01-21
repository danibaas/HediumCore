package nl.shizleshizle.hediumcore.listeners;

import nl.shizleshizle.hediumcore.commands.Frozen;
import nl.shizleshizle.hediumcore.commands.Wild;
import nl.shizleshizle.hediumcore.objects.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;

public class PlayerMove implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        User p = new User(event.getPlayer());
        int firstX = (int) event.getFrom().getX();
        int newX = (int) Objects.requireNonNull(event.getTo()).getX();
        if (p.isAfk() && firstX != newX) {
            p.setAfk(false);
        }
        if (Wild.comfortSpawn.contains(p.getName())) {
            event.getFrom().getBlock().breakNaturally();
            Wild.comfortSpawn.remove(p.getName());
        }
        if (p.isFrozen()) {
            if (event.getTo().getX() != event.getFrom().getX() || event.getTo().getZ() != event.getFrom().getZ() || event.getTo().getY() != event.getFrom().getY()) {
                event.setTo(event.getFrom());
            }
            if (!Frozen.sentOnce) {
                p.sendMessage(Frozen.prefix + "You have been frozen!");
                Frozen.sentOnce = true;
            }
        }
    }
}
