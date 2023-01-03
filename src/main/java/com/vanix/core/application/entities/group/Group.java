package com.vanix.core.application.entities.group;

import com.vanix.core.application.entities.group.groups.AdminGroup;
import com.vanix.core.application.entities.group.groups.MemberGroup;
import com.vanix.core.application.entities.group.groups.VanixGroup;
import org.bukkit.ChatColor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum Group {

    MEMBER(new MemberGroup()),
    VANIX(new VanixGroup()),
    ADMIN(new AdminGroup());

    private final GroupBase base;

    private static final Map<String, Group> GROUPS;

    static {
        Map<String, Group> map = new ConcurrentHashMap<>();
        for (Group group : Group.values()) {
            map.put(group.name().toLowerCase(), group);
            String prefix = ChatColor.stripColor(group.getBase().getPrefix());
            map.put(prefix.toLowerCase(), group);
            map.put(group.getBase().getName(), group);
        }
        GROUPS = Collections.unmodifiableMap(map);
    }

    Group(GroupBase base) {
        this.base = base;
    }

    public GroupBase getBase() {
        return base;
    }

    public static Group findByName(String name) {
        return Group.GROUPS.get(name.toLowerCase());
    }
}
