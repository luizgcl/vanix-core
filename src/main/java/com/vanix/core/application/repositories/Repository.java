package com.vanix.core.application.repositories;

import java.util.UUID;

public interface Repository<Data, DTO> {

    Data findById(UUID id);

    void create(DTO data);

    void updateById(UUID id, DTO data);

    void destroy(UUID id);
}
