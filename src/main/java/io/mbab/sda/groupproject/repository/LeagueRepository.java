package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.entity.Team;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RequiredArgsConstructor
public class LeagueRepository implements CrudRepository<League, Integer> {

  private final EntityManager em;
  //private final Class<League> entityClass;

  public League findByName(String name) {

    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(League.class);
    var root = criteriaQuery.from(League.class);
    var entity =
        em.createQuery(
                criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("name"), name)))
            .getSingleResult();

    return entity;
  }

  @Override
  public List<League> getAll() {
    return null;
  }

  @Override
  public League findById(Integer integer) {
    return null;
  }

  @Override
  public League create(League entity) {
    em.getTransaction().begin();
    em.persist(entity);
    em.getTransaction().commit();
    return entity;
  }

  @Override
  public League update(League entity) {
    return null;
  }

  @Override
  public void delete(Integer integer) {}
}
