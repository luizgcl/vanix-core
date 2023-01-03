package com.vanix.core.infra.bukkit.plugin;

import com.vanix.core.infra.bukkit.command.loader.CommandRegister;
import lombok.Getter;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public abstract class VanixPlugin extends JavaPlugin implements VanixPluginLifeCycle {

    @Getter
    protected static VanixPlugin instance;

    protected Logger logger = Bukkit.getLogger();
    @Override
    public void onLoad() {
        instance = this;
        load();
    }

    @Override
    public void onEnable() {
        enable();
    }

    @Override
    public void onDisable() {
        disable();
    }

    @Override
    public void registerListeners(Listener... listeners) {
        for (Listener listener : listeners)
            Bukkit.getPluginManager().registerEvents(listener, this);
    }

    @Override
    public <T extends CommandExecutor> void registerCommand(T command, String... aliases) {
        CommandRegister.registerCommand(this, command, aliases);
    }
}
