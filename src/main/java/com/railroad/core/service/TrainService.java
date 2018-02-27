package com.railroad.core.service;

import com.railroad.core.mapper.TrainMapper;
import com.railroad.model.dao.TrainDao;
import com.railroad.model.dto.*;
import com.railroad.model.entity.Schedule;
import com.railroad.model.entity.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class TrainService {

    private final
    TrainDao trainDao;

    private final
    TrainMapper trainMapper;

    private final
    WagonService wagonService;

    @Autowired
    public TrainService(TrainDao trainDao, TrainMapper trainMapper, WagonService wagonService) {
        this.trainDao = trainDao;
        this.trainMapper = trainMapper;
        this.wagonService = wagonService;
    }

    /**
     * Gets list of all trains.
     *
     * @return list of all trains DTO's.
     */
    public List<TrainDto> getAllTrains() {
        return trainMapper.trainListToTrainDtoList(trainDao.getAllTrains());
    }

    /**
     * Gets train DTO by train id.
     *
     * @param id train id.
     * @return train DTO.
     */
    public TrainDto getTrainById(Long id) {
        Train train = trainDao.getTrainById(id);
        //if train not exist, then return empty trainDto
        if (train == null) {
            return new TrainDto(0L, "", true);
        }
        TrainDto trainDto = trainMapper.trainToTrainDto(train);
        //sort schedules
        Collections.sort(trainDto.getSchedules(),
                (o1, o2) -> o1.getScheduleId().compareTo(o2.getScheduleId()));
        return trainDto;
    }

    /**
     * Gets list of all wagon types.
     *
     * @return list of all wagon type's DTO's.
     */
    public List<WagonTypeDto> getAllWagonTypes() {
        return wagonService.getAllWagonTypes();
    }

    /**
     * Saves or updates train. If train doe's not exist,
     * then save, else update.
     *
     * @param trainDto saved/updated train DTO.
     * @return new or current train id.
     */
    public Long saveOrUpdateTrain(TrainDto trainDto) {
        if (trainDto.getTrainId() == null || trainDao.getTrainById(trainDto.getTrainId()) == null) {
            //save train and get new id
            return trainDao.saveTrain(trainMapper.trainDtoToTrain(trainDto));
        } else {
            //update train
            trainDao.updateTrain(trainMapper.trainDtoToTrain(trainDto));
            return trainDto.getTrainId();
        }
    }
}
