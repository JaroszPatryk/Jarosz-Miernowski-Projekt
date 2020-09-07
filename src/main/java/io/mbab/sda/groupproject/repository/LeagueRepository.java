package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.entity.Team;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class LeagueRepository implements CrudRepository<League, Integer> {

  private final EntityManager em;
  // private final Class<League> entityClass;

  public Optional<League> findByName(String name) {
    try {
      CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
      var criteriaQuery = criteriaBuilder.createQuery(League.class);
      var root = criteriaQuery.from(League.class);
      return Optional.of(
          em.createQuery(
                  criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("name"), name)))
              .getSingleResult());
    } catch (NoResultException ex) {
      return Optional.empty();
    }
  }

  @Override
  public List<League> getAll() {
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(League.class);
    var root = criteriaQuery.from(League.class);
    return em.createQuery(criteriaQuery.select(root)).getResultList();
  }

  @Override
  public League findById(Integer integer) {
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(League.class);
    var root = criteriaQuery.from(League.class);
    return em.createQuery(
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), integer)))
        .getSingleResult();
  }

  @Override
  public League create(League entity) {
    if (entity.getId() != null) {
      em.getTransaction().begin();
      em.persist(entity);
      em.getTransaction().commit();
    }
    return entity;
  }

  @Override
  public League update(League entity) {
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
}
