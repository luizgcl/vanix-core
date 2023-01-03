package com.vanix.core.application.entities.permissions;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
public class Permission {

    String permission;
    Date insertedAt;

    @Setter
    Date unavailableAt;

    public Permission(String permission) {
      this(permission, null);
    }

    public Permission(String permission, Date unavailableAt) {
        this.permission = permission;
        this.insertedAt = new Date(System.currentTimeMillis());
        this.unavailableAt = unavailableAt;
    }
}
