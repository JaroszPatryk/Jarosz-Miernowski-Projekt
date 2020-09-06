package io.mbab.sda.groupproject.service;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.entity.Team;
import io.mbab.sda.groupproject.repository.CountryRepository;
import io.mbab.sda.groupproject.repository.LeagueRepository;
import io.mbab.sda.groupproject.repository.TeamRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public class TeamService {

  private final CountryRepository countryRepository;
  private final LeagueRepository leagueRepository;
  private final TeamRepository teamRepository;
  private final EntityManager em;

    public Team saveTeam(Country country, League league, Team team){
        try{
            em.getTransaction().begin();
            if (country.getId() == null){
                country = countryRepository.create(country);
            }
            league = league.toBuilder().country(country).build();
            if(league.getId() == null){
                league = leagueRepository.create(league);
            }
            team = team.toBuilder().league(league).build();
            em.persist(team);
            em.getTransaction().commit();
        }catch(Exception ex){
            em.getTransaction().rollback();
        }
        return team;
    }


    public Optional<Country> getCountryByName(String name) {
    return countryRepository.findByName(name);
  }

  public Optional<League> getLeagueByName(String name) {
    return leagueRepository.findByName(name);
  }
}
