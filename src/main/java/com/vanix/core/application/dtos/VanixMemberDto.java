package com.vanix.core.application.dtos;

import com.vanix.core.application.entities.group.Group;
import com.vanix.core.application.entities.group.GroupControl;
import com.vanix.core.application.entities.member.VanixMember;
import com.vanix.core.application.entities.permissions.Permission;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

@Getter
public class VanixMemberDto implements Serializable {

    @Getter
    private final UUID id;

    @Setter
    private String username;

    @Setter
    private List<GroupControl> groups;

    @Setter
    private List<Permission> permissions;

    @Setter
    private Date insertedAt;

    @Setter
    private Date lastLogin;

    public VanixMemberDto(UUID id, String username) {
        this.id = id;
        this.username = username;
        this.groups = new ArrayList<>(Collections.singletonList(new GroupControl(Group.MEMBER)));
        this.permissions = new ArrayList<>();
        this.insertedAt = new Date(System.currentTimeMillis());
        this.lastLogin = new Date(System.currentTimeMillis());
    }

    public static VanixMemberDto fromClass(VanixMember member) {
        VanixMemberDto dto = new VanixMemberDto(member.getId(), member.getUsername());
        dto.setGroups(member.getPermissionsController().getGroups());
        dto.setPermissions(member.getPermissionsController().getPermissions());
        dto.setInsertedAt(member.getInsertedAt());
        dto.setLastLogin(member.getLastLogin());
        return dto;
    }
}
