package io.mbab.sda.groupproject.rest;

import io.mbab.sda.groupproject.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractCrudController<DTO, ID> {
    protected final CrudService<DTO, ID> service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    DTO save(@Valid @RequestBody DTO dto) {
        return service.save(dto);
    }

    @GetMapping
    List<DTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<DTO> findById(@PathVariable ID id) {
        return service.findByIdOptional(id)
                .map(dto -> ResponseEntity.ok(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    DTO update(@Valid @RequestBody DTO dto, @PathVariable ID id) {
        return service.update(dto);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@PathVariable ID id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
