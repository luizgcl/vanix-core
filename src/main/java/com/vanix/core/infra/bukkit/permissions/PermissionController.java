package com.vanix.core.infra.bukkit.permissions;

import com.vanix.core.VanixCore;
import com.vanix.core.application.entities.member.VanixMember;
import com.vanix.core.application.entities.permissions.Permission;
import com.vanix.core.infra.bukkit.BukkitMain;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;

import java.util.Date;
import java.util.Map;

public class PermissionController {

    public static void load(Player player) {
        PermissionAttachment attachment = player.addAttachment(BukkitMain.getInstance());

        VanixMember member = VanixCore.getPlatform().getMemoryDatabase().getMembers().get(player.getUniqueId());

        for (String permission : member.getPermissionsController().getGroup().permissions()) {
            attachment.setPermission(permission, true);
        }

        for (Permission permission : member.getPermissionsController().getPermissions()) {
            if (permission == null) continue;
            if (permission.getUnavailableAt() != null && permission.getUnavailableAt().after(new Date(System.currentTimeMillis()))) continue;
            attachment.setPermission(permission.getPermission(), true);
        }
    }

    public static void unload(Player player) {
        for (PermissionAttachmentInfo info : player.getEffectivePermissions()) {
            PermissionAttachment attachment = info.getAttachment();
            if (attachment != null) {
                Map<String, Boolean> flags = attachment.getPermissions();
                for (Map.Entry<String, Boolean> entry : flags.entrySet()) {
                    attachment.setPermission(entry.getKey(), false);
                }
            }
        }
    }

}
