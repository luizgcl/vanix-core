package com.vanix.core.application.entities.group;

import com.vanix.core.application.entities.group.assignment.Assignment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class GroupControl {

    Group group;
    Date insertedAt;

    @Setter
    Date unavailableAt;

    @Setter
    UUID staff;
    @Setter
    Assignment assignment;

    public GroupControl(Group group) {
        this(group, null);
    }

    public GroupControl(Group group, Date unavailableAt) {
        this(group, new Date(System.currentTimeMillis()), unavailableAt, UUID.fromString("00000000-0000-0000-0000-000000000000"), Assignment.AUTO);
    }

}
