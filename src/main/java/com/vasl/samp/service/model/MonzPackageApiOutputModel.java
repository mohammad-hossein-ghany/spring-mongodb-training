package com.vasl.samp.service.model;

import com.vasl.samp.dal.entity.ApiMethod;
import lombok.Data;


@Data
public class MonzPackageApiOutputModel {
    private String id;
    private String connectorId;
    private String endpointId;
    private ApiMethod method;
}
