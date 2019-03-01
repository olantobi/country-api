package com.liferon.countryapi.service.impl;

import com.liferon.countryapi.domain.Country;
import com.liferon.countryapi.model.CountryModel;
import com.liferon.countryapi.repository.CountryRepository;
import com.liferon.countryapi.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public Optional<Country> getCountry(Long countryId) {
        return countryRepository.findById(countryId);
    }

    @Override
    public Country addCountry(CountryModel countryModel) {
        Country country = new Country(countryModel);

        return countryRepository.save(country);
    }

    @Override
    public boolean updateCountry(CountryModel countryModel) {

        Country country = new Country(countryModel);
        country.setId(countryModel.getId());

        Country updatedCountry = countryRepository.save(country);
        if (updatedCountry != null)
            return true;
        else
            return false;
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public void deleteCountry(Country country) {
        countryRepository.delete(country);
    }
}
