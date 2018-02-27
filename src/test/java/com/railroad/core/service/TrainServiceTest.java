package com.railroad.core.service;

import com.railroad.config.AppConfig;
import com.railroad.core.mapper.TrainMapper;
import com.railroad.model.dao.TrainDao;
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

import java.util.Arrays;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class TrainServiceTest {

    @Mock
    private TrainDao mockTrainDao;

    @Mock
    private TrainMapper mockTrainMapper;

    @InjectMocks
    private TrainService trainService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllTrains() throws Exception {
        Train firstTrain = new Train(1L, "A100", true);
        Train secondTrain = new Train(2L, "A101", true);

        TrainDto firstTrainDto = new TrainDto(1L, "A100", true);
        TrainDto secondTrainDto = new TrainDto(2L, "A101", true);

        when(mockTrainDao.getAllTrains()).thenReturn(Arrays.asList(firstTrain, secondTrain));
        when(mockTrainMapper.trainListToTrainDtoList(Arrays.asList(firstTrain, secondTrain)))
                .thenReturn(Arrays.asList(firstTrainDto, secondTrainDto));
        trainService.getAllTrains();
        verify(mockTrainDao, times(1)).getAllTrains();
        verify(mockTrainMapper, times(1)).trainListToTrainDtoList(Arrays.asList(firstTrain, secondTrain));
    }

}