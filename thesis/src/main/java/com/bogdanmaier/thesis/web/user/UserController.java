package com.bogdanmaier.thesis.web.user;

import com.bogdanmaier.thesis.service.ServiceException;
import com.bogdanmaier.thesis.service.user.UserDetailsDto;
import com.bogdanmaier.thesis.service.user.UserDto;
import com.bogdanmaier.thesis.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    /**
     * Gets a user by its id.
     *
     * @param id
     *      Long - id
     * @return
     *      HttpStatus: OK - if a user was found.
     *      HttpStatus: NOT_FOUND - if no user was found.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<UserDto> findById (@PathVariable Long id) {
        UserDto user = userService.findById(id);
        ResponseEntity<UserDto> returnValue = null;

        if (user != null) {
            returnValue = new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            returnValue = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return returnValue;
    }


    @RequestMapping(value = "/loggedin", method = RequestMethod.GET)
    @ResponseBody
    private ResponseEntity<UserDto> getLoggedInUser () {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDto user = userService.finByEmail(principal.toString());
        ResponseEntity<UserDto> returnValue = null;

        if (user != null) {
            returnValue = new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            returnValue = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return returnValue;
    }


    /**
     * Registers a user in the application
     *
     *
     * @param user
     *      UserDto - The user that should be added.
     * @return
     *      HttpStatus: CREATED - if the user was created successfully,
     *      HttpStatus: CONFLICT - if email of the user already exits in the application
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    private ResponseEntity<UserDto> addTesUsers (@RequestBody UserDto user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        UserDto createdUser = null;

        try {
             createdUser = userService.create(user);
        } catch (ServiceException ex) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        if (createdUser != null)
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
