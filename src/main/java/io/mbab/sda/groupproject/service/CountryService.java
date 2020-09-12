package io.mbab.sda.groupproject.service;

import io.mbab.sda.groupproject.dto.CountryDto;
import io.mbab.sda.groupproject.entity.Country;
import io.mbab.sda.groupproject.mapper.CrudMapper;
import io.mbab.sda.groupproject.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService implements CrudService<CountryDto, Integer> {

  private final CountryRepository countryRepository;
  protected final CrudMapper<Country, CountryDto> crudMapper;

  public CountryDto save(CountryDto countryDto) {

    Country country = crudMapper.dtoToEntity(countryDto);

    if (country.getId() == null) {
      country = countryRepository.create(country);
    }
    countryDto = crudMapper.entityToDto(country);
    return countryDto;

  }

  public Optional<CountryDto> findByNameOptional(String name) {

    return countryRepository.findByNameOptional(name).map(crudMapper::entityToDto);
  }

  @Override
  public List<CountryDto> getAll() {
    return countryRepository.getAll().stream()
            .map(crudMapper::entityToDto)
            .collect(Collectors.toUnmodifiableList());
  }

  @Override
  public CountryDto findById(Integer integer) {
    return crudMapper.entityToDto(countryRepository.findById(integer));
  }

  @Override
  public CountryDto update(CountryDto dto) {
    Country country = crudMapper.dtoToEntity(dto);
    countryRepository.update(country);
    return crudMapper.entityToDto(country);
  }

  @Override
  public void delete(Integer integer) {
    countryRepository.delete(integer);
  }

  @Override
  public Optional<CountryDto> findByIdOptional(Integer integer) {
    return countryRepository.findByIdOptional(integer).map(CountryDto::toDto);
  }
}
