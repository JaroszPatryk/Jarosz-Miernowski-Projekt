package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.entity.Player;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PlayerRepository implements CrudRepository<Player, Integer> {

  private final EntityManager em;

  @Override
  public List<Player> getAll() {
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(Player.class);
    var root = criteriaQuery.from(Player.class);
    return em.createQuery(criteriaQuery.select(root)).getResultList();
  }

  @Override
  public Player findById(Integer integer) {
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(Player.class);
    var root = criteriaQuery.from(Player.class);
    return em.createQuery(
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), integer)))
        .getSingleResult();
  }

  public Optional<Player> findByIdOptional(Integer integer) {
    try {
      CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
      var criteriaQuery = criteriaBuilder.createQuery(Player.class);
      var root = criteriaQuery.from(Player.class);
      return Optional.of(
          em.createQuery(
                  criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), integer)))
              .getSingleResult());
    } catch (NoResultException ex) {
      return Optional.empty();
    }
  }

  @Override
  public Player create(Player entity) {
    if (entity.getId() != null) {
      em.getTransaction().begin();
      em.persist(entity);
      em.getTransaction().commit();
    }
    return entity;
  }

  @Override
  public Player update(Player entity) {
    em.getTransaction().begin();
    em.merge(entity);
    em.getTransaction().commit();
    return entity;
  }

  @Override
  public void delete(Integer integer) {
    em.getTransaction().begin();
    em.remove(findById(integer));
    em.getTransaction().commit();
  }

  public Optional<Player> findByName(String name) {
    try {
      CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
      var criteriaQuery = criteriaBuilder.createQuery(Player.class);
      var root = criteriaQuery.from(Player.class);
      return Optional.of(
          em.createQuery(
                  criteriaQuery
                      .select(root)
                      .where(criteriaBuilder.equal(root.get("lastName"), name)))
              .getSingleResult());
    } catch (NoResultException ex) {
      return Optional.empty();
    }
  }
}
