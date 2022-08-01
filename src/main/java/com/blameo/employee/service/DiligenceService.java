package com.blameo.employee.service;

import com.blameo.employee.dto.DiligenceDto;
import com.blameo.employee.entity.model.Diligence;
import com.blameo.employee.entity.model.User;
import com.blameo.employee.exception.BlameoException;
import com.blameo.employee.mapper.DiligenceMapper;
import com.blameo.employee.repository.DiligenceRepository;
import com.blameo.employee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class DiligenceService {

    @Autowired
    private DiligenceRepository diligenceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private DiligenceMapper diligenceMapper;

    public List<DiligenceDto> getDiligences() {

        List<Diligence> diligences = diligenceRepository.findAll();
        return diligenceMapper.mapDiligenceToDiligenceDto(diligences);
    }

    public Map<Integer, List<DiligenceDto>> getAllDiligencesByUserIds(List<Integer> userIds) throws BlameoException {

        List<String> error = new ArrayList<>();
        userIds.forEach(userId -> {
            Optional<User> user = userRepository.findById(userId);
            if (!user.isPresent()) {
                String message = "User " + userId +" not found";
                error.add(message);
            }
        });

        if (!error.isEmpty()) {
            String message = String.join("\n", error);
            throw new BlameoException(message, HttpStatus.NOT_FOUND.value());
        }
        Map<Integer, List<DiligenceDto>> diligenceDtosGroupByUserId = new HashMap<>();
        userIds.forEach(userId -> {
            diligenceDtosGroupByUserId.put(userId, getAllDiligencesByUserId(userId));
        });

        return diligenceDtosGroupByUserId;
    }

    public List<DiligenceDto> getAllDiligencesByUserId(Integer userId) {

        Optional<User> user = userRepository.findById(userId);
        List<Diligence> diligences = diligenceRepository.findAllByUser(user.get());
        return diligenceMapper.mapDiligenceToDiligenceDto(diligences);
    }

    public List<DiligenceDto> getDiligencesInRange(Instant timeStart, Instant timeStop) {

        List<Diligence> diligences = diligenceRepository.findAllByTimeStartGreaterThanEqualAndTimeStopLessThanEqual(timeStart, timeStop);
        return diligenceMapper.mapDiligenceToDiligenceDto(diligences);
    }

    public Map<Integer, List<DiligenceDto>> getAllDiligencesByUserIdsInRange(Instant timeStart, Instant timeStop, List<Integer> userIds) throws BlameoException {

        List<String> error = new ArrayList<>();
        userIds.forEach(userId -> {
            Optional<User> user = userRepository.findById(userId);
            if (!user.isPresent()) {
                String message = "User " + userId +" not found";
                error.add(message);
            }
        });

        if (!error.isEmpty()) {
            String message = String.join("\n", error);
            throw new BlameoException(message, HttpStatus.NOT_FOUND.value());
        }

        Map<Integer, List<DiligenceDto>> diligenceDtosGroupByUserIdInRange = new HashMap<>();
        userIds.forEach(userId -> {
            diligenceDtosGroupByUserIdInRange.put(userId, getAllDiligencesByUserIdInRange(timeStart, timeStop, userId));
        });
        return diligenceDtosGroupByUserIdInRange;
    }

    public List<DiligenceDto> getAllDiligencesByUserIdInRange(Instant timeStart, Instant timeStop, Integer userId) {

        Optional<User> user = userRepository.findById(userId);
        List<Diligence> diligences = diligenceRepository.findAllByUserAndTimeStartGreaterThanEqualAndTimeStopLessThanEqual(timeStart, timeStop, user.get());
        return diligenceMapper.mapDiligenceToDiligenceDto(diligences);
    }
}
