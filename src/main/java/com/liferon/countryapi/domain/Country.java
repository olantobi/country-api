package com.liferon.countryapi.domain;

import com.liferon.countryapi.config.Auditable;
import com.liferon.countryapi.model.CountryModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "countries")
@NoArgsConstructor
public class Country extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String continent;

    public Country(CountryModel model) {
        this.name = model.getName();
        this.continent = model.getContinent();
    }
}
