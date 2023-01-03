package com.vanix.core.infra.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class ServerConfiguration {

    private boolean
            spawnCreatures,
            spawnCustomMobs,
            spawnMonsters,
            spawnMobs,
            foodEnabled,
            weatherEnabled,
            alwaysDay;



}
