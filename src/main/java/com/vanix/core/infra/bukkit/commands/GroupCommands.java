package com.vanix.core.infra.bukkit.commands;

import com.vanix.core.VanixCore;
import com.vanix.core.application.entities.group.Group;
import com.vanix.core.application.entities.group.GroupControl;
import com.vanix.core.application.entities.member.VanixMember;
import com.vanix.core.infra.bukkit.BukkitMain;
import com.vanix.core.infra.bukkit.command.VanixCommand;
import com.vanix.core.infra.bukkit.command.builder.impl.CommandBuilderImpl;
import com.vanix.core.infra.bukkit.command.factory.CommandFactory;
import com.vanix.core.infra.bukkit.command.helper.CommandHelper;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class GroupCommands extends VanixCommand {

    @Override
    public void setup() {
        CommandFactory.make(new CommandBuilderImpl() {
            @Override
            public void handler(CommandSender sender, CommandHelper helper, String... args) throws Exception {
                if (sender instanceof Player) {
                    Player player = helper.getPlayer(sender);
                    VanixMember member = helper.getMember(player);

                    if (!member.getPermissionsController().hasGroupPermission(Group.ADMIN)) {
                        player.sendMessage(helper.getMsg("&cVocê não possui permissão para utilizar este comando."));
                        return;
                    }

                    if (args.length == 0) {
                        player.sendMessage(helper.getMsg("&6Grupos &b- &eAjuda:"));
                        player.sendMessage(helper.getMsg("&b - &e/group list"));
                        player.sendMessage(helper.getMsg("&b - &e/group <name> <group>"));
                        player.sendMessage(helper.getMsg("&b - &e/group <name> <group> <period>"));
                        return;
                    }

                    if (args.length > 0) {
                        if (args.length == 1) {
                            if (!args[0].equalsIgnoreCase("list")) {
                                sendUsage(player);
                                return;
                            }

                            player.sendMessage(helper.getMsg("&6Grupos &b- &eLista:"));
                            for (Group group : Group.values()) {
                                player.sendMessage(helper.getMsg("&f - " + group.getBase().getColor() + group.getBase().getPrefix()));
                            }
                            player.sendMessage(" ");
                        }

                        VanixMember searchedMember = VanixCore.getPlatform().getMemberRepository().findByUsername(args[0]);

                        if (searchedMember == null) {
                            player.sendMessage(helper.getMsg("&cNão foi possível encontrar o jogador citado no banco de dados."));
                            return;
                        }

                        Group searchedGroup = null;

                        try {
                            searchedGroup = Group.findByName(args[1]);
                        } catch (Exception exception) {
                            return;
                        }


                        List<GroupControl> groups = searchedMember.getPermissionsController().getGroups();

                        groups.add(
                                new GroupControl(
                                        searchedGroup
                                )
                        );

                        searchedMember.getPermissionsController().setGroups(groups);
                    }
                }
            }
        }).usage("group <name> <group> <period>").register(BukkitMain.getInstance(),"group");
    }
}
