package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.Player;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CountryRepository implements CrudRepository<Country, Integer> {

  private final EntityManager em;
  // Class <Country> entityClass;

  public Optional<Country> findByName(String name) {
    try {
      CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
      var criteriaQuery = criteriaBuilder.createQuery(Country.class);
      var root = criteriaQuery.from(Country.class);
      return Optional.of(
          em.createQuery(
                  criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("name"), name)))
              .getSingleResult());
    } catch (NoResultException ex) {
      return Optional.empty();
    }
  }

  @Override
  public List<Country> getAll() {
    return em.createQuery("FROM Country", Country.class).getResultList();
  }

  @Override
  public Country findById(Integer id) {
    //     .getSingleResult();CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    ////    var criteriaQuery = criteriaBuilder.createQuery(Country.class);
    ////    var root = criteriaQuery.from(Country.class);
    ////    return em.createQuery(
    ////            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id)))
    ////        .getSingleResult();
    String jpql = "from Country where id=: id";
    TypedQuery<Country> query = em.createQuery(jpql, Country.class);
    return query.setParameter("id", id).getSingleResult();
  }

  @Override
  public Country create(Country entity) {

    if (entity.getId() != null) {
      em.getTransaction().begin();
      em.persist(entity);
      em.getTransaction().commit();
    }
    return entity;
  }

  @Override
  public Country update(Country entity) {

    em.getTransaction().begin();
    em.merge(entity);
    em.getTransaction().commit();
    return entity;
  }

  @Override
  public void delete(Integer id) {
    em.getTransaction().begin();
    em.remove(findById(id));
    em.getTransaction().commit();
  }
}
