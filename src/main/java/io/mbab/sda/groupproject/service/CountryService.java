package io.mbab.sda.groupproject.service;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.repository.CountryRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class CountryService implements CrudService<Country, Integer> {

  private final CountryRepository countryRepository;
  private final EntityManager em;

  public Country save(Country country) {
    try {
      em.getTransaction().begin();
      if (country.getId() == null) {
        country = countryRepository.create(country);
      }
      em.persist(country);
      em.getTransaction().commit();
    } catch (Exception ex) {
      em.getTransaction().rollback();
    }
    return country;
  }

  @Override
  public List<Country> getAll() {
    return null;
  }

  @Override
  public Country findById(Integer integer) {
    return null;
  }

  @Override
  public Country create(Country entity) {
    return null;
  }

  @Override
  public Country update(Country entity) {
    return null;
  }

  @Override
  public void delete(Integer integer) {}
}
