package com.bogdanmaier.thesis.service.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private Date addedDate;
    private UserDetailsDto userDetails;

}
