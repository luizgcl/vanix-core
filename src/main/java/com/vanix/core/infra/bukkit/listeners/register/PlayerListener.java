package com.vanix.core.infra.bukkit.listeners.register;

import com.vanix.core.VanixCore;
import com.vanix.core.application.dtos.VanixMemberDto;
import com.vanix.core.application.entities.member.VanixMember;
import com.vanix.core.infra.bukkit.event.events.CreatePlayerEvent;
import com.vanix.core.infra.bukkit.event.events.PlayerLeaveEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    void event(AsyncPlayerPreLoginEvent event) {
        VanixMember member = VanixCore.getPlatform().getMemberRepository().findById(event.getUniqueId());

        if (member == null) {
            new CreatePlayerEvent(new VanixMemberDto(event.getUniqueId(), event.getName())).call();
            return;
        }

        VanixCore.getPlatform().getMemoryDatabase().getMembers().put(event.getUniqueId(), member);
    }

    @EventHandler
    void event(PlayerKickEvent event) {
        Player player = event.getPlayer();
        VanixMember member = VanixCore.getPlatform().getMemoryDatabase().getMembers().get(player.getUniqueId());

        new PlayerLeaveEvent(VanixMemberDto.fromClass(member)).call();
    }

    @EventHandler
    void event(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        VanixMember member = VanixCore.getPlatform().getMemoryDatabase().getMembers().get(player.getUniqueId());

        new PlayerLeaveEvent(VanixMemberDto.fromClass(member)).call();
    }

}
