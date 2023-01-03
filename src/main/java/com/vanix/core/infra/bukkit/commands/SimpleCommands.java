package com.vanix.core.infra.bukkit.commands;

import com.vanix.core.VanixCore;
import com.vanix.core.application.entities.group.Group;
import com.vanix.core.application.entities.member.VanixMember;
import com.vanix.core.infra.bukkit.BukkitMain;
import com.vanix.core.infra.bukkit.command.VanixCommand;
import com.vanix.core.infra.bukkit.command.builder.impl.CommandBuilderImpl;
import com.vanix.core.infra.bukkit.command.factory.CommandFactory;
import com.vanix.core.infra.bukkit.command.helper.CommandHelper;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class SimpleCommands extends VanixCommand {

    @Override
    public void setup() {
        CommandFactory.make(new CommandBuilderImpl() {
            @Override
            public void handler(CommandSender commandSender, CommandHelper helper, String... args) throws Exception {
                Player player = helper.getPlayer(commandSender);
                VanixMember member = VanixCore.getPlatform().getMemoryDatabase().getMembers().get(player.getUniqueId());

                player.sendMessage("§e§m================[]=================§r");
                player.sendMessage("§eInformações do jogador: " + member.getUsername());
                player.sendMessage("§e§m================[]=================§r");
                player.sendMessage("§eGrupos: ");
                List<Group> groups = member.getPermissionsController().getGroups().stream().map(groupControl -> groupControl.getGroup()).collect(Collectors.toList());
                groups.sort((a, b) -> b.ordinal() - a.ordinal());
                groups.forEach(group -> {
                    player.sendMessage(" §f- " + group.getBase().getColor() + group.getBase().getName());
                });
                player.sendMessage("§eAtividade:");
                player.sendMessage(" §f- §ePrimeiro login: §r" + member.getInsertedAt().toLocaleString());
                player.sendMessage(" §f- §eÚltimo login: §r" + member.getLastLogin().toLocaleString());
                player.sendMessage("§e§m================[]=================§r");
            }
        }).player().register(BukkitMain.getPlugin(BukkitMain.class), "account", "acc");
    }

}
