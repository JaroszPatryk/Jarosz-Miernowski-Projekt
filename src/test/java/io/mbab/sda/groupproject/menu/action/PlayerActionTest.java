package io.mbab.sda.groupproject.menu.action;

//

import io.mbab.sda.groupproject.dto.CountryDto;
import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.mapper.CountryMapper;
import io.mbab.sda.groupproject.mapper.PlayerMapper;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.CountryRepository;
import io.mbab.sda.groupproject.repository.TeamRepository;
import io.mbab.sda.groupproject.service.CountryService;
import io.mbab.sda.groupproject.service.PlayerService;
import io.mbab.sda.groupproject.service.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.OneToMany;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PlayerActionTest {

    private MenuActionContext menuActionContext;
    private CountryRepository countryRepository;
    private PlayerService playerService;
    private TeamRepository teamRepository;
    private PlayerAction playerAction;
    private TeamService teamService;
    private CountryService countryService;
    private CountryMapper countryMapper;
    private PlayerMapper playerMapper;

    @BeforeEach
    void initialization() {
        menuActionContext = mock(MenuActionContext.class);
        countryRepository = mock(CountryRepository.class);
        playerService = mock(PlayerService.class);
        teamRepository = mock(TeamRepository.class);
        teamService = mock(TeamService.class);
        countryService = mock(CountryService.class);
        playerAction = new PlayerAction(menuActionContext, countryService, playerService, teamService);
        countryMapper = new CountryMapper();
        playerMapper = new PlayerMapper();
    }

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
    @Test
    void shouldReturnCountryWhenCountryIsInDatabase() {
        //given
        Country newCountry = new Country(1, "Polska");
        CountryDto countryDto = countryMapper.entityToDto(newCountry);
        String countryName = "Polska";

        when(countryService.findByNameOptional(countryName)).thenReturn(Optional.of(countryDto));
        //when
        CountryDto country = playerAction.getCountry(countryName);
        //then
        verify(countryService, times(1)).findByNameOptional(countryName);
        assertEquals(country.getName(), countryName);
        assertEquals(country.getId(), 1);

    }
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