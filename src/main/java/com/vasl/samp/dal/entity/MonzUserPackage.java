package com.vasl.samp.dal.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "monzUserPackage")
public class MonzUserPackage extends BaseEntity {
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
