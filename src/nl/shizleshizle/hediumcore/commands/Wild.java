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

import java.util.ArrayList;
import java.util.List;

public class Wild {
    public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Wild" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;
    public static ArrayList<String> comfortSpawn = new ArrayList<>();

    private static class Executor implements CommandExecutor {

        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (sender instanceof Player) {
                User p = new User((Player) sender);
                if (args.length == 0) {
                    p.wild();
                } else if (args.length == 1) {
                    if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
                        Player targetPlayer = Bukkit.getPlayer(args[0]);
                        User target = new User(targetPlayer);
                        target.sendMessage(prefix + "You have been teleported into the wilderness by " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
                        p.sendMessage(prefix + "You have teleported " + ChatColor.GOLD + target.getName() + ChatColor.YELLOW + " to the wilderness.");
                    } else {
                        p.wild();
                    }
                } else {
                    boolean isMod = Perm.hasPerm(p, PermGroup.MODERATOR);
                    ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.INVALID_USAGE, isMod ? "/wild [player]" : "/wild");
                }
            } else {
                ErrorMessages.doErrorMessage(sender, ErrorMessages.Messages.NO_CONSOLE, "/wild");
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
        Main.p.getCommand("wild").setExecutor(new Executor());
        Main.p.getCommand("wild").setTabCompleter(new Tabcompleter());
    }
}
