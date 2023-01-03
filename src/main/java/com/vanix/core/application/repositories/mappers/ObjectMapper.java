package com.vanix.core.application.repositories.mappers;

public interface ObjectMapper<T, DTO> {

    T toClass(DTO dto);

    DTO toDto(T type);
}
