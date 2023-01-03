package com.vanix.core.application.entities.group.groups;

import com.vanix.core.application.entities.group.GroupBase;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class VanixGroup extends GroupBase {
    public VanixGroup() {
        super("Vanix", "Vanix", ChatColor.GOLD);
    }

    @Override
    public List<String> permissions() {
        return new ArrayList<>();
    }
}
