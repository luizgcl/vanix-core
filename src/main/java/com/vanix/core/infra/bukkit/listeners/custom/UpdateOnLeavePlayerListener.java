package com.vanix.core.infra.bukkit.listeners.custom;

import com.vanix.core.VanixCore;
import com.vanix.core.application.useCases.member.UpdateOnLeaveVanixMemberUseCase;
import com.vanix.core.infra.bukkit.event.events.PlayerLeaveEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class UpdateOnLeavePlayerListener implements Listener {

    @EventHandler
    void event(PlayerLeaveEvent event) {
        boolean result = ((UpdateOnLeaveVanixMemberUseCase)event.getUseCase()).execute(event.getPlayerData());

        if (result)
            VanixCore.getPlatform().getMemoryDatabase().getMembers().remove(event.getPlayerData().getId());
    }
}
