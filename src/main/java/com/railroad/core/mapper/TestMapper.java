package com.railroad.core.mapper;

import com.railroad.model.dto.TestDto;
import com.railroad.model.entity.Test;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface TestMapper {
    TestDto testToTestDto(Test test);
}
