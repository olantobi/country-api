package com.liferon.countryapi.model;

import com.liferon.countryapi.domain.Country;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CountryModel {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String continent;

    public CountryModel(Country country) {
        this.id = country.getId();
        this.name = country.getName();
        this.continent = country.getContinent();
    }
}
