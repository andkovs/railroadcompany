package com.railroad.core.mapper;

import com.railroad.model.dto.ScheduleDto;
import com.railroad.model.dto.TrainDto;
import com.railroad.model.dto.WagonDto;
import com.railroad.model.entity.Schedule;
import com.railroad.model.entity.Train;
import com.railroad.model.entity.Wagon;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrainMapper {

    /**
     * Converts list of trains to list of train DTO's.
     *
     * @param trains list od trains.
     * @return list of train Dto's.
     */
    public List<TrainDto> trainListToTrainDtoList(List<Train> trains) {
        return trains.stream().map(this::trainToTrainDto).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Converts train to trainDto.
     *
     * @param train convertible train.
     * @return train Dto.
     */
    public TrainDto trainToTrainDto(Train train) {
        TrainDto trainDto = new TrainDto(
                train.getTrainId(),
                train.getTrainNumber(),
                train.getEnabled());
        ArrayList<Wagon> wagons = new ArrayList(train.getWagonsByTrainId());
        for (Wagon wagon : wagons) {
            trainDto.getWagons().add(new WagonDto(
                    wagon.getWagonId(),
                    wagon.getTrainId(),
                    wagon.getWagonTypeByWagonTypeId().getWagonType(),
                    wagon.getWagonTitle()
            ));
        }
        ArrayList<Schedule> schedules = new ArrayList(train.getSchedulesByTrainId());
        for (Schedule schedule : schedules) {
            trainDto.getSchedules().add(new ScheduleDto(
                    schedule.getScheduleId(),
                    schedule.getDirectionId(),
                    schedule.getTrainId(),
                    schedule.getDepartureTime(),
                    schedule.getArriveTime(),
                    schedule.getDirectionByDirectionId()
            ));
        }
        return trainDto;
    }

    /**
     * Converts train DTO to train.
     *
     * @param trainDto convertible train DTO.
     * @return train.
     */
    public Train trainDtoToTrain(TrainDto trainDto) {
        return new Train(
                trainDto.getTrainId(),
                trainDto.getTrainNumber(),
                trainDto.getEnabled()
        );
    }
}
