package io.mbab.sda.groupproject.dto;

import io.mbab.sda.groupproject.entity.Country;
import lombok.Builder;
import lombok.Value;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
@Builder(toBuilder = true)
public class CountryDto implements CrudDto<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 64)
    private String name;

    public Country toEntity() {
        return Country.builder().id(this.id).name(this.name).build();
    }

  public static CountryDto toDto(Country country) {
      if (country == null) return null;
    return CountryDto.builder().id(country.getId()).name(country.getName()).build();
  }
}
