package com.bogdanmaier.thesis.service.user;

import com.bogdanmaier.thesis.dataaccess.user.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsMapper {

    public UserDetails toEntity (UserDetailsDto userDetailsDto) {
        UserDetails userDetails = new UserDetails();

        userDetails.setId(userDetailsDto.getId());
        userDetails.setFirstName(userDetailsDto.getFirstName());
        userDetails.setLastName(userDetailsDto.getLastName());
        userDetails.setPhone(userDetailsDto.getPhone());

        return userDetails;
    }

    public UserDetailsDto toService (UserDetails userDetails) {
        UserDetailsDto userDetailsDto = new UserDetailsDto();

        userDetailsDto.setId(userDetails.getId());
        userDetailsDto.setFirstName(userDetails.getFirstName());
        userDetailsDto.setLastName(userDetails.getLastName());
        userDetailsDto.setPhone(userDetails.getPhone());

        return userDetailsDto;
    }

}
