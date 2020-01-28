package nl.shizleshizle.hediumcore.commands;

import nl.shizleshizle.hediumcore.Main;
import nl.shizleshizle.hediumcore.objects.User;
import nl.shizleshizle.hediumcore.permissions.PermGroup;
import nl.shizleshizle.hediumcore.utils.ErrorMessages;
import nl.shizleshizle.hediumcore.utils.NickNameManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class Nickname {
    public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Nicks" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

    public static class Executor implements CommandExecutor {
        private ChatColor gold = ChatColor.GOLD;
        private ChatColor yellow = ChatColor.YELLOW;

        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (sender instanceof Player) {
                User p = new User((Player) sender);
                if (p.hasPermission(PermGroup.RANKED)) {
                    if (args.length == 1) {
                        p.setNick(args[0]);
                        p.sendMessage(prefix + "Your nickname has been set to " + ChatColor.translateAlternateColorCodes('&', args[0] + yellow + "!"));
                    } else if (args.length == 2) {
                        if (p.hasPermission(PermGroup.MODERATOR)) {
                            if (args[0].equalsIgnoreCase("remove")) {
                                Player targetPlayer = Bukkit.getPlayer(args[1]);
                                assert targetPlayer != null;
                                User target = new User(targetPlayer);
                                if (target.hasNick()) {
                                    target.resetNickname();
                                    target.sendMessage(prefix + "Your nickname has been removed by " + gold + p.getName() + yellow + "!");
                                    p.sendMessage(prefix + "You have removed " + gold + target.getName() + yellow + "'s nickname!");
                                } else {
                                    p.sendMessage(prefix + "Player " + gold + target.getName() + yellow + " does not have a nickname!");
                                }
                            } else if (args[0].equalsIgnoreCase("whois")) {
                                p.sendMessage(prefix + "The real name of the player with nick name " + gold + args[1] + yellow + " is "
                                        + gold + NickNameManager.getPlayerFromNick(args[1]) + yellow + "!");
                            } else {
                                ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.INVALID_USAGE, "/nickname <set|remove|whois> <nick|player> [player]");
                            }
                        } else {
                            ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.NOPERM, "/nickname");
                        }
                    } else if (args.length == 3) {
                        if (p.hasPermission(PermGroup.MODERATOR)) {
                            if (args[0].equalsIgnoreCase("set")) {
                                String nick = args[1];
                                Player targetPlayer = Bukkit.getPlayer(args[2]);
                                assert targetPlayer != null;
                                User target = new User(targetPlayer);
                                target.setNick(nick);
                                p.sendMessage(prefix + "You have set " + gold + target.getName() + yellow + "'s nickname to "
                                        + ChatColor.translateAlternateColorCodes('&', nick) + yellow + "!");
                                target.sendMessage(prefix + "Your nickname has been set to " + ChatColor.translateAlternateColorCodes('&', nick) + yellow
                                        + " by " + gold + p.getName() + yellow + "!");
                            } else {
                                ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.INVALID_USAGE, "/nickname <set|remove|whois> <nick|player> [player]");
                            }
                        } else {
                            ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.NOPERM, "/nickname");
                        }
                    } else {
                        ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.INVALID_USAGE, "/nickname <nick>");
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.NOPERM, "/nickname");
                }
            } else {
                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("whois")) {
                        sender.sendMessage(prefix + "The player with nickname " + gold + args[1] + yellow + "'s real name is "
                                + ChatColor.RESET + NickNameManager.getPlayerFromNick(args[1]) + yellow + "!");

                    } else {
                        ErrorMessages.doErrorMessage(sender, ErrorMessages.Messages.INVALID_USAGE, "/nickname whois <player>");
                    }
                } else {
                    ErrorMessages.doErrorMessage(sender, ErrorMessages.Messages.INVALID_USAGE, "/nickname whois <player>");
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
        Main.p.getCommand("nickname").setExecutor(new Executor());
        Main.p.getCommand("nickname").setTabCompleter(new Tabcompleter());
    }
}
