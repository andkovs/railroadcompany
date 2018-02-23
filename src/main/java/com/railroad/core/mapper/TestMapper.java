package com.railroad.core.mapper;

import com.railroad.model.dto.TestDto;
import com.railroad.model.entity.Test;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestMapper {
    TestDto testToTestDto(Test test);
}
