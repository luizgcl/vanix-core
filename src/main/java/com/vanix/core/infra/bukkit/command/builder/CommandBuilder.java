package com.vanix.core.infra.bukkit.command.builder;

import com.vanix.core.infra.bukkit.command.helper.CommandHelper;
import com.vanix.core.infra.bukkit.plugin.VanixPlugin;
import org.bukkit.command.CommandSender;

import java.util.concurrent.ThreadPoolExecutor;

public interface CommandBuilder {

    void handler(CommandSender commandSender, CommandHelper helper, String... args) throws Exception;

    void register(VanixPlugin plugin, String... alias);

    CommandBuilder player();

    CommandBuilder async();

    CommandBuilder executor(ThreadPoolExecutor executor);

    CommandBuilder usage(String usage);

    CommandBuilder sendUsage(CommandSender commandSender);
    CommandBuilder permission(String perm);

}
