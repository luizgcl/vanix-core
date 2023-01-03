package com.vanix.core.infra.bukkit.platform.configuration;

import com.vanix.core.infra.configuration.ServerConfiguration;

public class BukkitServerConfiguration extends ServerConfiguration {
    public BukkitServerConfiguration() {
        super(false,
                false,
                false,
                false,
                false,
                false,
                true);
    }
}
