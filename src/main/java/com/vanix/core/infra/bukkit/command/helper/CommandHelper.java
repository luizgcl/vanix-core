package com.vanix.core.infra.bukkit.command.helper;

import com.vanix.core.application.entities.member.VanixMember;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface CommandHelper {

    boolean isInteger(String s);

    boolean isPlayer(CommandSender sender);

    Player getPlayer(CommandSender sender);

    VanixMember getMember(Player player);

    String getMsg(String msg);

}
