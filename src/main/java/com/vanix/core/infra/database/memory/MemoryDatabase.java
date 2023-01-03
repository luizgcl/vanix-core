package com.vanix.core.infra.database.memory;

import com.vanix.core.application.entities.member.VanixMember;
import lombok.Getter;

import java.util.*;

public class MemoryDatabase {

    @Getter
    Map<UUID, VanixMember> members = new HashMap<>();

}
