package io.mbab.sda.groupproject.service;

import io.mbab.sda.groupproject.dto.CountryDto;
import io.mbab.sda.groupproject.dto.LeagueDto;
import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.entity.League;
import io.mbab.sda.groupproject.mapper.CrudMapper;
import io.mbab.sda.groupproject.repository.CountryRepository;
import io.mbab.sda.groupproject.repository.CrudRepository;
import io.mbab.sda.groupproject.repository.LeagueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LeagueService implements CrudService<LeagueDto, Integer> {

    private final CountryRepository countryRepository;
    private final LeagueRepository leagueRepository;
    protected final CrudMapper<League, LeagueDto> crudMapper;
    protected final CrudMapper<Country, CountryDto> crudMapperCountry;

    //  public League save(Country country, League league) {
    //    League newLeague = null;
    //    try {
    //      em.getTransaction().begin();
    //      countryRepository.create(country);
    //      var leagueWithCountry = league.toBuilder().country(country).build();
    //      newLeague = leagueRepository.create(leagueWithCountry);
    //      em.getTransaction().commit();
    //    } catch (Exception ex) {
    //    em.getTransaction().rollback();
    //  }
    //
    //    if(country.getId()==null){
    //      countryRepository.create(country);
    //    }
    //
    //    return newLeague;
    //  }

    public Optional<CountryDto> getCountryByName(String name) {

        return countryRepository.findByNameOptional(name).map(crudMapperCountry::entityToDto);
    }

    @Override
    public List<LeagueDto> getAll() {
        return leagueRepository.getAll().stream()
                .map(crudMapper::entityToDto)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public LeagueDto findById(Integer integer) {
        return crudMapper.entityToDto(leagueRepository.findById(integer));
    }

    @Override
    public LeagueDto save(LeagueDto dto) {

        League league = crudMapper.dtoToEntity(dto);

        if (league.getId() == null) {
            league = leagueRepository.create(league);
        }
        return crudMapper.entityToDto(league);
    }

    @Override
    public LeagueDto update(LeagueDto dto) {
        League league = crudMapper.dtoToEntity(dto);
        leagueRepository.update(league);
        return crudMapper.entityToDto(league);
    }

    @Override
    public void delete(Integer integer) {
        leagueRepository.delete(integer);
    }

    @Override
    public Optional<LeagueDto> findByIdOptional(Integer integer) {
        return leagueRepository.findByIdOptional(integer).map(LeagueDto::toDto);
    }
}
