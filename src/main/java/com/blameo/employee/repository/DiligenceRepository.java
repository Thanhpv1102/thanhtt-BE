package com.blameo.employee.repository;

import com.blameo.employee.entity.model.Diligence;
import com.blameo.employee.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface DiligenceRepository extends JpaRepository<Diligence, Integer> {

    List<Diligence> findAllByUser(User user);

    List<Diligence> findAllByTimeStartGreaterThanEqualAndTimeStopLessThanEqual(Instant timeStart, Instant timeStop);

    List<Diligence> findAllByUserAndTimeStartGreaterThanEqualAndTimeStopLessThanEqual(Instant timeStart, Instant timeStop, User user);
}
