package com.railroad.core.service;

import com.railroad.core.mapper.WagonMapper;
import com.railroad.model.dao.WagonDao;
import com.railroad.model.dto.WagonAddDto;
import com.railroad.model.dto.WagonDto;
import com.railroad.model.dto.WagonTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WagonService {

    private final
    WagonDao wagonDao;

    private final
    WagonMapper wagonMapper;


    @Autowired
    public WagonService(WagonDao wagonDao, WagonMapper wagonMapper) {
        this.wagonDao = wagonDao;
        this.wagonMapper = wagonMapper;
    }

    List<WagonTypeDto> getAllWagonTypes() {
        return wagonMapper.wagonTypeListToWagonTypeDtoList(wagonDao.getAllWagonTypes());
    }

    public Long getTrainIdByWagonId(Long id) {
        return wagonDao.getTrainIdByWagonId(id);
    }

    public void removeWagonById(Long id) {
        wagonDao.removeWagonById(id);
    }

    public void saveWagon(WagonAddDto wagonAddDto) {
        wagonDao.saveWagon(wagonMapper.wagonAddDtoToWagon(wagonAddDto));
    }
}
