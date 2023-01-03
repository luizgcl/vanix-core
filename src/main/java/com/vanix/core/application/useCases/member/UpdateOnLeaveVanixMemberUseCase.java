package com.vanix.core.application.useCases.member;

import com.vanix.core.VanixCore;
import com.vanix.core.application.dtos.VanixMemberDto;
import com.vanix.core.application.repositories.MemberRepository;
import com.vanix.core.application.useCases.UseCase;

public class UpdateOnLeaveVanixMemberUseCase implements UseCase<VanixMemberDto> {

    MemberRepository repository;

    public UpdateOnLeaveVanixMemberUseCase(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean execute(VanixMemberDto data) {
        try {
            this.repository.updateById(data.getId(), data);
            return true;
        } catch (Exception exception) {
            VanixCore.getLogger().severe(exception.getMessage());
            return false;
        }
    }
}
