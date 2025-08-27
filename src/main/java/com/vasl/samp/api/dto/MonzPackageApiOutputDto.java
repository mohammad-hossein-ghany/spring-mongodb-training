package com.vasl.samp.api.dto;

import com.vasl.samp.dal.entity.ApiMethod;
import lombok.Data;

@Data
public class MonzPackageApiOutputDto {
    private String id;
    private String connectorId;
    private String endpointId;
    private ApiMethod method;
}
