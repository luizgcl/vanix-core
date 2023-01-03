package com.vanix.core.application.entities.member.controllers;

import com.vanix.core.VanixCore;
import com.vanix.core.application.entities.group.Group;
import com.vanix.core.application.entities.group.GroupBase;
import com.vanix.core.application.entities.group.GroupControl;
import com.vanix.core.application.entities.permissions.Permission;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Getter
public class MemberPermissionsController {

    @Setter
    private List<GroupControl> groups;

    @Setter
    private List<Permission> permissions;

    public MemberPermissionsController() {
        this.groups = new ArrayList<>(Collections.singletonList(new GroupControl(
                Group.MEMBER
        )));
        this.permissions = new ArrayList<>();
    }

    public GroupBase getGroup() {
        AtomicReference<Group> higherGroup = new AtomicReference<>(null);

        this.groups.forEach(group -> {
            if (higherGroup.get() != null) {
                if (group.getGroup().ordinal() > higherGroup.get().ordinal())
                    higherGroup.set(group.getGroup());
            } else {
                higherGroup.set(group.getGroup());
            }
        });

        return higherGroup.get().getBase();
    }

    public boolean hasGroup(Group groupPermission) {
        return this.groups.stream().filter(groupControl -> groupControl.getGroup() == groupPermission).findFirst() != null;
    }

    public boolean hasGroupPermission(Group groupPermission) {
        AtomicBoolean result = new AtomicBoolean(false);

        this.groups.forEach(group -> {
            if (!result.get() && group.getGroup().ordinal() >= groupPermission.ordinal())
                result.set(true);
        });

        return result.get();
    }

}
