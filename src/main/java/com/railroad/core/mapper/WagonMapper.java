package com.railroad.core.mapper;

import com.railroad.model.dto.WagonAddDto;
import com.railroad.model.dto.WagonDto;
import com.railroad.model.dto.WagonTypeDto;
import com.railroad.model.entity.Wagon;
import com.railroad.model.entity.WagonType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WagonMapper {

    /**
     * Converts list of wagon type's to  list of wagon type's Dto.
     *
     * @param allWagons Convertible list of wagon type's.
     * @return list of wagon type dto's.
     */
    public List<WagonTypeDto> wagonTypeListToWagonTypeDtoList(List<WagonType> allWagons) {
        return allWagons.stream().map(wagon -> new WagonTypeDto(
                wagon.getWagonTypeId(),
                wagon.getWagonType(),
                wagon.getCapacity())).collect(Collectors.toList());
    }

    /**
     * Converts wagonAdd Dto to Wagon.
     *
     * @param wagonAddDto Convertible wagonAdd dto.
     * @return wagon.
     */
    public Wagon wagonAddDtoToWagon(WagonAddDto wagonAddDto) {
        return new Wagon(wagonAddDto.getTrainId(), wagonAddDto.getWagonTypeId(), wagonAddDto.getWagonTitle());
    }

    /**
     * Converts Wagon to Wagon DTO.
     *
     * @param wagon Convertible wagon.
     * @return wagon dto.
     */
    WagonDto wagonToWagonDto(Wagon wagon) {
        return new WagonDto(
                wagon.getWagonId(),
                wagon.getTrainId(),
                wagon.getWagonTypeByWagonTypeId().getWagonType(),
                wagon.getWagonTitle(),
                wagon.getWagonTypeByWagonTypeId()
        );
    }
}
