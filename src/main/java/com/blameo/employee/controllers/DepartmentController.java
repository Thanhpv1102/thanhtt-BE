package com.blameo.employee.controllers;

import com.blameo.employee.dto.DepartmentDtoRequest;
import com.blameo.employee.entity.model.Department;
import com.blameo.employee.exception.BlameoException;
import com.blameo.employee.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    @PostMapping("/add-department")
    public ResponseEntity<?> addDepartment(@RequestBody DepartmentDtoRequest departmentDtoRequest) throws BlameoException {
        return ResponseEntity.ok(departmentService.addDepartment(departmentDtoRequest));
    }
    @GetMapping ("/find-department-by-id/{department_id}")
    public ResponseEntity<?> findDepartmentById(@PathVariable("department_id") Integer department_id) throws BlameoException {
        return ResponseEntity.ok(departmentService.findDepartmentById(department_id));
    }
    @GetMapping("/find-all-department")
    public ResponseEntity<?> findAllDepartment(){
        return ResponseEntity.ok(departmentService.findAllDepartment());
    }
    @DeleteMapping("/delete-department/{department_id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("department_id") Integer department_id) throws BlameoException{
        return ResponseEntity.ok(departmentService.deleteDepartment(department_id));
    }
    @PutMapping("/update-department")
    public ResponseEntity<?> updateDepartment(@RequestBody Department department) throws BlameoException {
        return ResponseEntity.ok(departmentService.updateDepartment(department));
    }

}
