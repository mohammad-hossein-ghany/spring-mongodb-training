package com.vasl.samp.api.dto;

import com.vasl.samp.dal.entity.ApiMethod;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class MonzPackageApiInputDto {
    private String id;
    private ObjectId connectorId;
    private String endpointId;
    private ApiMethod method;
}
