package com.vasl.samp.service.model;

import com.vasl.samp.dal.entity.ApiMethod;
import lombok.Data;
import org.bson.types.ObjectId;


@Data
public class MonzPackageApiInputModel {
    private String id;
    private ObjectId connectorId;
    private String endpointId;
    private ApiMethod method;
}
