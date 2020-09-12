package io.mbab.sda.groupproject.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<DTO, ID> {

    List<DTO> getAll();

    DTO findById(ID id);

    DTO save(DTO entity);

    DTO update(DTO entity);

    void delete(ID id);

    Optional<DTO> findByIdOptional(ID id);
}
