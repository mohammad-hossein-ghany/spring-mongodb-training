package com.vasl.samp.api.dto;

import lombok.Data;

@Data
public class PurchasedInputDto {
    String packageId;
    String planId;
    String username;
    String userId;
}
