package com.vasl.samp.service;

import com.vasl.samp.api.dto.ApiEndpointInputDto;
import com.vasl.samp.api.dto.ApiEndpointOutputDto;
import com.vasl.samp.service.model.ApiEndpointInputModel;

import java.util.List;

public interface ApiService {

    List<ApiEndpointOutputDto> insertEndpoint(String apiId, ApiEndpointInputModel apiEndpointInputModel);
}
