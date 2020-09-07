package io.mbab.sda.groupproject.service;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.CountryRepository;
import io.mbab.sda.groupproject.repository.LeagueRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class LeagueService implements CrudService<LeagueService, Integer> {

  private final CountryRepository countryRepository;
  private final LeagueRepository leagueRepository;
  private final EntityManager em;

  public League save(Country country, League league) {
    League newLeague = null;
    try {
      em.getTransaction().begin();
      countryRepository.create(country);
      var leagueWithCountry = league.toBuilder().country(country).build();
      newLeague = leagueRepository.create(leagueWithCountry);
      em.getTransaction().commit();
    } catch (Exception ex) {
      em.getTransaction().rollback();
    }
    return newLeague;
  }

  public League save(League league) {

    try {
      em.getTransaction().begin();
      if (league.getId() == null) {
        league = leagueRepository.create(league);
      }
      em.persist(league);
      em.getTransaction().commit();
    } catch (Exception ex) {
      em.getTransaction().rollback();
    }

    return league;
  }

  public Optional<Country> getCountryByName(String name) {
    return countryRepository.findByName(name);
  }

  @Override
  public List<LeagueService> getAll() {
    return null;
  }

  @Override
  public LeagueService findById(Integer integer) {
    return null;
  }

  @Override
  public LeagueService create(LeagueService entity) {
    return null;
  }

  @Override
  public LeagueService update(LeagueService entity) {
    return null;
  }

  @Override
  public void delete(Integer integer) {}
}
