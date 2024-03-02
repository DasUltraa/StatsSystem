package org.dasultra.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.dasultra.commands.CommandStats;
import org.dasultra.file.FileManager;
import org.dasultra.listener.DeathListener;
import org.dasultra.listener.JoinListener;
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

        databaseConfig();

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

        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
    }

    @Override
    public void onDisable() {
        System.out.print("StatsSystem is stopping");
    }

    private void databaseConfig() {
        FileManager mysql = new FileManager("plugins/PlayerStats/MySQL.yml");

        mysql.add("Host", "127.0.0.1");
        mysql.add("Port", "3306");
        mysql.add("Username", "user");
        mysql.add("Password", "password");
        mysql.add("Database", "database");

        mysql.save();
    }
}
