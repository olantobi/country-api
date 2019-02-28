package com.liferon.countryapi.repository;

import com.liferon.countryapi.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
