package com.bogdanmaier.thesis.web.country;

import com.bogdanmaier.thesis.service.ServiceException;
import com.bogdanmaier.thesis.service.country.CountryDto;
import com.bogdanmaier.thesis.service.country.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CountryDto> create (@RequestBody CountryDto countryDto) {
        ResponseEntity<CountryDto> returnValue = null;

        try {
            CountryDto createdCountry = countryService.create(countryDto);
            if (createdCountry != null) {
                returnValue = new ResponseEntity<>(createdCountry, HttpStatus.CREATED);
            }
        } catch (ServiceException e) {
            System.out.println("Country could not be created: " + e.getMessage());
            returnValue = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return returnValue;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CountryDto>> findAll () {
        ResponseEntity<List<CountryDto>> returnValue = null;

        List<CountryDto> countries = countryService.findAll();
        returnValue = new ResponseEntity<>(countries, HttpStatus.OK);

        return returnValue;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<CountryDto> delete (@RequestBody  CountryDto countryDto) {
        ResponseEntity returnValue = null;

        try {
            countryService.delete(countryDto);
            returnValue = new ResponseEntity<>(countryDto, HttpStatus.OK);
        } catch (ServiceException e) {
            System.out.println("Country could not be deleted: " + e.getMessage());
            returnValue = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return returnValue;
    }

}
