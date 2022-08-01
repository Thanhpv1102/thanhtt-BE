package com.blameo.employee.entity;

import com.blameo.employee.entity.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "request")
public class Request {
    @Id
    @Column(name = "request_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "createDate")
    private Instant createDate;

    @Column(name = "timeStart")
    private Instant timeStart;

    @Column(name = "timeStop")
    private Instant timeStop;

    @Column(name = "reason", length = 100)
    private String reason;

    @Column(name = "status", length = 15)
    private String status;

    @Column(name = "handleDate")
    private Instant handleDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private Requesttype type;

}