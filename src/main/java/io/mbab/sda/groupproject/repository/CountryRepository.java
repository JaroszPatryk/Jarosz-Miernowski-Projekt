package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.Country;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CountryRepository implements CrudRepository<Country, Integer> {

  private final EntityManager em;
  // Class <Country> entityClass;

  public Optional<Country> findByName(String name) {
    Optional<Country> country = null;
    try {
      CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
      var criteriaQuery = criteriaBuilder.createQuery(Country.class);
      var root = criteriaQuery.from(Country.class);
      country =
          Optional.of(
              em.createQuery(
                      criteriaQuery
                          .select(root)
                          .where(criteriaBuilder.equal(root.get("name"), name)))
                  .getSingleResult());
    } catch (NoResultException ex) {
      return Optional.empty();
    }
    return country;
  }

  @Override
  public List<Country> getAll() {
    return em.createQuery("FROM Country", Country.class).getResultList();
  }

  @Override
  public Country findById(Integer id) {
    return null;
  }

  @Override
  public Country create(Country entity) {
    em.getTransaction().begin();
    em.persist(entity);
    em.getTransaction().commit();
    return entity;
  }

  @Override
  public Country update(Country entity) {
    return null;
  }

  @Override
  public void delete(Integer o) {}
}
