package com.vanix.core.application.entities.group.groups;

import com.vanix.core.application.entities.group.GroupBase;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class MemberGroup extends GroupBase {

    public MemberGroup() {
        super("Membro", "", ChatColor.GRAY);
    }

    @Override
    public List<String> permissions() {
        return new ArrayList<>();
    }
}
