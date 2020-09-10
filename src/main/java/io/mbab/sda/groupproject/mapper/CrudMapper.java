package io.mbab.sda.groupproject.mapper;

public interface CrudMapper<ENTITY, DTO> {

    ENTITY dtoToEntity(DTO dto);

    DTO entityToDto(ENTITY entity);

}
