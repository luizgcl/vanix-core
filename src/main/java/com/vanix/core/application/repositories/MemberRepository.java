package com.vanix.core.application.repositories;

import com.vanix.core.application.dtos.VanixMemberDto;
import com.vanix.core.application.entities.member.VanixMember;

public interface MemberRepository extends Repository<VanixMember, VanixMemberDto> {

    VanixMember findByUsername(String username);

}
