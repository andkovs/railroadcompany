package com.railroad.model.dto;

public class TestDto {

    private Long testId;
    private String testTitle;

    public TestDto(Long testId, String testTitle) {
        this.testId = testId;
        this.testTitle = testTitle;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }
}
