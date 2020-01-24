package nl.shizleshizle.hediumcore.commands;

import nl.shizleshizle.hediumcore.Main;
import nl.shizleshizle.hediumcore.commands.utils.WarpUtils;
import nl.shizleshizle.hediumcore.objects.User;
import nl.shizleshizle.hediumcore.utils.ErrorMessages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class Warp {
    public static String prefix = ChatColor.YELLOW.toString() + ChatColor.BOLD + "Warp" + ChatColor.GOLD + " >> " + ChatColor.YELLOW;

    private static class Executor implements CommandExecutor {

        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (sender instanceof Player) {
                User p = new User((Player) sender);
                if (args.length == 0) {
                    p.sendMessage(prefix + "These are the current warps:");
                    StringBuilder sb = new StringBuilder();
                    for (String warpName : WarpUtils.listWarps()) {
                        sb.append(ChatColor.YELLOW).append(warpName).append(ChatColor.GOLD).append(", ");
                    }
                    String msg = sb.toString().substring(0, sb.length() - 2);
                    p.sendMessage(msg);
                } else if (args.length == 1) {
                    String warpName = args[0];
                    if (WarpUtils.exists(warpName)) {
                        p.warp(warpName);
                    } else {
                        p.sendMessage(prefix + "Warp " + ChatColor.GOLD + warpName + ChatColor.YELLOW + " does not exist!");
                    }
                } else if (args.length == 2) {
                    String command = args[0];
                    String warp = args[1];
                    if (command.equalsIgnoreCase("set")) {
                        if (!WarpUtils.exists(warp)) {
                            WarpUtils.addWarp(p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), (double) p.getLocation().getYaw(), (double) p.getLocation().getPitch(),
                                    p.getWorld().getUID().toString(), warp);
                            p.sendMessage(prefix + "Warp " + ChatColor.GOLD + warp + ChatColor.YELLOW + " set to your location!");
                        } else {
                            p.sendMessage(prefix + "Warp " + ChatColor.GOLD + warp + ChatColor.YELLOW + " already exists!");
                        }
                    } else if (command.equalsIgnoreCase("remove")) {
                        if (WarpUtils.exists(warp)) {
                            WarpUtils.removeWarp(warp);
                            p.sendMessage(prefix + "Warp " + ChatColor.GOLD + warp + ChatColor.YELLOW + " has been deleted!");
                        } else {
                            p.sendMessage(prefix + "Warp " + ChatColor.GOLD + warp + ChatColor.YELLOW + " does not exist!");
                        }
                    } else {
                        Player targetPlayer = Bukkit.getPlayer(command);
                        assert targetPlayer != null;
                        User target = new User(targetPlayer);
                        if (WarpUtils.exists(warp)) {
                            target.warp(warp);
                            target.sendMessage(prefix + "You have been warped by " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + "!");
                            p.sendMessage(prefix + "You have teleported " + ChatColor.GOLD + target.getName() + ChatColor.YELLOW + " to " + ChatColor.GOLD + warp + ChatColor.YELLOW + "!");
                        } else {
                            p.sendMessage(prefix + "Warp " + ChatColor.GOLD + warp + ChatColor.YELLOW + " does not exist!");
                        }
                    }
                } else {
                    ErrorMessages.doErrorMessage(p, ErrorMessages.Messages.INVALID_USAGE, "/warp [name|set|remove|player] [name]");
                }
            } else {
                ErrorMessages.doErrorMessage(sender, ErrorMessages.Messages.NO_CONSOLE, "/warp");
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
        Main.p.getCommand("warp").setExecutor(new Executor());
        Main.p.getCommand("warp").setTabCompleter(new Tabcompleter());
    }
}
