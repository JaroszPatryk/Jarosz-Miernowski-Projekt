package io.mbab.sda.groupproject.menu.action;

import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.entity.Team;
import io.mbab.sda.groupproject.menu.CustomScanner;
import io.mbab.sda.groupproject.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
public class CreateTeamAction implements MenuAction {

    CustomScanner cs;
    LeagueRepository leagueRepository;
//    CountryRepository countryRepository;


    @Override
    public void execute() {

    System.out.println("--> Wciśnięcie '0' powoduję powtór do menu głównego <--");
    System.out.println("Podaj nazwę drużyny:");


        String name = cs.nextLine();
        var builder = Team.builder().name(name);

        System.out.println("Podaj nawę Ligi:");
        String leagueName = cs.nextLine();

        League league = leagueRepository.findByName(leagueName);
        if(league == null){
      System.out.println("Tworzysz nową lige o nazwie " + leagueName);
      System.out.println("Z jakiego Państwa jest ta liga?");
      String country = cs.nextLine();


        }else {
            builder.league(league);
        }

    System.out.println("Podaj nawę miasta:");
    String city = cs.nextLine();
    builder.city(city);

    System.out.println("Podaj wartość drużyny");
    double value = Double.parseDouble(cs.nextLine());
    builder.city(city);
    }

}
