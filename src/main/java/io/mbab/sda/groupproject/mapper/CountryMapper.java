package io.mbab.sda.groupproject.mapper;

import io.mbab.sda.groupproject.dto.CountryDto;
import io.mbab.sda.groupproject.entity.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper implements CrudMapper<Country, CountryDto> {
    @Override
    public Country dtoToEntity(CountryDto countryDto) {
        if (countryDto == null) return null;

        return countryDto.toEntity();
    }

    @Override
    public CountryDto entityToDto(Country country) {
        if (country == null) return null;

        return CountryDto.toDto(country);
    }


}
