package nl.shizleshizle.hediumcore.commands;

import nl.shizleshizle.hediumcore.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;


public class Back {
    private static class Executor implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
            Player player = (Player) commandSender;

            return true;
        }
    }

    private static class Tabcompleter implements TabCompleter {

        @Override
        public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
            return null;
        }
    }

    public static void register() {
        Main.p.getCommand("back").setExecutor(new Executor());
        Main.p.getCommand("back").setTabCompleter(new Tabcompleter());
    }
}