package nl.shizleshizle.hediumcore.commands;

import nl.shizleshizle.hediumcore.Main;
import nl.shizleshizle.hediumcore.objects.User;
import nl.shizleshizle.hediumcore.permissions.Perm;
import nl.shizleshizle.hediumcore.permissions.PermGroup;
import nl.shizleshizle.hediumcore.utils.ErrorMessages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class Fly {
    public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Fly" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

    private static class Executor implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (cmd.getName().equalsIgnoreCase("fly")) {
                if (sender instanceof Player) {
                    User p = new User((Player) sender);
                    if (Perm.hasPerm(p, PermGroup.RANKED)) {
                        if (args.length == 0) {
                            if (p.getAllowFlight()) {
                                p.setFly(false);
                                p.sendMessage(prefix + "Fly has been " + ChatColor.GOLD + "disabled" + ChatColor.YELLOW + "!");
                            } else {
                                p.setFly(true);
                                p.sendMessage(prefix + "Fly has been " + ChatColor.GOLD + "enabled" + ChatColor.YELLOW + "!");
                            }
                        } else if (args.length == 1) {
                            if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
                                Player t = Bukkit.getPlayer(args[0]);
                                assert t != null;
                                if (t.isOnline()) {
                                    User target = new User(t);
                                    if (target.getAllowFlight()) {
                                        target.setFly(false);
                                        target.sendMessage(prefix + "Fly has been " + ChatColor.GOLD + "disabled" + ChatColor.YELLOW + " by " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
                                        p.sendMessage(prefix + "Fly has been " + ChatColor.GOLD + "disabled" + ChatColor.YELLOW + " for " + ChatColor.GOLD + target.getName() + ChatColor.YELLOW + "!");
                                    } else {
                                        target.setFly(true);
                                        target.sendMessage(prefix + "Fly has been " + ChatColor.GOLD + "enabled" + ChatColor.YELLOW + " by " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
                                        p.sendMessage(prefix + "Fly has been " + ChatColor.GOLD + "enabled" + ChatColor.YELLOW + " for " + ChatColor.GOLD + target.getName() + ChatColor.YELLOW + "!");
                                    }
                                }
                            }
                        } else {
                            ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.INVALID_USAGE, "/fly [player]");
                        }
                    }
                } else {
                    if (args.length == 1) {
                        Player player = Bukkit.getPlayer(args[0]);
                        assert player != null;
                        if (player.isOnline()) {
                            User p = new User(player);
                            if (p.getAllowFlight()) {
                                p.setFly(false);
                                p.sendMessage(prefix + "Fly has been " + ChatColor.GOLD + "disabled" + ChatColor.YELLOW + " by " + ChatColor.GOLD + "Console" + ChatColor.YELLOW + "!");
                            } else {
                                p.setFly(true);
                                p.sendMessage(prefix + "Fly has been " + ChatColor.GOLD + "enabled" + ChatColor.YELLOW + " by " + ChatColor.GOLD + "Console" + ChatColor.YELLOW + "!");
                            }
                        } else {
                            ErrorMessages.doErrorMessage(sender, ErrorMessages.Messages.PLAYER_OFFLINE, player.getName());
                        }
                    }
                }
            }
            return false;
        }
    }

    private static class Tabcompleter implements TabCompleter {

        @Override
        public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
            return null;
        }
    }

    public static void register() {
        Main.p.getCommand("fly").setExecutor(new Executor());
        Main.p.getCommand("fly").setTabCompleter(new Tabcompleter());
    }
}
