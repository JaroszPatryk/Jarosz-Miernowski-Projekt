package io.mbab.sda.groupproject.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {

  List<T> getAll();

  T findById(ID id);

  T create(T entity);

  T update(T entity);

  void delete(ID id);
}
