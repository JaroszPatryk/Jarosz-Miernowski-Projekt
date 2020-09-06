package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.Player;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class PlayerRepository implements CrudRepository<Player, Integer> {

  private final EntityManager em;

  @Override
  public List<Player> getAll() {
    return null;
  }

  @Override
  public Player findById(Integer integer) {
    return null;
  }

  @Override
  public Player create(Player entity) {
    em.getTransaction().begin();
    em.persist(entity);
    em.getTransaction().commit();
    return entity;
  }

  @Override
  public Player update(Player entity) {
    return null;
  }

  @Override
  public void delete(Integer integer) {}
}
