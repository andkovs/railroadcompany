package com.railroad.core.mapper;

import com.railroad.config.AppConfig;
import com.railroad.model.dto.TrainDto;
import com.railroad.model.entity.Train;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class TrainMapperTest {

    @Mock
    private WagonMapper wagonMapper;

    @InjectMocks
    private TrainMapper trainMapper;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void trainListToTrainDtoList() throws Exception {
//        Train firstTrain = new Train(1L, "A100", true);
//        Train secondTrain = new Train(2L, "A101", true);
//        List<Train> trains = new ArrayList<>();
//        trains.add(firstTrain);
//        trains.add(secondTrain);
//        List<TrainDto> trainDtos = trainMapper.trainListToTrainDtoList(trains);
//        for (int i = 0; i < trains.size(); i++) {
//            assertEquals(trainDtos.get(i).getTrainId(), trains.get(i).getTrainId());
//            assertEquals(trainDtos.get(i).getTrainNumber(), trains.get(i).getTrainNumber());
//            assertEquals(trainDtos.get(i).getEnabled(), trains.get(i).getEnabled());
//
//        }
//    }

}