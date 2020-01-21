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

public class Frozen {
    public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Freeze" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;
    public static boolean sentOnce = false;

    private static class Executor implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (sender instanceof Player) {
                User p = new User((Player) sender);
                if (Perm.hasPerm(p, PermGroup.MODERATOR)) {
                    if (args.length == 1) {
                        Player targetPlayer = Bukkit.getPlayer(args[0]);
                        assert targetPlayer != null;
                        User target = new User(targetPlayer);
                        if (target.isFrozen()) {
                            target.freezeUser(false);
                            target.sendMessage(prefix + "You have been " + ChatColor.GOLD + "unfrozen" + ChatColor.YELLOW + " by " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
                            p.sendMessage(prefix + "You have " + ChatColor.GOLD + "unfrozen" + ChatColor.YELLOW + " player " + ChatColor.GOLD + target.getName() + ChatColor.YELLOW + "!");
                        } else {
                            target.freezeUser(true);
                            target.sendMessage(prefix + "You have been " + ChatColor.GOLD + "frozen" + ChatColor.YELLOW + " by " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
                            p.sendMessage(prefix + "You have " + ChatColor.GOLD + "frozen" + ChatColor.YELLOW + " player " + ChatColor.GOLD + target.getName() + ChatColor.YELLOW + "!");
                        }
                    } else {
                        ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.INVALID_USAGE, "/freeze <player>");
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.NOPERM, "/freeze");
                }
            } else {
                if (args.length == 1) {
                    Player targetPlayer = Bukkit.getPlayer(args[0]);
                    assert targetPlayer != null;
                    User target = new User(targetPlayer);
                    if (target.isFrozen()) {
                        target.freezeUser(false);
                        target.sendMessage(prefix + "You have been frozen by " + ChatColor.GOLD + "Console" + ChatColor.YELLOW + "!");
                        sender.sendMessage(ChatColor.stripColor(prefix) + "You have frozen " + target.getName() + "!'");
                    } else {
                        target.freezeUser(true);
                    }
                } else {
                    ErrorMessages.doErrorMessage(sender, ErrorMessages.Messages.INVALID_USAGE, "/freeze <player>");
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
        Main.p.getCommand("freeze").setExecutor(new Executor());
        Main.p.getCommand("freeze").setTabCompleter(new Tabcompleter());
    }
}
