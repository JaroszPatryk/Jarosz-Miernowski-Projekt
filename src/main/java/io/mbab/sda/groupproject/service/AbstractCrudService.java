package io.mbab.sda.groupproject.service;

import io.mbab.sda.groupproject.entity.CrudEntites;
import io.mbab.sda.groupproject.repository.AbstractCrudRepository;
import io.mbab.sda.groupproject.repository.CrudRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractCrudService<ENTITY extends CrudEntites, ID> implements CrudService<ENTITY, ID> {
//
//    protected final CrudRepository<ENTITY, ID> crudRepository;
//
//    @Override
//    public List<ENTITY> getAll() {
//        return crudRepository.getAll();
//    }
//
//
//    @Override
//    public ENTITY findById(ID id) {
//        return crudRepository.findById(id);
//    }
//
//    @Override
//    public ENTITY save(ENTITY entity) {
//        if (entity.getId() == null) {
//            entity = crudRepository.create(entity);
//        }
//
//        return entity;
//    }
//
//    @Override
//    public ENTITY update(ENTITY entity) {
//        return crudRepository.update(entity);
//    }
//
//    @Override
//    public void delete(ID id) {
//        crudRepository.delete(id);
//    }
//


}
