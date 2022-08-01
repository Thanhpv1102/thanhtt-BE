package com.blameo.employee.entity.model;

import com.blameo.employee.entity.GenderEnum;
import com.blameo.employee.entity.UserStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class User {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", length = 45, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "createdDate")
    private Instant createdDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 15)
    private UserStatusEnum status;

    @Column(name = "email", length = 45, nullable = false)
    private String email;

    @Column(name = "phoneNumber", length = 12)
    private String phoneNumber;

    @Column(name = "nation", length = 45)
    private String nation;

    @Column(name = "idCard", length = 45)
    private String idCard;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 5)
    private GenderEnum gender;

    @Column(name = "dateOfBirth")
    private Instant dateOfBirth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(name = "fullname", length = 100, nullable = false)
    private String fullname;

    @Column(name = "address", length = 100)
    private String address;
}