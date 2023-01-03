package com.vanix.core.infra.bukkit.listeners.server;

import com.vanix.core.VanixCore;
import com.vanix.core.application.entities.member.VanixMember;
import com.vanix.core.infra.bukkit.permissions.PermissionController;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Date;

public class PlayerListener implements Listener {

    @EventHandler
    void event(PlayerJoinEvent event) {
        event.setJoinMessage(null);

        VanixMember member = VanixCore.getPlatform().getMemoryDatabase().getMembers().get(event.getPlayer().getUniqueId());

        if (member == null) {
            event.getPlayer().kickPlayer("You dont have a register in server!");
            return;
        }

        member.setLastLogin(new Date(System.currentTimeMillis()));

        PermissionController.load(event.getPlayer());
    }

    @EventHandler
    void event(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        PermissionController.unload(event.getPlayer());
    }
}
