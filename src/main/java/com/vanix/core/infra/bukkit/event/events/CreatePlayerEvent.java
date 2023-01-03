package com.vanix.core.infra.bukkit.event.events;

import com.vanix.core.VanixCore;
import com.vanix.core.application.dtos.VanixMemberDto;
import com.vanix.core.application.useCases.member.CreateVanixMemberUseCase;
import com.vanix.core.infra.bukkit.event.VanixMutationEvent;
import com.vanix.core.infra.database.mongodb.repositories.MemberRepositoryImpl;
import lombok.Getter;
import lombok.Setter;

public class CreatePlayerEvent extends VanixMutationEvent {

    @Getter
    @Setter
    VanixMemberDto playerData;
    public  CreatePlayerEvent(VanixMemberDto data) {
        setUseCase(new CreateVanixMemberUseCase(new MemberRepositoryImpl(VanixCore.getPlatform().getMongoConection())));
        setPlayerData(data);
    }

}
