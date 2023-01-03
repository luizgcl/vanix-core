package com.vanix.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vanix.core.application.repositories.MemberRepository;
import com.vanix.core.platform.VanixPlatform;
import lombok.Getter;
import lombok.Setter;

import java.util.logging.Logger;

public final class VanixCore {

    @Getter
    private static final Gson gson = new GsonBuilder().create();

    @Getter
    @Setter
    private static Logger logger;

    @Getter
    @Setter
    private static VanixPlatform platform;
}
