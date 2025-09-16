package com.vasl.samp.dal.entity;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class MonzPackageApi {
    private String id;
    private ObjectId connectorId;
    private String endpointId;
    private ApiMethod method;
}