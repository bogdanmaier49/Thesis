package com.bogdanmaier.thesis.service.country;

import com.bogdanmaier.thesis.dataaccess.country.Country;
import com.bogdanmaier.thesis.dataaccess.country.ICountryRepository;
import com.bogdanmaier.thesis.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private ICountryRepository countryRepository;

    @Autowired
    private CountryMapper countryMapper;

    public CountryDto create (CountryDto countryDto) throws ServiceException {

        if (countryRepository.findByCode(countryDto.getCode()) != null) {
            throw new ServiceException("Country with code " + countryDto.getCode() + " already exists");
        }

        CountryDto createdCountryDto = countryMapper.toService(countryRepository.save(countryMapper.toEntity(countryDto)));
        return createdCountryDto;
    }

    public CountryDto findById (Long id) {
        Optional<Country> optional = countryRepository.findById(id);

        if (optional.isPresent())
            return countryMapper.toService(optional.get());
        else return null;
    }

    public List<CountryDto> findAll () {
        List<Country> countries = countryRepository.findAll();
        List<CountryDto> returnValue = new ArrayList<>();
        for (Country country : countries) {
            returnValue.add(countryMapper.toService(country));
        }
        return returnValue;
    }

    public void delete (CountryDto countryDto) throws ServiceException{
        if (findById(countryDto.getId()) != null) {
            throw new ServiceException("Country with id " + countryDto.getId() + " does not exits");
        }

        countryRepository.delete(countryMapper.toEntity(countryDto));
    }

}
