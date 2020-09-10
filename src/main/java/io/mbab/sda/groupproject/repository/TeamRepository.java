package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.Player;
import io.mbab.sda.groupproject.entity.Team;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

@Repository
public class TeamRepository extends AbstractCrudRepository<Team, Integer> {

  public TeamRepository(EntityManager em) {
    super(em, Team.class);
  }

  @Override
  public Team findByName(String name) {
    try {
      CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
      var criteriaQuery = criteriaBuilder.createQuery(Team.class);
      var root = criteriaQuery.from(Team.class);

      return em.createQuery(
              criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("name"), name)))
          .setMaxResults(1)
          .getSingleResult();
    } catch (NoResultException ex) {
      return null;
    }
  }

  public Optional<Team> findByIdOptional(Integer integer) {
    try {
      CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
      var criteriaQuery = criteriaBuilder.createQuery(Team.class);
      var root = criteriaQuery.from(Team.class);
      return Optional.of(
          em.createQuery(
                  criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), integer)))
              .getSingleResult());
    } catch (NoResultException ex) {
      return null;
    }
  }
}
