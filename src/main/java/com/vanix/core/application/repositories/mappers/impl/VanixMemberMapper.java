package com.vanix.core.application.repositories.mappers.impl;

import com.vanix.core.application.dtos.VanixMemberDto;
import com.vanix.core.application.entities.member.VanixMember;
import com.vanix.core.application.repositories.mappers.ObjectMapper;

public class VanixMemberMapper implements ObjectMapper<VanixMember, VanixMemberDto> {
    @Override
    public VanixMember toClass(VanixMemberDto dto) {
        return VanixMember.fromDto(dto);
    }

    @Override
    public VanixMemberDto toDto(VanixMember type) {
        return VanixMemberDto.fromClass(type);
    }

}
