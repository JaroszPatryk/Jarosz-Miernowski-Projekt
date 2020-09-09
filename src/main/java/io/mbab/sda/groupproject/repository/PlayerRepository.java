package io.mbab.sda.groupproject.repository;


import io.mbab.sda.groupproject.entity.Player;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;


public class PlayerRepository extends AbstractCrudRepository<Player, Integer> {


    public PlayerRepository(EntityManager em) {
        super(em, Player.class);
    }

    @Override
    public Player findByName(String name) {
        try {
            CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
            var criteriaQuery = criteriaBuilder.createQuery(Player.class);
            var root = criteriaQuery.from(Player.class);

            return em.createQuery(
                    criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("lastName"), name)))
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

  public Optional<Player> findByIdOptional(Integer integer) {
      try {
          CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
          var criteriaQuery = criteriaBuilder.createQuery(Player.class);
          var root = criteriaQuery.from(Player.class);
          return Optional.of(em.createQuery(
                  criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), integer)))
                  .getSingleResult());
      } catch (NoResultException ex) {
          return null;
      }
  }
}
