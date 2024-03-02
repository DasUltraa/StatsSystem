package org.dasultra.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.dasultra.mysql.mysql;

import java.util.UUID;

public class DeathListener implements Listener {

    public static void addDeaths(UUID uuid, Double d) {
        mysql.updateDeaths(uuid, 1);
    }
    public static void addKills(UUID uuid, Double d) {
        mysql.updateKills(uuid, 1);
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player p = event.getEntity().getPlayer();
        Player a = event.getEntity().getKiller();

        addKills(a.getUniqueId(), 1.0);
        addDeaths(p.getUniqueId(), 1.0);
    }
}
