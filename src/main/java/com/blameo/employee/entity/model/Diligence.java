package com.blameo.employee.entity.model;

import com.blameo.employee.entity.model.User;
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
@Table(name = "diligence")
public class Diligence {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diligence_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "timeStart")
    private Instant timeStart;

    @Column(name = "timeStop")
    private Instant timeStop;

    @Column(name = "totalWorking")
    private Double totalWorking;

    @Column(name = "status", length = 45)
    private String status;

}