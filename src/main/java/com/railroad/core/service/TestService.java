package com.railroad.core.service;

import com.railroad.core.mapper.TestMapper;
import com.railroad.model.dao.TestDao;
import com.railroad.model.dto.TestDto;
import com.railroad.model.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    @Autowired
    TestDao testDao;

//    @Autowired
//    TestMapper testMapper;

    public List<TestDto> getAllTests() {
        List<Test> tests = testDao.getAllTests();
        List<TestDto> testDtos = new ArrayList<TestDto>();
        for (Test test :
                tests) {
            //testDtos.add(testMapper.testToTestDto(test));
            testDtos.add(new TestDto(test.getTestId(), test.getTestTitle()));
        }
        return testDtos;


    }
}
