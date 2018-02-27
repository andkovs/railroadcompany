package com.railroad.core.mapper;

import com.railroad.model.dto.WagonAddDto;
import com.railroad.model.dto.WagonTypeDto;
import com.railroad.model.entity.Wagon;
import com.railroad.model.entity.WagonType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WagonMapper {

    public List<WagonTypeDto> wagonTypeListToWagonTypeDtoList(List<WagonType> allWagons) {
        return allWagons.stream().map(wagon -> new WagonTypeDto(
                wagon.getWagonTypeId(),
                wagon.getWagonType(),
                wagon.getCapacity())).collect(Collectors.toList());
    }

    public Wagon wagonAddDtoToWagon(WagonAddDto wagonAddDto) {
        return new Wagon(wagonAddDto.getTrainId(), wagonAddDto.getWagonTypeId(), wagonAddDto.getWagonTitle());
    }
}
