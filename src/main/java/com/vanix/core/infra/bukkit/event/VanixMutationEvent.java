package com.vanix.core.infra.bukkit.event;

import com.vanix.core.application.useCases.UseCase;
import lombok.Getter;
import lombok.Setter;

public abstract class VanixMutationEvent extends VanixEvent {

    @Getter
    @Setter
    UseCase<?> useCase;
}
