package com.vanix.core.application.entities.group.groups;

import com.vanix.core.application.entities.group.GroupBase;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdminGroup extends GroupBase {

    public AdminGroup() {
        super("Administrador", "Admin", ChatColor.RED);
    }

    @Override
    public List<String> permissions() {
        return new ArrayList<>(Collections.singletonList("*"));
    }
}
