package com.bogdanmaier.thesis.service.country;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CountryDto {

    private Long id;
    private String name;
    private String code;

}
