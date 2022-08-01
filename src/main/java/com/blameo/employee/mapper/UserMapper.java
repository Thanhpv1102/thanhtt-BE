package com.blameo.employee.mapper;

import com.blameo.employee.dto.UserDto;
import com.blameo.employee.entity.model.Department;
import com.blameo.employee.entity.model.User;
import com.blameo.employee.exception.BlameoException;
import com.blameo.employee.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<UserDto> mapUsersToUserDtos(List<User> users) {

        return users.stream().map(this::mapUserToUserDto).collect(Collectors.toList());
    }

    public UserDto mapUserToUserDto(User user) {

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setFullname(user.getFullname());
        userDto.setCreatedDate(user.getCreatedDate());
        userDto.setStatus(user.getStatus());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setNation(user.getNation());
        userDto.setIdCard(user.getIdCard());
        userDto.setGender(user.getGender());
        userDto.setDateOfBirth(user.getDateOfBirth());
        userDto.setAddress(user.getAddress());
        userDto.setDepartmentId(user.getDepartment().getId());

        return userDto;
    }

    public User mapUserDtoToNewUser(UserDto userDto) throws BlameoException {

        User newUser = new User();

        newUser.setUsername(userDto.getUsername());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setCreatedDate(Instant.now());
        newUser.setStatus(userDto.getStatus());
        newUser.setPhoneNumber(userDto.getPhoneNumber());
        newUser.setNation(userDto.getNation());
        newUser.setIdCard(userDto.getIdCard());
        newUser.setGender(userDto.getGender());
        newUser.setDateOfBirth(userDto.getDateOfBirth());
        newUser.setAddress(userDto.getAddress());
        newUser.setFullname(userDto.getFullname());

        Optional<Department> departmentOpt = departmentRepository.findById(userDto.getDepartmentId());
        newUser.setDepartment(departmentOpt.get());

        return newUser;
    }
}
