package com.blameo.employee.mapper;

import com.blameo.employee.dto.DiligenceDto;
import com.blameo.employee.entity.model.Diligence;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiligenceMapper {

    public List<DiligenceDto> mapDiligenceToDiligenceDto(List<Diligence> diligences) {

        return diligences.stream().map(this::mapDiligenceToDiligenceDto).collect(Collectors.toList());
    }

    public DiligenceDto mapDiligenceToDiligenceDto(Diligence diligence) {

        DiligenceDto diligenceDto = new DiligenceDto();
        diligenceDto.setId(diligence.getId());
        diligenceDto.setUserId(diligence.getUser().getId());
        diligenceDto.setTimeStart(diligence.getTimeStart());
        diligenceDto.setTimeStop(diligence.getTimeStop());
        diligenceDto.setTotalWorking(diligence.getTotalWorking());
        diligenceDto.setStatus(diligence.getStatus());

        return diligenceDto;
    }
}
