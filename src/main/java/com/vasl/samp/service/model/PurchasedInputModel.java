package com.vasl.samp.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchasedInputModel {
    String packageId;
    String planId;
    String username;
    String userId;
}
