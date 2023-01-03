package com.vanix.core.application.entities.group;

import lombok.Getter;
import org.bukkit.ChatColor;

import java.util.List;

@Getter
public abstract class GroupBase {

    protected String name;
    protected String prefix;
    protected ChatColor color;

    public GroupBase (String name, String prefix, ChatColor color) {
        this.name = name;
        this.prefix = prefix;
        this.color = color;
    }

    public abstract List<String> permissions();
}
