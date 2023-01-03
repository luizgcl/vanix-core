package com.vanix.core.infra.bukkit.event.events;

import com.vanix.core.VanixCore;
import com.vanix.core.application.entities.member.VanixMember;
import com.vanix.core.infra.bukkit.event.VanixEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;

@Getter
@AllArgsConstructor
public class TimeSecondEvent extends VanixEvent {

    public Player player;

    public VanixMember vanixMember() {
        return VanixCore.getPlatform().getMemoryDatabase().getMembers().get(player.getUniqueId());
    }
}
