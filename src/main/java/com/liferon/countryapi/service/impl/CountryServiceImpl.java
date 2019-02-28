package com.liferon.countryapi.service.impl;

import com.liferon.countryapi.domain.Country;
import com.liferon.countryapi.model.CountryModel;
import com.liferon.countryapi.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    @Override
    public Optional<Country> getCountry(Long countryId) {
        return Optional.empty();
    }

    @Override
    public Country addCountry(CountryModel country) {
        return null;
    }

    @Override
    public boolean updateCountry(CountryModel country) {
        return false;
    }

    @Override
    public List<Country> getAllCountries() {
        return null;
    }

    @Override
    public boolean deleteCountry(Long countryId) {
        return false;
    }
}
