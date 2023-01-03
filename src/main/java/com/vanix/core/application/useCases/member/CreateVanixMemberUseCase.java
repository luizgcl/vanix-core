package com.vanix.core.application.useCases.member;

import com.vanix.core.application.dtos.VanixMemberDto;
import com.vanix.core.application.repositories.MemberRepository;
import com.vanix.core.application.useCases.UseCase;

public class CreateVanixMemberUseCase implements UseCase<VanixMemberDto> {

    private final MemberRepository repository;

    public CreateVanixMemberUseCase(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean execute(VanixMemberDto data) {
        try {
            this.repository.create(data);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
