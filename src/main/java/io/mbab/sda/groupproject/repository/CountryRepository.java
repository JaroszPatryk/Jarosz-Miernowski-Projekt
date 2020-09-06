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
    try {
      CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
      var criteriaQuery = criteriaBuilder.createQuery(Country.class);
      var root = criteriaQuery.from(Country.class);
      return Optional.of(
              em.createQuery(
                      criteriaQuery
                          .select(root)
                          .where(criteriaBuilder.equal(root.get("name"), name)))
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
    return null;
  }

  @Override
  public Country create(Country country) {
    if (country.getId() == null) {
      em.getTransaction().begin();
      em.persist(country);
      em.getTransaction().commit();
    }
    return country;
  }

  @Override
  public Country update(Country entity) {
    return null;
  }

  @Override
  public void delete(Integer o) {}
}
