package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.dto.CountryDto;
import io.mbab.sda.groupproject.dto.PlayerDto;
import io.mbab.sda.groupproject.dto.TeamDto;
import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.Player.PlayerBuilder;
import io.mbab.sda.groupproject.entity.Team;
import io.mbab.sda.groupproject.menu.MainAction;
import io.mbab.sda.groupproject.menu.MenuActionContext;
import io.mbab.sda.groupproject.repository.CountryRepository;
import io.mbab.sda.groupproject.service.CountryService;
import io.mbab.sda.groupproject.service.PlayerService;
import io.mbab.sda.groupproject.service.TeamService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PlayerAction {


    private final MenuActionContext ctx;
    private final CountryService countryService;
    private final PlayerService playerService;
    private final TeamService teamService;

    public void createPlayer(PlayerDto dto) {
        playerService.save(dto);
    }

    public PlayerDto.PlayerDtoBuilder getPlayerBuilder(
            String firstName, String lastName, String dateOfBirth, CountryDto country) {
        return PlayerDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .dateOfBirth(dateOfBirth)
                .country(country);
    }

    public List<TeamDto> getAll() {
        return teamService.getAll();
    }

    public CountryDto getCountry(String countryName) {
        return countryService
                .findByNameOptional(countryName)
                .orElseGet(() -> CountryDto.builder().name(countryName).build());
    }

    public boolean pressedZero(String input) {
        if (input.equals("0")) {
            ctx.use(MainAction.class).execute();
            return true;
        }
        return false;
    }

    //  public void addTeam(PlayerBuilder builder) {
    //    String teamName = scanner.nextLine();
    //
    //    searchTeam(builder, teamName);
    //  }

    public boolean searchTeam(PlayerDto.PlayerDtoBuilder builder, String teamName) {
        if (" ".equals(teamName) || "".equals(teamName)) {
            builder.team(null);
            return true;
        }
        TeamDto team = teamService.findByName(teamName);
        if (team == null) {
            return false;
        }
        teamService.save(team);
        builder.team(team).build();
      return true;

      //      teamRepository
      //          .findByName(teamName)
      //          .ifPresentOrElse(
      //              team -> builder.team(team).build(),
      //              () -> {
      //                System.out.println("Nie znaleziono drużyny o podanej nazwie!");
      //                System.out.println("Wciśnij '0' jak chcesz wyjść do głownego menu.");
      //
      //                addTeam(builder);
      //              });
  }
}
