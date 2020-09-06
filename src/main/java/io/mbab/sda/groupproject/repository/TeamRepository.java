package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.entity.Team;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TeamRepository implements CrudRepository<Team, Integer> {

  private final EntityManager em;
  // private final Class<Team> entityClass;

  @Override
  public List<Team> getAll() {

    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(Team.class);
    var root = criteriaQuery.from(Team.class);
    return em.createQuery(criteriaQuery.select(root)).getResultList();
  }

  public Optional<Team> findByName(String name) {

    try {
      CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
      var criteriaQuery = criteriaBuilder.createQuery(Team.class);
      var root = criteriaQuery.from(Team.class);

      return Optional.of(
          em.createQuery(
                  criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("name"), name)))
              .getSingleResult());
    } catch (NoResultException ex) {
      return Optional.empty();
    }
  }

  @Override
  public Team findById(Integer integer) {
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(Team.class);
    var root = criteriaQuery.from(Team.class);
    return em.createQuery(
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("name"), integer)))
        .getSingleResult();
  }

  @Override
  public Team create(Team entity) {
    if (entity.getId() != null) {
      em.getTransaction().begin();
      em.persist(entity);
      em.getTransaction().commit();
    }
    return entity;
  }

  @Override
  public Team update(Team entity) {
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
