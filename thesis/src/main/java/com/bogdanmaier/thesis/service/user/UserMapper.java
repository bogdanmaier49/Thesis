package com.bogdanmaier.thesis.service.user;

import com.bogdanmaier.thesis.dataaccess.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    @Autowired
    private UserDetailsMapper userDetailsMapper;

    public User toEntity (UserDto userDto) {
        User user = new User();

        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setDateAdded(userDto.getAddedDate());
        user.setUserDetails(userDetailsMapper.toEntity(userDto.getUserDetails()));

        return user;
    }

    public UserDto toService (User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAddedDate(user.getDateAdded());
        userDto.setUserDetails(userDetailsMapper.toService(user.getUserDetails()));

        return userDto;
    }

}
