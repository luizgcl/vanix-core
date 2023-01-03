package com.vanix.core.infra.bukkit.plugin;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

public interface VanixPluginLifeCycle  {

    void load();

    void enable();

    void disable();

    void registerListeners(Listener... listeners);

    <T extends CommandExecutor> void registerCommand(T command, String... aliases);

}
