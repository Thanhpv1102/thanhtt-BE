package com.blameo.employee.repository;

import com.blameo.employee.entity.model.Department;
import com.blameo.employee.entity.UserStatusEnum;
import com.blameo.employee.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findOneByUsername(String username);

    List<User> findAllByStatusNot(UserStatusEnum userStatusEnum);

    List<User> findAllByStatus(UserStatusEnum userStatusEnum);

    List<User> findAllByDepartmentAndStatusNot(Department department, UserStatusEnum userStatusEnum);

    List<User> findAllByDepartment(Department department);
}
