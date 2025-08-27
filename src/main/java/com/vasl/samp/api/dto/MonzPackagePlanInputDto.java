package com.vasl.samp.api.dto;

import lombok.Data;

@Data
public class MonzPackagePlanInputDto {
    private String id;
    private String name;
    private String title;
    private String description;
    private Integer maxSpeed;
    private Integer maxCount;
    private Integer maxDailyCount;
    private String paymentType;
    private Integer durationDays;
    private String[] steps;
    private String packageStatus;
    private Long level;
}

