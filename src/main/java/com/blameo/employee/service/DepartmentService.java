package com.blameo.employee.service;

import com.blameo.employee.dto.DepartmentDtoRequest;
import com.blameo.employee.dto.DepartmentDtoRespone;
import com.blameo.employee.entity.model.Department;
import com.blameo.employee.exception.BlameoException;
import java.util.List;
public interface DepartmentService {
    DepartmentDtoRespone addDepartment(DepartmentDtoRequest departmentDtoRequest) throws BlameoException;

    DepartmentDtoRespone findDepartmentById(Integer department_id) throws BlameoException;

    List<DepartmentDtoRespone> findAllDepartment();

    boolean deleteDepartment(Integer department_id);

    DepartmentDtoRespone updateDepartment(Department department) throws BlameoException;
}
