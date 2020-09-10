package io.mbab.sda.groupproject.menu;

import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.entity.Player;
import io.mbab.sda.groupproject.entity.Team;
import io.mbab.sda.groupproject.repository.CountryRepository;
import io.mbab.sda.groupproject.repository.LeagueRepository;
import io.mbab.sda.groupproject.repository.PlayerRepository;
import io.mbab.sda.groupproject.repository.TeamRepository;
import io.mbab.sda.groupproject.service.LeagueService;
import io.mbab.sda.groupproject.service.PlayerService;
import io.mbab.sda.groupproject.service.TeamService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InitDatabase {

    private final CountryRepository countryRepository;
    private final TeamRepository teamRepository;
    private final LeagueRepository leagueRepository;
    private final PlayerRepository playerRepository;

    public void init() {

        Country poland = addCountryToDatabase("Poland");
        Country spain = addCountryToDatabase("Spain");
        Country england = addCountryToDatabase("England");
        Country germany = addCountryToDatabase("Germany");
        Country france = addCountryToDatabase("France");
        Country portugal = addCountryToDatabase("Portugal");

        League ekstraklasa = addLeagueToDatabase("Ekstraklasa", poland);
        League laLiga = addLeagueToDatabase("La Liga", spain);
        League premierLeague = addLeagueToDatabase("Premier League", england);
        League bundesliga = addLeagueToDatabase("Bundesliga", germany);
        League ligue1 = addLeagueToDatabase("Ligue 1", france);
        League seriaA = addLeagueToDatabase("Seria A", null);
        League championLeague = addLeagueToDatabase("Chempion League", england);

        Team legia = addTeamToDatabase("Legia", "Warszawa", "15m", poland, ekstraklasa);
        Team wisla = addTeamToDatabase("Wisła", "Kraków", "5m", poland, ekstraklasa);
        Team lech = addTeamToDatabase("Lech", "Poznań", "6m", poland, ekstraklasa);
        Team barcelona = addTeamToDatabase("Fc Barcelona", "Barcelona", "900m", spain, laLiga);
        Team real = addTeamToDatabase("Real", "Madryt", "1b", spain, laLiga);
        Team athletico = addTeamToDatabase("Athletico", "Madryt", "300m", spain, laLiga);
        Team espanyol = addTeamToDatabase("Espanyol", "Barcelona", "100m", spain, laLiga);
        Team manCity = addTeamToDatabase("Legia", "Warszawa", "10m", poland, ekstraklasa);
        Team manU = addTeamToDatabase("Manchester United", "Manchester", "800m", england, premierLeague);
        Team liverpool = addTeamToDatabase("Liverpool FC", "Liverpool", "700m", england, premierLeague);
        Team bayern = addTeamToDatabase("Bayern", "Monachium", "800m", germany, bundesliga);
        Team borussia = addTeamToDatabase("Borussia", "Dortmund", "400m", germany, bundesliga);
        Team bayer = addTeamToDatabase("Bayer", "Leverkusen", "200m", germany, bundesliga);
        Team psg = addTeamToDatabase("PSG", "Paryż", "700m", france, ligue1);
        Team lyon = addTeamToDatabase("Legia", "Warszawa", "10m", france, ligue1);
        Team acMilan = addTeamToDatabase("Ac Milan", "Mediolan", "500m", null, seriaA);
        Team inter = addTeamToDatabase("Inter", "Mediolan", "45m", null, seriaA);
        Team juventus = addTeamToDatabase("Juventus", "Turyn", "600m", null, seriaA);
        Team torino = addTeamToDatabase("Torino Fc", "Turyn", "2500m", null, seriaA);
        Team hull = addTeamToDatabase("Hull City A.F.C.", "Hull", "40", england, championLeague);

        Player boruc = addPlayerToDatabase("Artur", "Boruc", "20 lut 1980", poland, legia);
        Player jedrejczyk = addPlayerToDatabase("Artur", "Jędrzejczyk", "4 lis 1987", poland, legia);
        Player astis = addPlayerToDatabase("Inaki", "Astiz", "13 lip 1983", poland, legia);
        Player remy = addPlayerToDatabase("William", "Remy", "27 cze 1990", poland, legia);
        Player lewczuk = addPlayerToDatabase("Igor", "Lewczuk", "5 sty 1985", poland, legia);
        Player messi = addPlayerToDatabase("Lionel", "Messi", "24 cze 1980", spain, barcelona);
        Player lewandowski = addPlayerToDatabase("Robert", "Lewandowski", "9 wr 1989", poland, bayer);
        Player ronaldo = addPlayerToDatabase("Christiano", "Ronaldo", "8 lut 1985", portugal, hull);


    }

    private Country addCountryToDatabase(String name) {
        Country country = Country.builder().name(name).build();
        return countryRepository.create(country);
    }

    private Team addTeamToDatabase(
            String name, String city, String value, Country country, League league) {
        Team team =
                Team.builder().name(name).city(city).value(value).country(country).league(league).build();
        return teamRepository.create(team);
    }

    private Player addPlayerToDatabase(
            String firstName, String lastName, String dateOfBirth, Country country, Team team) {
        Player player =
                Player.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .dateOfBirth(dateOfBirth)
                        .country(country)
                        .team(team)
                        .build();
        return playerRepository.create(player);
    }

    private League addLeagueToDatabase(String name, Country country) {
        League league = League.builder().name(name).country(country).build();
        return leagueRepository.create(league);
    }
}
