package com.vanix.core.application.entities.member;

import com.vanix.core.application.dtos.VanixMemberDto;
import com.vanix.core.application.entities.member.controllers.MemberPermissionsController;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
public class VanixMember {

    private final UUID id;
    @Setter
    private String username;

    private final MemberPermissionsController permissionsController;

    @Setter
    private Date insertedAt;

    @Setter
    private Date lastLogin;

    public VanixMember(UUID id, String username) {
        this.id = id;
        this.username = username;
        this.permissionsController = new MemberPermissionsController();
        this.insertedAt = new Date(System.currentTimeMillis());
        this.lastLogin = new Date(System.currentTimeMillis());
    }

    public static VanixMember fromDto(VanixMemberDto dto) {
        VanixMember member = new VanixMember(dto.getId(), dto.getUsername());
        member.getPermissionsController().setGroups(dto.getGroups());
        member.getPermissionsController().setPermissions(dto.getPermissions());
        member.setInsertedAt(dto.getInsertedAt());
        member.setLastLogin(dto.getLastLogin());
        return member;
    }
}
