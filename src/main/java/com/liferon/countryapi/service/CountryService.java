package com.liferon.countryapi.service;

import com.liferon.countryapi.domain.Country;
import com.liferon.countryapi.model.CountryModel;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    Optional<Country> getCountry(Long countryId);
    Country addCountry(CountryModel country);
    boolean updateCountry(CountryModel country);
    List<Country> getAllCountries();
    boolean deleteCountry(Long countryId);
}
