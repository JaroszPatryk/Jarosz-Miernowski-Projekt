package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.dto.CountryDto;
import io.mbab.sda.groupproject.dto.LeagueDto;
import io.mbab.sda.groupproject.dto.TeamDto;
import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.entity.Team;
import io.mbab.sda.groupproject.mapper.CountryMapper;
import io.mbab.sda.groupproject.mapper.LeagueMapper;
import io.mbab.sda.groupproject.mapper.TeamMapper;
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
    private MenuActionContext menuActionContext;
    private TeamService teamService;
    private CountryService countryService;
    private LeagueService leagueService;
    private TeamAction teamAction;
    private LeagueMapper leagueMapper;
    private CountryMapper countryMapper;
    private TeamMapper teamMapper;

    @BeforeEach
    void initialization() {
        menuActionContext = mock(MenuActionContext.class);
        teamService = mock(TeamService.class);
        countryService = mock(CountryService.class);
        leagueService = mock(LeagueService.class);
        teamAction = new TeamAction(menuActionContext, teamService, countryService, leagueService);
        leagueMapper = new LeagueMapper();
        countryMapper = new CountryMapper();
        teamMapper = new TeamMapper();
    }

    @Test
    void ShouldSaveLeaue() {
        // given
        League league = mock(League.class);
        LeagueDto leagueDto = leagueMapper.entityToDto(league);
        // when
        teamAction.saveLeague(leagueDto);
        // then
        verify(leagueService, times(1)).save(leagueDto);
    }

    @Test
    void ShouldSaveTeam() {
        // given
        Team team = mock(Team.class);
        TeamDto teamDto = teamMapper.entityToDto(team);
        // when
        teamAction.saveTeam(teamDto);
        // then
        verify(teamService, times(1)).save(teamDto);
    }

    @Test
    void shouldReturnLeagueWhenLeagueIsNotInDatabase() {
        //given
        String leagueName = "La Liga";
        when(teamService.getLeagueByName(leagueName)).thenReturn(Optional.empty());
        //when
        LeagueDto league = teamAction.getLeague(leagueName);
        //then
        verify(teamService, times(1)).getLeagueByName(leagueName);
        assertNull(league);
    }

    @Test
    void shouldReturnLeagueWhenLeagueIsInDatabase() {
        //given
        String leagueName = "La Liga";
        League newLeague = League.builder().name("La Liga").build();
        LeagueDto leagueDto = leagueMapper.entityToDto(newLeague);
        leagueService.save(leagueDto);
        when(teamService.getLeagueByName(leagueName)).thenReturn(Optional.of(leagueDto));
        //when
        LeagueDto league = teamAction.getLeague(leagueName);
        //then
        verify(teamService, times(1)).getLeagueByName(leagueName);
        assertEquals(league, leagueDto);

    }

    @Test
    void shouldSaveCountry() {
        //given
        Country country = Country.builder().build();
        CountryDto countryDto = countryMapper.entityToDto(country);
        //when
        teamAction.saveCountry(countryDto);
        //then
        verify(countryService, times(1)).save(countryDto);
    }

    @Test
    void shouldReturnCountryWhenCountryIsNotInDatebase() {
        //given
        String countryName = "Hiszpania";
        when(teamService.getCountryByName(countryName)).
                thenReturn(Optional.of(CountryDto.builder().name(countryName).build()));
        //when
        CountryDto country = teamAction.getCountry(countryName);
        //then
        verify(teamService, times(1)).getCountryByName(countryName);
        assertEquals(country.getName(), countryName);
        assertNull(country.getId());
    }

    @Test
    void shouldReturnCountryWhenCountryIsInDatebase() {
        //given
        String countryName = "Hiszpania";
        CountryDto newCountry = CountryDto.builder().name("Hiszpania").id(1).build();
        when(teamService.getCountryByName(countryName))
                .thenReturn(Optional.of(newCountry));
        //when
        CountryDto country = teamAction.getCountry(countryName);
        //then
        verify(teamService, times(1)).getCountryByName(countryName);
        assertEquals(country.getName(), countryName);
        assertNotNull(country.getId());
    }


}