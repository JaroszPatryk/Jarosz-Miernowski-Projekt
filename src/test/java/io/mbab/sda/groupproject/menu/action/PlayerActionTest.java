package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.Player;
import io.mbab.sda.groupproject.entity.Team;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MainAction;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.CountryRepository;
import io.mbab.sda.groupproject.repository.TeamRepository;
import io.mbab.sda.groupproject.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerActionTest {

//    private MenuActionContext menuActionContext;
//    private CountryRepository countryRepository;
//    private PlayerService playerService;
//    private TeamRepository teamRepository;
//    private PlayerAction playerAction;
//
//    @BeforeEach
//    void initialization() {
//        menuActionContext = mock(MenuActionContext.class);
//        countryRepository = mock(CountryRepository.class);
//        playerService = mock(PlayerService.class);
//        teamRepository = mock(TeamRepository.class);
//        playerAction = new PlayerAction(menuActionContext, countryRepository,playerService,teamRepository);
//    }
//
//    @Test
//    void shouldCreatePlayer(){
//        //given
//        Player player = mock(Player.class);
//        //when
//        playerAction.createPlayer(player);
//        //then
//        verify(playerService, times(1)).save(player);
//    }
//
//    @Test
//    void shouldReturnPlayerBuilder(){
//
//        // It does not make sense
//
//        //given
//        String firtsName = "Jan";
//        String lastName = "Kowalski";
//        String dateOfBirth = "Jan";
//        Country country = new Country(1,"Polska");
//
//        Player.PlayerBuilder playerBuilder = Player.builder()
//                .firstName(firtsName)
//                .lastName(lastName)
//                .dateOfBirth(dateOfBirth)
//                .country(country);
//        //when
//        Player.PlayerBuilder playerBuilderFromMethod = playerAction.getPlayerBuilder(
//                firtsName, lastName, dateOfBirth, country);
//        //then
//        assertEquals(playerBuilder.toString(),playerBuilderFromMethod.toString());
//    }
//
//    @Test
//    void shouldReturnCountryWhenCountryIsNotInDatabase(){
//        //given
//        String countryName = "Polska";
//        when(countryRepository.findByNameOptional(countryName)).thenReturn(Optional.empty());
//        //when
//        Country country = playerAction.getCountry(countryName);
//        //then
//        verify(countryRepository,times(1)).findByName(countryName);
//        assertEquals(country.getName(), countryName);
//        assertNull(country.getId());
//
//    }
//
//    @Test
//    void shouldReturnCountryWhenCountryIsInDatabase(){
//        //given
//        Country newCountry = new Country(1,"Polska");
//        String countryName = "Polska";
//        when(countryRepository.findByNameOptional(countryName)).thenReturn(Optional.of(newCountry));
//        //when
//        Country country = playerAction.getCountry(countryName);
//        //then
//        verify(countryRepository,times(1)).findByName(countryName);
//        assertEquals(country.getName(), countryName);
//        assertEquals(country.getId(), 1);
//
//    }
//
//    @Test
//    void shouldGoToMenuWhenPressZero() {
//        // given
//        var scanner = mock(CustomScanner.class);
//
//        when(scanner.nextLine()).thenReturn("0");
//        when(menuActionContext.use(MainAction.class)).thenReturn(menuActionContext);
//        // when
//        boolean isZero = playerAction.pressedZero("0");
//
//        // then
//        verify(menuActionContext, times(1)).execute();
//        assertTrue(isZero);
//    }
//
//    @Test
//    void shouldGoToMenuWhenPressNotZero() {
//        // given
//        var scanner = mock(CustomScanner.class);
//
//        when(scanner.nextLine()).thenReturn("0");
//        when(menuActionContext.use(MainAction.class)).thenReturn(menuActionContext);
//        // when
//        boolean isZero = playerAction.pressedZero("1");
//
//        // then
//        verify(menuActionContext, times(0)).execute();
//        assertFalse(isZero);
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = {"", " "})
//    void shouldFindTeamForBlankValueofTeamName(String value){
//        //given
//        Player.PlayerBuilder playerBuilder = Player.builder();
//        boolean isNull;
//        when(teamRepository.findByName(value)).thenReturn(null);
//        //when
//        isNull = playerAction.searchTeam(playerBuilder,value);
//        //then
//        assertNull(playerBuilder.build().getTeam());
//        assertTrue(isNull);
//        verify(teamRepository, times(0)).findByName(value);
//    }
//
//    @Test
//    void shouldFindTeamWhenTeamDoesNotExistsInDatabase(){
//        //given
//        Player.PlayerBuilder playerBuilder = Player.builder();
//        String teamName = "Real";
//        boolean isNull;
//        when(teamRepository.findByName(teamName)).thenReturn(null);
//        //when
//        isNull = playerAction.searchTeam(playerBuilder,teamName);
//        //then
//        assertNull(playerBuilder.build().getTeam());
//        assertFalse(isNull);
//        verify(teamRepository, times(1)).findByName(teamName);
//    }
//
//    @Test
//    void shouldFindTeamWhenTeamExistsInDatabase(){
//        //given
//        Player.PlayerBuilder playerBuilder = Player.builder();
//        String teamName = "Real";
//        Team team = Team.builder().name("Real").build();
//        boolean isNull;
//        when(teamRepository.findByName(teamName)).thenReturn(team);
//        //when
//        isNull = playerAction.searchTeam(playerBuilder,teamName);
//        //then
//        assertNull(playerBuilder.build().getTeam());
//        assertTrue(isNull);
//        verify(teamRepository, times(1)).findByName(teamName);
//    }


}