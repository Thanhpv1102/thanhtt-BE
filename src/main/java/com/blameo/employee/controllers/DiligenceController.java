package com.blameo.employee.controllers;

import com.blameo.employee.config.security.JwtCustomUserDetail;
import com.blameo.employee.dto.DiligenceDto;
import com.blameo.employee.exception.BlameoException;
import com.blameo.employee.service.DiligenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("/diligence")
public class DiligenceController {

    @Autowired
    private DiligenceService diligenceService;

    @RequestMapping(method = RequestMethod.GET, value = "/all", produces = "application/json")
    public ResponseEntity getDiligences() {
        List<DiligenceDto> diligenceDtos = diligenceService.getDiligences();
        return new ResponseEntity<>(diligenceDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getDiligencesInRange(
            @RequestParam(value = "personIds") Instant timeStart,
            @RequestParam(value = "timeStop") Instant timeStop){
        List<DiligenceDto> diligenceDtos = diligenceService.getDiligencesInRange(timeStart, timeStop);
        return new ResponseEntity<>(diligenceDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{ids}/all", produces = "application/json")
    public ResponseEntity getAllDiligencesByUserIds(@PathVariable List<Integer> userIds) throws BlameoException {
        Map<Integer, List<DiligenceDto>> diligenceDtos = diligenceService.getAllDiligencesByUserIds(userIds);
        return new ResponseEntity<>(diligenceDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{ids}", produces = "application/json")
    public ResponseEntity getAllDiligencesByUserIdsInRange(
            @PathVariable List<Integer> userIds,
            @RequestParam(value = "personIds") Instant timeStart,
            @RequestParam(value = "timeStop") Instant timeStop) throws BlameoException {
        Map<Integer, List<DiligenceDto>> diligenceDtos = diligenceService.getAllDiligencesByUserIdsInRange(timeStart, timeStop, userIds);
        return new ResponseEntity<>(diligenceDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/currentUser/all", produces = "application/json")
    public ResponseEntity getAllDiligencesByUserId(@AuthenticationPrincipal JwtCustomUserDetail currentUser) {
        Integer userId = currentUser.getUserId();
        List<DiligenceDto> diligenceDtos = diligenceService.getAllDiligencesByUserId(userId);
        return new ResponseEntity<>(diligenceDtos, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/currentUser", produces = "application/json")
    public ResponseEntity getAllDiligencesByUserIdInRange(
            @AuthenticationPrincipal JwtCustomUserDetail currentUser,
            @RequestParam(value = "personIds") Instant timeStart,
            @RequestParam(value = "timeStop") Instant timeStop) {
        Integer userId = currentUser.getUserId();
        List<DiligenceDto> diligenceDtos = diligenceService.getAllDiligencesByUserIdInRange(timeStart, timeStop, userId);
        return new ResponseEntity<>(diligenceDtos, HttpStatus.OK);
    }
}
