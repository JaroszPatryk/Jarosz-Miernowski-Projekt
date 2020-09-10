package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.entity.Team;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.CountryRepository;
import io.mbab.sda.groupproject.repository.LeagueRepository;
import io.mbab.sda.groupproject.service.CountryService;
import io.mbab.sda.groupproject.service.LeagueService;
import io.mbab.sda.groupproject.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeamActionTest {
//    private MenuActionContext menuActionContext;
//    private TeamService teamService;
//    private CountryService countryService;
//    private LeagueService leagueService;
//    private TeamAction teamAction;
//
//    @BeforeEach
//    void initialization() {
//        menuActionContext = mock(MenuActionContext.class);
//        teamService = mock(TeamService.class);
//        countryService = mock(CountryService.class);
//        leagueService = mock(LeagueService.class);
//        teamAction = new TeamAction(menuActionContext, teamService, countryService, leagueService);
//    }
//
//    @Test
//    void ShouldSaveLeaue() {
//        // given
//        League league = mock(League.class);
//        // when
//        teamAction.saveLeague(league);
//        // then
//        verify(leagueService, times(1)).save(league);
//    }
//
//    @Test
//    void ShouldSaveTeam() {
//        // given
//        Team team = mock(Team.class);
//        // when
//        teamAction.saveTeam(team);
//        // then
//        verify(teamService, times(1)).save(team);
//    }
//
//    @Test
//    void shouldReturnLeagueWhenLeagueIsNotInDatabase() {
//        //given
//        String leagueName = "La Liga";
//        when(teamService.getLeagueByName(leagueName)).thenReturn(Optional.empty());
//        //when
//        League league = teamAction.getLeague(leagueName);
//        //then
//        verify(teamService, times(1)).getLeagueByName(leagueName);
//        assertNull(league);
//    }
//
//    @Test
//    void shouldReturnLeagueWhenLeagueIsInDatabase() {
//        //given
//        String leagueName = "La Liga";
//        League newLeague = League.builder().name("La Liga").build();
//        leagueService.save(newLeague);
//        when(teamService.getLeagueByName(leagueName)).thenReturn(Optional.of(newLeague));
//        //when
//        League league = teamAction.getLeague(leagueName);
//        //then
//        verify(teamService, times(1)).getLeagueByName(leagueName);
//        assertEquals(league, newLeague);
//
//    }
//
//    @Test
//    void shouldSaveCountry() {
//        //given
//        Country country = Country.builder().build();
//        //when
//        teamAction.saveCountry(country);
//        //then
//        verify(countryService, times(1)).save(country);
//    }
//
//    @Test
//    void shouldReturnCountryWhenCountryIsNotInDatebase() {
//        //given
//        String countryName = "Hiszpania";
//        when(teamService.getCountryByName(countryName)).
//                thenReturn(Optional.of(Country.builder().name(countryName).build()));
//        //when
//        Country country = teamAction.getCountry(countryName);
//        //then
//        verify(teamService, times(1)).getCountryByName(countryName);
//        assertEquals(country.getName(), countryName);
//        assertNull(country.getId());
//    }
//
//    @Test
//    void shouldReturnCountryWhenCountryIsInDatebase() {
//        //given
//        String countryName = "Hiszpania";
//        Country newCountry = Country.builder().name("Hiszpania").id(1).build();
//        when(teamService.getCountryByName(countryName))
//                .thenReturn(Optional.of(newCountry));
//        //when
//        Country country = teamAction.getCountry(countryName);
//        //then
//        verify(teamService, times(1)).getCountryByName(countryName);
//        assertEquals(country.getName(), countryName);
//        assertNotNull(country.getId());
//    }
//

}