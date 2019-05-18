package com.bogdanmaier.thesis.service.country;

import com.bogdanmaier.thesis.dataaccess.country.Country;
import org.springframework.stereotype.Service;

@Service
public class CountryMapper {

    public Country toEntity (CountryDto countryDto) {
        Country country = new Country();
        country.setId(countryDto.getId());
        country.setName(countryDto.getName());
        country.setCode(countryDto.getCode());
        return country;
    }

    public CountryDto toService (Country country) {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(country.getId());
        countryDto.setName(country.getName());
        countryDto.setCode(country.getCode());
        return countryDto;
    }

}
