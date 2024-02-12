package org.dasultra.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.dasultra.commands.CommandStats;
import org.dasultra.mysql.mysql;

import java.sql.SQLException;

public final class Main extends JavaPlugin {

    public static Main plugin;

    public static Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {

        plugin = this;

        try {
            mysql.connect();
            Bukkit.getConsoleSender().sendMessage("§aMySQL connected Successfully!");
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("§cError, MySQL couldn't connect!");
            throw new RuntimeException(e);
        }

        System.out.print("StatsSystem is running");

        getCommand("stats").setExecutor(new CommandStats());
        getCommand("stats").setTabCompleter(new CommandStats());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
