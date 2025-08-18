package com.vasl.samp.service;

import com.vasl.samp.api.dto.ApiEndpointInputDto;
import com.vasl.samp.api.dto.ApiEndpointOutputDto;
import com.vasl.samp.api.dto.ApiOutputDto;
import com.vasl.samp.service.model.ApiEndpointInputModel;
import com.vasl.samp.service.model.ApiInputModel;

import java.util.List;

public interface ApiService {

    //ApiServices
    ApiOutputDto create(ApiInputModel dto);
    public List<ApiOutputDto> getAll();
    public ApiOutputDto getById(String id);
public ApiOutputDto update(String id, ApiInputModel dto);
    public List<ApiOutputDto> deleteById(String id);


    //EndpointServices
    List<ApiEndpointOutputDto> insertEndpoint(String apiId, ApiEndpointInputModel apiEndpointInputModel);
    public List<ApiEndpointOutputDto> getAllEndpoints(String ApiId);
    public ApiEndpointOutputDto getEndpointById(String ApiId, String apiEndpointId);
    public List<ApiEndpointOutputDto> updateEndpoint(String apiId, String endpointId, ApiEndpointInputDto endpointDto);
    public List<ApiEndpointOutputDto> upsertEndpoint(String apiId, ApiEndpointInputModel dto);
    public List<ApiEndpointOutputDto> deleteEndpoint(String apiId, String endpointId);

}
