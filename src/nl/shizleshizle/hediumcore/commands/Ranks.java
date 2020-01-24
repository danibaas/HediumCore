package nl.shizleshizle.hediumcore.commands;

import nl.shizleshizle.hediumcore.Main;
import nl.shizleshizle.hediumcore.objects.User;
import nl.shizleshizle.hediumcore.permissions.Perm;
import nl.shizleshizle.hediumcore.permissions.PermGroup;
import nl.shizleshizle.hediumcore.permissions.Permissions;
import nl.shizleshizle.hediumcore.utils.ErrorMessages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class Ranks {
    public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Ranks" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

    private static class Executor implements CommandExecutor {

        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (cmd.getName().equalsIgnoreCase("rank")) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(prefix + "You must be a player to perform this command!");
                    return true;
                } else {
                    Player x = (Player) sender;
                    User p = new User(x);
                    if (Perm.hasPerm(p, PermGroup.ADMIN) || (p.getName().equals("shizleshizle") || p.getName().equals("Electrogamez"))) {
                        if (args.length == 1) {
                            if (args[0].equalsIgnoreCase("list")) {
                                p.sendMessage(prefix + "There are a total of " + ChatColor.GOLD + PermGroup.getAmount() + ChatColor.YELLOW + " ranks! Here they are:");
                                for (PermGroup s : PermGroup.values()) {
                                    p.sendMessage(s.getPrefix());
                                }
                            } else {
                                ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.INVALID_USAGE, "/rank <list|set|get|list|add|remove> [user|rank|group] [rank|permission]");
                            }
                            return true;
                        } else if (args.length == 2) {
                            if (args[0].equalsIgnoreCase("get")) {
                                Player targetPlayer = Bukkit.getPlayer(args[1]);
                                assert targetPlayer != null;
                                User target = new User(targetPlayer);
                                PermGroup pg = target.getGroup();
                                if (pg != null) {
                                    p.sendMessage(prefix + "Player " + ChatColor.GOLD + args[1] + ChatColor.YELLOW + " is in group "
                                            + ChatColor.GOLD + pg.getName() + ChatColor.YELLOW + "!");
                                } else {
                                    p.sendMessage(prefix + "Database could not find player " + ChatColor.GOLD + args[1] + ChatColor.YELLOW + "!");
                                }
                                return true;
                            } else if (args[0].equalsIgnoreCase("list")) {
                                PermGroup pg = Perm.getGroup(args[1]);
                                p.sendMessage(prefix + ChatColor.GOLD + pg.getName() + ChatColor.YELLOW + " has these permissions:");
                                for (String pe : Permissions.getPermissions(pg)) {
                                    p.sendMessage(ChatColor.GRAY + pe);
                                }
                            } else {
                                ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.INVALID_USAGE, "/rank <list|set|get> [user] [rank]");
                                return true;
                            }
                        } else if (args.length == 3) {
                            if (args[0].equalsIgnoreCase("set")) {
                                String user = args[1];
                                PermGroup g = null;
                                for (PermGroup s : PermGroup.values()) {
                                    if (s.getName().equalsIgnoreCase(args[2])) {
                                        g = s;
                                    }
                                }
                                if (g == null) {
                                    p.sendMessage(prefix + "Invalid rank!");
                                } else {
                                    if (g.equals(PermGroup.LEAD_DEVELOPER) && (p.getName().equals("shizleshizle") || p.getName().equals("Electrogamez"))) {
                                        Perm.updateGroup(user, g);
                                    } else if (g.equals(PermGroup.ADMIN) && Perm.hasPerm(p, PermGroup.LEAD_DEVELOPER)) {
                                        Perm.updateGroup(user, g);
                                    } else if (g.equals(PermGroup.MODERATOR) && Perm.hasPerm(p, PermGroup.ADMIN)) {
                                        Perm.updateGroup(user, g);
                                    } else if (g.equals(PermGroup.RANKED) && Perm.hasPerm(p, PermGroup.ADMIN)) {
                                        Perm.updateGroup(user, g);
                                    } else if (g.getId() < PermGroup.RANKED.getId() && Perm.hasPerm(p, PermGroup.ADMIN)) {
                                        Perm.updateGroup(user, g);
                                    } else {
                                        p.sendMessage(prefix + "You can not set this rank!");
                                        return true;
                                    }
                                    Player targetPlayer = Bukkit.getPlayer(user);
                                    assert targetPlayer != null;
                                    User target = new User(targetPlayer);
                                    if (target.getName().equals(p.getName())) {
                                        p.sendMessage(prefix + "Your rank has been updated to " + ChatColor.GOLD + g.getName() + ChatColor.YELLOW + "!");
                                    } else {
                                        p.sendMessage(prefix + "You have updated " + ChatColor.GOLD + target.getName() + ChatColor.YELLOW + "'s rank to "
                                                + ChatColor.GOLD + g.getName() + ChatColor.YELLOW + "!");
                                        if (target.isOnline()) {
                                            target.sendMessage(prefix + "Your rank has been updated to " + ChatColor.GOLD + g.getName() + ChatColor.YELLOW + " by " + ChatColor.GOLD
                                                    + p.getName() + ChatColor.YELLOW + "!");
                                        }
                                    }
                                }
                                return true;
                            } else if (args[0].equalsIgnoreCase("add")) {
                                PermGroup pg = Perm.getGroup(args[1]);
                                if (pg == null) {
                                    p.sendMessage(prefix + "Invalid group!");
                                } else {
                                    String perm = args[2].trim();
                                    if (!Permissions.getPermissions(pg).contains(perm)) {
                                        Permissions.addPermission(pg, perm);
                                        p.sendMessage(prefix + "You have added '" + ChatColor.GOLD + perm + ChatColor.YELLOW + "' to " + ChatColor.GOLD + pg.getName()
                                                + ChatColor.YELLOW + "!");
                                    } else {
                                        p.sendMessage(prefix + "Group " + ChatColor.GOLD + pg.getName() + ChatColor.YELLOW + " already has permission '"
                                                + ChatColor.GOLD + perm + ChatColor.YELLOW + "'!");
                                    }
                                }
                            } else if (args[0].equalsIgnoreCase("remove")) {
                                PermGroup pg = Perm.getGroup(args[1]);
                                if (pg == null) {
                                    p.sendMessage(prefix + "Invalid group!");
                                } else {
                                    String perm = args[2].trim();
                                    if (Permissions.getPermissions(pg).contains(perm)) {
                                        Permissions.removePermission(pg, perm);
                                        p.sendMessage(prefix + "You have removed '" + ChatColor.GOLD + perm + ChatColor.YELLOW + "' from " + ChatColor.GOLD
                                                + pg.getName() + ChatColor.YELLOW + "!");
                                    } else {
                                        p.sendMessage(prefix + "Group " + ChatColor.GOLD + pg.getName() + ChatColor.YELLOW + " does not have permission '"
                                                + ChatColor.GOLD + perm + ChatColor.YELLOW + "'!");
                                    }
                                }
                            } else {
                                ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.INVALID_USAGE, "/rank <list|set|get|add|remove> [user|rank] [rank|permission]");
                            }
                        } else {
                            ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.INVALID_USAGE, "/rank <list|set|get> [user] [rank]");
                        }
                    } else {
                        ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.NOPERM, "/rank");
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
        Main.p.getCommand("rank").setExecutor(new Executor());
        Main.p.getCommand("rank").setTabCompleter(new Tabcompleter());
    }
}