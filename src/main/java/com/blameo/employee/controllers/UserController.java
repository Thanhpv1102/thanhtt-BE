package com.blameo.employee.controllers;

import com.blameo.employee.config.security.JwtCustomUserDetail;
import com.blameo.employee.dto.PasswordChangeDto;
import com.blameo.employee.dto.UserDto;
import com.blameo.employee.exception.BlameoException;
import com.blameo.employee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getUsers() {
        List<UserDto> userDtos = userService.getUsers();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/withoutDeleted", produces = "application/json")
    public ResponseEntity getUsersWithoutDeleted() {
        List<UserDto> userDtos = userService.getUsersWithoutDeleted();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/active", produces = "application/json")
    public ResponseEntity getUsersActive() {
        List<UserDto> userDtos = userService.getUsersActive();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @RequestMapping(path = "/department/{departmentId}/withoutDeleted", method = RequestMethod.GET)
    public ResponseEntity getUsersByDepartmentWithoutDeleted(@PathVariable("departmentId") Integer departmentId) throws BlameoException {
        List<UserDto> userDtos = userService.getUsersByDepartmentWithoutDeleted(departmentId);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @RequestMapping(path = "/department/{departmentId}", method = RequestMethod.GET)
    public ResponseEntity getUsersByDepartment(@PathVariable("departmentId") Integer departmentId) throws BlameoException {
        List<UserDto> userDtos = userService.getUsersByDepartment(departmentId);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable Integer id) throws BlameoException {
        UserDto userDto = userService.getUserNotDeletedById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/current")
    public ResponseEntity getCurrentUser(@ApiIgnore @AuthenticationPrincipal JwtCustomUserDetail currentUser) throws BlameoException {
        UserDto userDto = userService.getUserActiveById(currentUser.getUserId());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity createUser(@RequestBody UserDto userDto) throws BlameoException {
        userService.createUser(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/{id}")
    public ResponseEntity updateUser(@RequestBody UserDto userDto,
                                     @PathVariable Integer id) throws BlameoException {
        userService.updateUser(id, userDto);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity deleteUser(@RequestBody List<Integer> userIds, @ApiIgnore @AuthenticationPrincipal JwtCustomUserDetail currentUser) throws BlameoException {

        userService.deleteUser(userIds, currentUser.getUserId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "/change-password")
    public ResponseEntity changePassword(@RequestBody PasswordChangeDto passwordChangeDto, @ApiIgnore @AuthenticationPrincipal JwtCustomUserDetail currentUser) throws BlameoException {

        userService.changePassword(currentUser.getUserId(), passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}