package com.bogdanmaier.thesis.dataaccess.country;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICountryRepository extends JpaRepository<Country, Long> {

    Country findByCode (String code);

    Country findByName (String name);

}
