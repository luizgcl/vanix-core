package com.vanix.core.infra.bukkit.listeners.server;

import com.vanix.core.VanixCore;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class ServerListener implements Listener {


    /* Toggles */

    @EventHandler
    void event(PlayerAchievementAwardedEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    void event(LeavesDecayEvent event) {
        event.setCancelled(true);
    }

    /* Configuration */

    @EventHandler
    void event(WeatherChangeEvent event) {
        if (VanixCore.getPlatform().getServerConfiguration().isAlwaysDay()) {
            event.getWorld().setTime(0);
            event.getWorld().setGameRuleValue("doDaylightCycle", "false");
        }
        event.setCancelled(!VanixCore.getPlatform().getServerConfiguration().isWeatherEnabled());
    }

    @EventHandler
    void event(FoodLevelChangeEvent event) {
        if (!VanixCore.getPlatform().getServerConfiguration().isFoodEnabled())
            event.setFoodLevel(20);
        event.setCancelled(!VanixCore.getPlatform().getServerConfiguration().isFoodEnabled());
    }

    @EventHandler
    void event(CreatureSpawnEvent event) {
        event.setCancelled(!VanixCore.getPlatform().getServerConfiguration().isSpawnCreatures());

        if (event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.CUSTOM)) {
            event.setCancelled(!VanixCore.getPlatform().getServerConfiguration().isSpawnCustomMobs());
        } else {
            if (event.getEntity() instanceof Monster) {
                event.setCancelled(!VanixCore.getPlatform().getServerConfiguration().isSpawnMonsters());
            } else {
                event.setCancelled(!VanixCore.getPlatform().getServerConfiguration().isSpawnMobs());
            }
        }
    }

}
