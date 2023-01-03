package com.vanix.core.infra.bukkit;

import com.vanix.core.VanixCore;
import com.vanix.core.infra.bukkit.command.VanixCommand;
import com.vanix.core.infra.bukkit.event.events.TimeSecondEvent;
import com.vanix.core.infra.bukkit.listeners.loader.VanixListener;
import com.vanix.core.infra.bukkit.permissions.regex.RegexPermissions;
import com.vanix.core.infra.bukkit.platform.BukkitPlatform;
import com.vanix.core.infra.bukkit.platform.configuration.BukkitServerConfiguration;
import com.vanix.core.infra.bukkit.plugin.VanixPlugin;
import com.vanix.core.infra.configuration.ServerConfiguration;
import com.vanix.core.infra.database.Credentials;
import com.vanix.core.infra.database.mongodb.MongoConnection;
import com.vanix.core.infra.database.mongodb.repositories.MemberRepositoryImpl;
import org.bukkit.Bukkit;

import java.util.TimeZone;

public class BukkitMain extends VanixPlugin {

    @Override
    public void load() {
        VanixCore.setPlatform(new BukkitPlatform());
        VanixCore.getPlatform().setMongoConection(
                new MongoConnection(new Credentials("127.0.0.1", "", "", "vanix", 27017))
        );
        VanixCore.setLogger(getLogger());
        VanixCore.getPlatform().setServerConfiguration(new BukkitServerConfiguration());

        logger.info("Loading plugin...");
    }

    @Override
    public void enable() {
        new RegexPermissions();

        try {
            VanixCore.getPlatform().getMongoConection().connect();
        } catch (Exception exception) {
            this.getPluginLoader().disablePlugin(this);
        }
        VanixCore.getPlatform().setMemberRepository(new MemberRepositoryImpl(VanixCore.getPlatform().getMongoConection()));

        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, () -> {
            Bukkit.getOnlinePlayers().forEach(player -> new TimeSecondEvent(player));
        }, 0, 20L);

        VanixCommand.register();
        VanixListener.register();

        logger.info("Plugin has been initialized!");
    }

    @Override
    public void disable() {
        VanixCore.getPlatform().getMongoConection().close();
        logger.info("Plugin has been disabled!");
    }

}
