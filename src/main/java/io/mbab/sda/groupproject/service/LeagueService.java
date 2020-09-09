package io.mbab.sda.groupproject.service;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.repository.CountryRepository;
import io.mbab.sda.groupproject.repository.CrudRepository;
import io.mbab.sda.groupproject.repository.LeagueRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class LeagueService implements CrudService<League, Integer> {

    private final CountryRepository countryRepository;
    private final LeagueRepository leagueRepository;

    //  public League save(Country country, League league) {
    //    League newLeague = null;
    //    try {
    //      em.getTransaction().begin();
    //      countryRepository.create(country);
    //      var leagueWithCountry = league.toBuilder().country(country).build();
    //      newLeague = leagueRepository.create(leagueWithCountry);
    //      em.getTransaction().commit();
    //    } catch (Exception ex) {
    //    em.getTransaction().rollback();
    //  }
    //
    //    if(country.getId()==null){
    //      countryRepository.create(country);
    //    }
    //
    //    return newLeague;
    //  }

    public Optional<Country> getCountryByName(String name) {
        return countryRepository.findByNameOptional(name);
    }

    @Override
    public List<League> getAll() {
        return leagueRepository.getAll();
    }

    @Override
    public League findById(Integer integer) {
        return leagueRepository.findById(integer);
    }

    @Override
    public League save(League entity) {
        if (entity.getId() == null) {
            entity = leagueRepository.create(entity);
        }
        return entity;
    }

    @Override
    public League update(League entity) {
        return leagueRepository.update(entity);
    }

    @Override
    public void delete(Integer integer) {
        leagueRepository.delete(integer);
    }
}
