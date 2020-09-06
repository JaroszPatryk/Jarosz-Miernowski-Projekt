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

@Getter
@RequiredArgsConstructor
public class LeagueService {

  private final CountryRepository countryRepository;
  private final LeagueRepository leagueRepository;
  private final EntityManager em;

  public League saveLeague(Country country, League league) {
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

  public Country getCountryByName(String name){
    return countryRepository.findByName(name);
  }
}
