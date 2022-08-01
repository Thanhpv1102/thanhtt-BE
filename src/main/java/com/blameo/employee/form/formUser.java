package com.blameo.employee.form;

import com.blameo.employee.entity.model.Department;
import com.blameo.employee.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class formUser {
    private Integer id;
    private String email;
    private String phoneNumber;
    private String nation;
    private String idCard;
    private String gender;
    private Instant dateOfBirth;
    private Department department;
    private String fullname;
    private String adress;
    private Role role;
}
