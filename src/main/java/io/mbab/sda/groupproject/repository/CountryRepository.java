package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.City;
import io.mbab.sda.groupproject.entity.Country;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CountryRepository implements CrudRepository<Country, Integer> {

  private final EntityManager em;
  Class <Country> entityClass;

  public Country findByName(String name){
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(entityClass);
    var root = criteriaQuery.from(entityClass);
    var entity =
            em.createQuery(
                    criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("name"), name)))
                    .getSingleResult();

    return findByName(name);
  }
  @Override
  public List<Country> getAll() {
    return em.createQuery("FROM country", Country.class)
            .getResultList();
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
