package com.vasl.samp.service.model;


import lombok.Data;

@Data
public class MonzPackagePlanOutputModel {
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
