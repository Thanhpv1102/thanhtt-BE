package com.blameo.employee.service.Impl;

import com.blameo.employee.dto.DepartmentDtoRequest;
import com.blameo.employee.dto.DepartmentDtoRespone;
import com.blameo.employee.entity.model.Department;
import com.blameo.employee.exception.BlameoException;
import com.blameo.employee.repository.DepartmentRepository;
import com.blameo.employee.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper mapper;
    @Override
    public DepartmentDtoRespone addDepartment(DepartmentDtoRequest departmentDtoRequest) throws BlameoException {
        Optional<Department> department = departmentRepository.findDepartmentByName(departmentDtoRequest.getName());
        if(department.isPresent()) {
            String message = "Department is present";
            throw new BlameoException(message, HttpStatus.NOT_FOUND.value());
        }
        Department newDepartment = new Department();
        newDepartment.setName(departmentDtoRequest.getName());
        return mapper.map(departmentRepository.save(newDepartment), DepartmentDtoRespone.class);
    }

    @Override
    public DepartmentDtoRespone findDepartmentById(Integer department_id) throws BlameoException{
        Optional<Department> department = departmentRepository.findById(department_id);
        if(!department.isPresent()) {
            String message = "Department not found";
            throw new BlameoException(message, HttpStatus.NOT_FOUND.value());
        }
        return  mapper.map(department.get(),DepartmentDtoRespone.class) ;
    }

    @Override
    public List<DepartmentDtoRespone> findAllDepartment() {
        return departmentRepository.findAll().stream().map(i -> mapper.map(i, DepartmentDtoRespone.class)).collect(Collectors.toList());
    }

    @Override
    public boolean deleteDepartment(Integer department_id) {
        departmentRepository.deleteById(department_id);
        return true;
    }

    @Override
    public DepartmentDtoRespone updateDepartment(Department department) throws BlameoException {
        Optional<Department> oldDepartment = departmentRepository.findById(department.getId());
        if(!oldDepartment.isPresent()) {
            String message = "Department not found";
            throw new BlameoException(message, HttpStatus.NOT_FOUND.value());
        }
        oldDepartment.get().setName(department.getName());
        return  mapper.map(departmentRepository.save(oldDepartment.get()),DepartmentDtoRespone.class);
    }
}
