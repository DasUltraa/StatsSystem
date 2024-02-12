package org.dasultra.listener;

import org.bukkit.event.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.dasultra.mysql.mysql;

import java.util.UUID;

public class KillListener implements Listener {

    public static void addKills(UUID uuid, Double d) {
        mysql.updateKills(uuid, 1);
    }
    @EventHandler
    public void onPlayerKill(PlayerDeathEvent event) {
        Player a = event.getEntity().getKiller();

        addKills(a.getUniqueId(), 1.0);
    }
}
