package com.vasl.samp.service.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MonzUserPackageOutputModel {
    private String packageId;
    private String planId;
    private String providerId;
    private String providerUsername;
    private String userId;
    private String username;
    private Long startUsageTime;
    private Integer countUsed;
    private Long expireTime;
    private Boolean active;
    private Integer durationDays;
    private Boolean expired;
    private String packageName;
    private String packageTitle;
    private String planName;
    private String planTitle;
    private String purchasedBy;
    private Boolean autoPurchasable;
    private Boolean expireNotified;
}
