package com.vanix.core.infra.bukkit.listeners.custom;

import com.vanix.core.VanixCore;
import com.vanix.core.application.entities.member.VanixMember;
import com.vanix.core.application.useCases.member.CreateVanixMemberUseCase;
import com.vanix.core.infra.bukkit.event.events.CreatePlayerEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CreatePlayerListener implements Listener {

    @EventHandler
    void event(CreatePlayerEvent event) {
        boolean result = ((CreateVanixMemberUseCase) event.getUseCase()).execute(event.getPlayerData());

        if (result) {
            VanixCore.getPlatform().getMemoryDatabase().getMembers().put(
                    event.getPlayerData().getId(),
                    VanixMember.fromDto(event.getPlayerData())
                    );
        }
    }
}
