package com.vasl.samp.dal.entity;

import lombok.Data;

@Data
public class MonzPackageApi {
    private String id;
    private String connectorId;
    private String endpointId;
    private ApiMethod method;
}