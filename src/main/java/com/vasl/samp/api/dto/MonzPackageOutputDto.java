package com.vasl.samp.api.dto;

import com.vasl.samp.dal.entity.MonzPackageApi;
import com.vasl.samp.dal.entity.MonzPackagePlan;
import lombok.Data;

import java.util.List;


@Data
public class MonzPackageOutputDto {
    private String name;
    private String title;
    private String description;
    private String descriptionEn;
    private String termsAndConditions;
    private String termsAndConditionsEn;
    private List<MonzPackageApi> apis;
    private List<MonzPackagePlan> plans;
    private Long startDate;
    private Long expireDate;
    private Integer durationDays;
    private String userId;
    private String username;
    private Double latency;
    private Long imageSize;
    private String status;
    private Boolean active;
    private Long level;
    private Long packageSellingCount;
    private Long packageCallingSum;
    private Double packagePopularityPercent;
    private Double serviceLevelApiCallPercent;
    private Long failedApiCallCount;
    private Long successfulCallCount;
}