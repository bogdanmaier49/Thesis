package com.bogdanmaier.thesis.service.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDetailsDto {

    private Long id;

    private String firstName;
    private String lastName;

    private String phone;

}
