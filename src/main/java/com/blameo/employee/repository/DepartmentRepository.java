package com.blameo.employee.repository;

import com.blameo.employee.entity.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    Optional<Department> findDepartmentByName(String name);

}