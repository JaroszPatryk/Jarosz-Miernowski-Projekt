package io.mbab.sda.groupproject.menu.action;

//

import io.mbab.sda.groupproject.dto.CountryDto;
import io.mbab.sda.groupproject.dto.PlayerDto;
import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.Team;
import io.mbab.sda.groupproject.mapper.CountryMapper;
import io.mbab.sda.groupproject.mapper.PlayerMapper;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.CountryRepository;
import io.mbab.sda.groupproject.repository.PlayerRepository;
import io.mbab.sda.groupproject.repository.TeamRepository;
import io.mbab.sda.groupproject.service.CountryService;
import io.mbab.sda.groupproject.service.PlayerService;
import io.mbab.sda.groupproject.service.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerActionTest {

  private MenuActionContext menuActionContext;
  private CountryRepository countryRepository;
  private TeamRepository teamRepository;
  private PlayerRepository playerRepository;
  private PlayerService playerService;
  private CountryService countryService;
  private TeamService teamService;
  private PlayerAction playerAction;
  private CountryMapper countryMapper;
  private PlayerMapper playerMapper;

  @BeforeEach
  void initialization() {
    menuActionContext = mock(MenuActionContext.class);
    countryRepository = mock(CountryRepository.class);
    playerRepository = mock(PlayerRepository.class);
    playerService = mock(PlayerService.class);
    teamRepository = mock(TeamRepository.class);
    teamService = mock(TeamService.class);
    countryService = mock(CountryService.class);
    playerAction = new PlayerAction(menuActionContext, countryService, playerService, teamService);
    countryMapper = new CountryMapper();
    playerMapper = new PlayerMapper();
  }

//  @Test
//  void shouldCreatePlayer() {
//    // given
//    Player player = Player.builder().firstName("Mariusz").lastName("Wasyl").build();
//    when(playerRepository.findByName(player))
//    // when
//    Player actual = playerRepository.create(player);
//    // then
//    verify(playerRepository, times(1)).create(player);
//    assertEquals(player.getFirstName(), actual.getFirstName());
//    assertNotEquals(player.getId(), actual.getId());
//  }

  @Test
  void shouldReturnNewCountryWhenNotFound() {
    // given
    String countryName = "Polska";
    when(countryService.findByName(countryName)).thenReturn(Optional.empty());
    // when
    CountryDto country = playerAction.getCountry(countryName);
    // then
    verify(countryService, times(1)).findByName(countryName);
    assertEquals(country.getName(), countryName);
    assertNull(country.getId());
  }

  @Test
  void shouldReturnCountry() {
    // given
    Country newCountry = new Country(1, "Polska");
    CountryDto countryDto = countryMapper.entityToDto(newCountry);
    String countryName = "Polska";
    when(countryService.findByName(countryName)).thenReturn(Optional.of(countryDto));
    // when
    CountryDto country = playerAction.getCountry(countryName);
    // then
    verify(countryService, times(1)).findByName(countryName);
    assertEquals(country.getName(), countryName);
    assertEquals(country.getId(), 1);
  }

  @Test
  void shouldGoToMenuWhenPressZero() {
    // given
    var scanner = mock(CustomScanner.class);

    when(scanner.nextLine()).thenReturn("0");
    when(menuActionContext.use(MainAction.class)).thenReturn(menuActionContext);
    // when
    boolean isZero = playerAction.pressedZero("0");

    // then
    verify(menuActionContext, times(1)).execute();
    assertTrue(isZero);
  }

  @Test
  void shouldGoToMenuWhenPressNotZero() {
    // given
    var scanner = mock(CustomScanner.class);

    when(scanner.nextLine()).thenReturn("0");
    when(menuActionContext.use(MainAction.class)).thenReturn(menuActionContext);
    // when
    boolean isZero = playerAction.pressedZero("1");

    // then
    verify(menuActionContext, times(0)).execute();
    assertFalse(isZero);
  }

  @Test
  void shouldFindTeamForSpaceValueofTeamName() {
    // given
    String value = " ";
    PlayerDto.PlayerDtoBuilder playerDtoBuilder = PlayerDto.builder();
    boolean isNull;
    when(teamRepository.findByName(value)).thenReturn(null);
    // when
    isNull = playerAction.searchTeam(playerDtoBuilder, value);
    // then
    assertNull(playerDtoBuilder.build().getTeam());
    assertTrue(isNull);
    verify(teamRepository, times(0)).findByName(value);
  }

  @Test
  void shouldFindTeamForBlankValueofTeamName() {
    // given
    String value = "";
    PlayerDto.PlayerDtoBuilder playerDtoBuilder = PlayerDto.builder();
    boolean isNull;
    when(teamRepository.findByName(value)).thenReturn(null);
    // when
    isNull = playerAction.searchTeam(playerDtoBuilder, value);
    // then
    assertNull(playerDtoBuilder.build().getTeam());
    assertTrue(isNull);
    verify(teamRepository, times(0)).findByName(value);
  }

  @Test
  void shouldReturnNewTeamWhenNotFound() {
    // given
    PlayerDto.PlayerDtoBuilder playerDtoBuilder = PlayerDto.builder();
    String teamName = "Real";
    boolean isNull;
    when(teamRepository.findByName(teamName)).thenReturn(null);
    // when
    isNull = playerAction.searchTeam(playerDtoBuilder, teamName);
    // then
    assertNull(playerDtoBuilder.build().getTeam());
    assertFalse(isNull);
    verify(teamService, times(1)).findByName(teamName);
  }

  @Test
  void shouldReturnTeam() {
    // given
    PlayerDto.PlayerDtoBuilder playerDtoBuilder = PlayerDto.builder();
    String teamName = "Real";
    Team team = Team.builder().name("Real").build();
    boolean isNull;
    when(teamRepository.findByName(teamName)).thenReturn(team);
    // when
    isNull = playerAction.searchTeam(playerDtoBuilder, teamName);
    // then
    assertNull(playerDtoBuilder.build().getTeam());
    assertFalse(isNull);
    verify(teamService, times(1)).findByName(teamName);
  }
}
