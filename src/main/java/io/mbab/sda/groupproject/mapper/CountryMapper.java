package io.mbab.sda.groupproject.mapper;

import io.mbab.sda.groupproject.dto.CountryDto;
import io.mbab.sda.groupproject.entity.Country;

public class CountryMapper implements CrudMapper<Country, CountryDto> {
  @Override
  public Country dtoToEntity(CountryDto countryDto) {
    return countryDto.toEntity();
  }

  @Override
  public CountryDto entityToDto(Country country) {
    return CountryDto.toDto(country);
  }
}
