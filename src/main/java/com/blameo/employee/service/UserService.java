package com.blameo.employee.service;

import com.blameo.employee.dto.UserDto;
import com.blameo.employee.entity.model.Department;
import com.blameo.employee.entity.UserStatusEnum;
import com.blameo.employee.entity.model.User;
import com.blameo.employee.exception.BlameoException;
import com.blameo.employee.mapper.UserMapper;
import com.blameo.employee.repository.DepartmentRepository;
import com.blameo.employee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<UserDto> getUsersWithoutDeleted() {

        List<User> users = userRepository.findAllByStatusNot(UserStatusEnum.DELETED);
        return userMapper.mapUsersToUserDtos(users);
    }

    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.mapUsersToUserDtos(users);
    }

    public List<UserDto> getUsersActive() {
        List<User> users = userRepository.findAllByStatus(UserStatusEnum.ACTIVE);
        return userMapper.mapUsersToUserDtos(users);
    }

    public List<UserDto> getUsersByDepartmentWithoutDeleted(int departmentId) throws BlameoException {

        Optional<Department> departmentOpt = departmentRepository.findById(departmentId);

        if (!departmentOpt.isPresent()) {
            String message = "Department not found";
            throw new BlameoException(message, HttpStatus.NOT_FOUND.value());
        }

        List<User> users = userRepository.findAllByDepartmentAndStatusNot(departmentOpt.get(), UserStatusEnum.DELETED);
        return userMapper.mapUsersToUserDtos(users);
    }

    public List<UserDto> getUsersByDepartment(int departmentId) throws BlameoException {

        Optional<Department> departmentOpt = departmentRepository.findById(departmentId);

        if (!departmentOpt.isPresent()) {
            String message = "Department not found";
            throw new BlameoException(message, HttpStatus.NOT_FOUND.value());
        }

        List<User> users = userRepository.findAllByDepartment(departmentOpt.get());
        return userMapper.mapUsersToUserDtos(users);
    }

    public UserDto getUserActiveById(int id) throws BlameoException {

        Optional<User> userOpt = userRepository.findById(id);
        if (!userOpt.isPresent() || !userOpt.get().getStatus().equals(UserStatusEnum.ACTIVE)) {
            String message = "User not found";
            throw new BlameoException(message, HttpStatus.NOT_FOUND.value());
        }
        return userMapper.mapUserToUserDto(userOpt.get());
    }

    public UserDto getUserNotDeletedById(int id) throws BlameoException {

        Optional<User> userOpt = userRepository.findById(id);
        if (!userOpt.isPresent() || userOpt.get().getStatus().equals(UserStatusEnum.DELETED)) {
            String message = "User not found";
            throw new BlameoException(message, HttpStatus.NOT_FOUND.value());
        }
        return userMapper.mapUserToUserDto(userOpt.get());
    }

    public void createUser(UserDto userDto) throws BlameoException {

        userDto.setUsername(userDto.getEmail());
        validateNewUser(userDto);
        User newUser = userMapper.mapUserDtoToNewUser(userDto);
        userRepository.save(newUser);
    }

    public void updateUser(int userId, UserDto userDto) throws BlameoException {

        validateUser(userId, userDto);

        Optional<User> userOpt = userRepository.findById(userId);
        if (!userOpt.isPresent() || userOpt.get().getStatus().equals(UserStatusEnum.DELETED)) {
            String message = "User not found";
            throw new BlameoException(message, HttpStatus.NOT_FOUND.value());
        }

        updateUserByUserDto(userOpt.get(), userDto);
    }

    private void updateUserByUserDto(User user, UserDto userDto) {

        user.setStatus(userDto.getStatus());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setNation(userDto.getNation());
        user.setIdCard(userDto.getIdCard());
        user.setGender(userDto.getGender());
        user.setDateOfBirth(userDto.getDateOfBirth());

        Optional<Department> departmentOpt = departmentRepository.findById(userDto.getDepartmentId());
        user.setDepartment(departmentOpt.get());

        user.setFullname(userDto.getFullname());
        user.setAddress(userDto.getAddress());

        userRepository.save(user);
    }

    public void deleteUser(List<Integer> useIds, int currentId) throws BlameoException {

        if (useIds.contains(currentId)) {
            String message = "Can't delete your self";
            throw new BlameoException(message, HttpStatus.BAD_REQUEST.value());
        }

        List<User> users = userRepository.findAllById(useIds);
        users.forEach(user -> {
            user.setStatus(UserStatusEnum.DELETED);
        });
        userRepository.saveAll(users);
    }

    public void changePassword(int userId, String currentClearTextPassword, String newPassword) throws BlameoException {

        Optional<User> userOtp = userRepository.findById(userId);
        if (!userOtp.isPresent()) {
            String message = "User not found";
            throw new BlameoException(message, HttpStatus.NOT_FOUND.value());
        } else {
            User user = userOtp.get();
            String currentEncryptedPassword = user.getPassword();
            if (!passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
                String message = "Password not match!";
                throw new BlameoException(message, HttpStatus.CONFLICT.value());
            }
            String encryptedPassword = passwordEncoder.encode(newPassword);
            user.setPassword(encryptedPassword);
            userRepository.save(user);
        }
    }

    private void validateNewUser(UserDto userDto) throws BlameoException {

        if (userDto.getEmail() == null || userDto.getEmail().isEmpty()) {
            String message = "Email is required";
            throw new BlameoException(message, HttpStatus.CONFLICT.value());
        }

        Optional<User> userOpt = userRepository.findOneByUsername(userDto.getUsername());
        if (userOpt.isPresent()) {
            String message = "Username already taken!";
            throw new BlameoException(message, HttpStatus.CONFLICT.value());
        }

        if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            String message = "Password is required";
            throw new BlameoException(message, HttpStatus.CONFLICT.value());
        }

        if (userDto.getFullname() == null || userDto.getFullname().isEmpty()) {
            String message = "Fullname is required";
            throw new BlameoException(message, HttpStatus.CONFLICT.value());
        }

        Optional<Department> departmentOpt = departmentRepository.findById(userDto.getDepartmentId());
        if (!departmentOpt.isPresent()) {
            String message = "Department not found";
            throw new BlameoException(message, HttpStatus.NOT_FOUND.value());
        }
    }

    private void validateUser(int id, UserDto userDto) throws BlameoException {

        if (!userDto.getId().equals(id)) {
            String message = "Invalid id";
            throw new BlameoException(message, HttpStatus.CONFLICT.value());
        }

        if (userDto.getEmail() == null || userDto.getEmail().isEmpty()) {
            String message = "Email is required";
            throw new BlameoException(message, HttpStatus.CONFLICT.value());
        }

        if (userDto.getFullname() == null || userDto.getFullname().isEmpty()) {
            String message = "Fullname is required";
            throw new BlameoException(message, HttpStatus.CONFLICT.value());
        }

        Optional<Department> departmentOpt = departmentRepository.findById(userDto.getDepartmentId());
        if (!departmentOpt.isPresent()) {
            String message = "Department not found";
            throw new BlameoException(message, HttpStatus.NOT_FOUND.value());
        }
    }
}