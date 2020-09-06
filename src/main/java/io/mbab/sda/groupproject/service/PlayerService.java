//package io.mbab.sda.groupproject.service;
//
//import io.mbab.sda.groupproject.entity.Country;
//import io.mbab.sda.groupproject.entity.League;
//import io.mbab.sda.groupproject.entity.Player;
//import io.mbab.sda.groupproject.entity.Team;
//import io.mbab.sda.groupproject.repository.CountryRepository;
//import io.mbab.sda.groupproject.repository.PlayerRepository;
//import io.mbab.sda.groupproject.repository.TeamRepository;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.var;
//
//import javax.persistence.EntityManager;
//import javax.swing.text.html.parser.Entity;
//import java.util.List;
//
//@Getter
//@RequiredArgsConstructor
//public class PlayerService {
//  private final CountryRepository countryRepository;
//  private final TeamRepository teamRepository;
//  private final PlayerRepository playerRepository;
//  private final EntityManager em;
//
//  public Player savePlayer(Country country, Team team, Player player) {
//    Team newPlayer = null;
//
//     try{
//        em.getTransaction().begin();
//        countryRepository.create(country);
//        var teamWithCointry = team.toBuilder().
////        var playerWithCountry = player.toBuilder().country(country).build();
////        newPlayer = playerRepository.create(playerWithCountry)
//
//
//     }
//  }
//
////    return teamRepository.create(team);
////}
////
////    public League saveLeague(Country country, League league){
////        League newLeague = null;
////        try{
////            em.getTransaction().begin();
////            countryRepository.create(country);
////            var leagueWithCountry = league.toBuilder().country(country).build();
////            newLeague = leagueRepository.create(leagueWithCountry);
////            em.getTransaction().commit();
////        }catch (Exception ex){
////            em.getTransaction().rollback();
////        }
////        return newLeague;
////    }
////
////}
////
////
////}
