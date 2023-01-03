package com.vanix.core.platform;

import com.vanix.core.application.repositories.MemberRepository;
import com.vanix.core.infra.configuration.ServerConfiguration;
import com.vanix.core.infra.database.memory.MemoryDatabase;
import com.vanix.core.infra.database.mongodb.MongoConnection;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class VanixPlatform {

    @Setter
    MongoConnection mongoConection;

    @Setter
    MemberRepository memberRepository;

    @Setter
    ServerConfiguration serverConfiguration;

    MemoryDatabase memoryDatabase = new MemoryDatabase();

}
