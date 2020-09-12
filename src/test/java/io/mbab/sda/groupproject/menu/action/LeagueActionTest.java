package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.dto.CountryDto;
import io.mbab.sda.groupproject.dto.LeagueDto;
import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.mapper.CountryMapper;
import io.mbab.sda.groupproject.mapper.LeagueMapper;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.CountryRepository;
import io.mbab.sda.groupproject.repository.LeagueRepository;
import io.mbab.sda.groupproject.service.CountryService;
import io.mbab.sda.groupproject.service.LeagueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LeagueActionTest {
  private MenuActionContext menuActionContext;
  private LeagueService leagueService;
  private LeagueAction leagueAction;
  private LeagueMapper leagueMapper;
  private CountryMapper countryMapper;

  @BeforeEach
  void initialization() {
    menuActionContext = mock(MenuActionContext.class);
    leagueService = mock(LeagueService.class);
    leagueAction = new LeagueAction(menuActionContext, leagueService);
    leagueMapper = new LeagueMapper();
    countryMapper = new CountryMapper();
  }

  @Test
  void shouldReturnLeague() {
    // given
    String name = "Real";
    Country country = new Country(1, "Hiszpania");
    CountryDto countryDto = countryMapper.entityToDto(country);

    // when
    LeagueDto leagueDto = leagueAction.getLeague(name, countryDto);
    // then
    assertEquals(leagueDto.getName(), name);
    assertEquals(leagueDto.getCountry(), countryDto);
  }

  @Test
  void ShouldSaveLeaue() {
    // given

    League league = mock(League.class);
    LeagueDto leagueDto = leagueMapper.entityToDto(league);
    // when
    leagueAction.save(leagueDto);
    // then
    verify(leagueService, times(1)).save(leagueDto);
  }

  @Test
  void shouldReturnCountryWhenCountryIsNotInDatabase() {
    // given
    CountryRepository countryRepository = mock(CountryRepository.class);
    LeagueRepository leagueRepository = mock(LeagueRepository.class);

    LeagueService leagueService =
            new LeagueService(countryRepository, leagueRepository, leagueMapper, countryMapper);

    LeagueAction leagueAction = new LeagueAction(menuActionContext, leagueService);

    String name = "Polska";

    when(leagueService.getCountryByName(name)).thenReturn(Optional.empty());

    // when
    CountryDto countryDto = leagueAction.getCountry(name);

    // then
    verify(countryRepository, times(1)).findByNameOptional(name);
    assertEquals(countryDto.getName(), "Polska");
    assertNull(countryDto.getId());
  }

  @Test
  void shouldReturnCountryWhenCountryIsInDatabase() {
    // given

    CountryRepository countryRepository = mock(CountryRepository.class);
    LeagueRepository leagueRepository = mock(LeagueRepository.class);

    LeagueAction leagueAction = new LeagueAction(menuActionContext, leagueService);

    Country country = new Country(1, "Polska");
    CountryDto countryDto = countryMapper.entityToDto(country);

    String name = "Polska";

    when(leagueService.getCountryByName(name)).thenReturn(Optional.of(countryDto));

    // when
    CountryDto wantedCountry = leagueAction.getCountry(name);

    // then

    assertEquals(wantedCountry.getName(), "Polska");
    assertEquals(wantedCountry.getId(), 1);
  }

  @Test
  void shouldGoToMenuWhenPressZero() {
    // given
    var scanner = mock(CustomScanner.class);

    when(scanner.nextLine()).thenReturn("0");
    when(menuActionContext.use(MainAction.class)).thenReturn(menuActionContext);
    // when
    boolean isZero = leagueAction.pressedZero("0");

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
    boolean isZero = leagueAction.pressedZero("1");

    // then
    verify(menuActionContext, times(0)).execute();
    assertFalse(isZero);
  }
}
