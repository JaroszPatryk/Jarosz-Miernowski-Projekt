package io.mbab.sda.groupproject.menu.action;

class LeagueActionTest {
//  private MenuActionContext menuActionContext;
//  private LeagueService leagueService;
//  private LeagueAction leagueAction;
//
//  @BeforeEach
//  void initialization() {
//    menuActionContext = mock(MenuActionContext.class);
//    leagueService = mock(LeagueService.class);
//    leagueAction = new LeagueAction(menuActionContext, leagueService);
//  }
//
//  @Test
//  void shouldReturnLeague() {
//    // given
//    String name = "Real";
//    Country country = new Country(1, "Hiszpania");
//    // when
//    League league = leagueAction.getLeague(name, country);
//    // then
//    assertEquals(league.getName(), name);
//    assertEquals(league.getCountry(), country);
//  }
//
//  @Test
//  void ShouldSaveLeaue() {
//    // given
//    League league = mock(League.class);
//    // when
//    leagueAction.save(league);
//    // then
//    verify(leagueService, times(1)).save(league);
//  }
//
//  @Test
//  void shouldReturnCountryWhenCountryIsNotInDatabase() {
//    // given
//    CountryRepository countryRepository = mock(CountryRepository.class);
//    LeagueRepository leagueRepository = mock(LeagueRepository.class);
//
//    LeagueService leagueService = new LeagueService(countryRepository, leagueRepository);
//
//    LeagueAction leagueAction = new LeagueAction(menuActionContext, leagueService);
//
//    String name = "Polska";
//
//    when(leagueService.getCountryByName(name)).thenReturn(Optional.empty());
//
//    // when
//    Country country = leagueAction.getCountry(name);
//
//    // then
//    verify(countryRepository, times(1)).findByName(name);
//    assertEquals(country.getName(), "Polska");
//    assertNull(country.getId());
//  }
//
//  @Test
//  void shouldReturnCountryWhenCountryIsInDatabase() {
//    // given
//
//    CountryRepository countryRepository = mock(CountryRepository.class);
//    LeagueRepository leagueRepository = mock(LeagueRepository.class);
//
//    leagueService = new LeagueService(countryRepository, leagueRepository);
//
//    LeagueAction leagueAction = new LeagueAction(menuActionContext, leagueService);
//
//    Country country = new Country(1, "Polska");
//    String name = "Polska";
//
//    when(leagueService.getCountryByName(name)).thenReturn(Optional.of(country));
//
//    // when
//    Country wantedCountry = leagueAction.getCountry(name);
//
//    // then
//    verify(countryRepository, times(1)).findByName(name);
//    assertEquals(wantedCountry.getName(), "Polska");
//    assertEquals(wantedCountry.getId(), 1);
//  }
//
//  @Test
//  void shouldGoToMenuWhenPressZero() {
//    // given
//    var scanner = mock(CustomScanner.class);
//
//    when(scanner.nextLine()).thenReturn("0");
//    when(menuActionContext.use(MainAction.class)).thenReturn(menuActionContext);
//    // when
//    boolean isZero = leagueAction.pressedZero("0");
//
//    // then
//    verify(menuActionContext, times(1)).execute();
//    assertTrue(isZero);
//  }
//
//  @Test
//  void shouldGoToMenuWhenPressNotZero() {
//    // given
//    var scanner = mock(CustomScanner.class);
//
//    when(scanner.nextLine()).thenReturn("0");
//    when(menuActionContext.use(MainAction.class)).thenReturn(menuActionContext);
//    // when
//    boolean isZero = leagueAction.pressedZero("1");
//
//    // then
//    verify(menuActionContext, times(0)).execute();
//    assertFalse(isZero);
//  }
}
