package org.dasultra.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.dasultra.file.FileManager;
import org.dasultra.mysql.mysql;

import java.util.List;

public class CommandStats implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player p) {
            if (args.length == 0) {
                p.sendMessage("§7§m-------------------------");
                p.sendMessage("§6§lStats of " + p.getName());
                p.sendMessage("§7§m-------------------------");
                p.sendMessage("§6Kills: §7" + mysql.getKills(p.getUniqueId()));
                p.sendMessage("§6Deaths: §7" + mysql.getDeaths(p.getUniqueId()));
                p.sendMessage("§6KDR: §7" + mysql.getKills(p.getUniqueId()) / mysql.getDeaths(p.getUniqueId()));
                p.sendMessage("§7§m-------------------------");
            } else if (args.length == 1) {
                Player t = Bukkit.getPlayer(args[0]);
                if (t != null) {
                    p.sendMessage("§7§m-------------------------");
                    p.sendMessage("§6§lStats of " + t.getName());
                    p.sendMessage("§7§m-------------------------");
                    p.sendMessage("§6Kills: §7" + mysql.getKills(t.getUniqueId()));
                    p.sendMessage("§6Deaths: §7" + mysql.getDeaths(t.getUniqueId()));
                    p.sendMessage("§6KDR: §7" + mysql.getKills(t.getUniqueId()) / mysql.getDeaths(t.getUniqueId()));
                    p.sendMessage("§7§m-------------------------");
                } else {
                    p.sendMessage("§cPlayer not found.");
                }
            } else {
                p.sendMessage("§cUsage: /stats [player]");
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
