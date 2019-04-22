package com.bogdanmaier.thesis.service.user;

import com.bogdanmaier.thesis.dataaccess.user.IUserRepository;
import com.bogdanmaier.thesis.dataaccess.user.User;
import com.bogdanmaier.thesis.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<UserDto> findAll () {
        List<User> users = userRepository.findAll();
        List<UserDto> returnValue = new ArrayList<>();

        for (User user : users) {
            returnValue.add(userMapper.toService(user));
        }

        return returnValue;
    }

    public UserDto findById (Long id) {
        Optional<User> optional = userRepository.findById(id);
        User user = optional.get();
        return userMapper.toService(user);
    }

    public UserDto finByEmail (String email) {
        User user = userRepository.findByEmail(email);
        return userMapper.toService(user);
    }

    public UserDto create (UserDto userDto) throws ServiceException {

        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new ServiceException("User with email " + userDto.getEmail() + " already exists");
        }

        userDto.setAddedDate(new Date(System.currentTimeMillis()));
        UserDto returnValue = userMapper.toService(userRepository.save(userMapper.toEntity(userDto)));
        return  returnValue;
    }

}
