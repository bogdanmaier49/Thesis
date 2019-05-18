package com.bogdanmaier.thesis.service.producer;

import com.bogdanmaier.thesis.service.country.CountryDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProducerDto {

    private Long id;
    private String name;
    private CountryDto country;

}
