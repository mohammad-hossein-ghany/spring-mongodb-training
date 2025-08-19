package com.vasl.samp.api.facade;


import com.vasl.samp.api.dto.ApiEndpointInputDto;
import com.vasl.samp.api.dto.ApiEndpointOutputDto;
import com.vasl.samp.api.dto.ApiInputDto;
import com.vasl.samp.api.dto.ApiOutputDto;
import com.vasl.samp.api.facade.mapper.ApiEndpointMapper;
import com.vasl.samp.api.facade.mapper.ApiMapper;
import com.vasl.samp.service.ApiService;
import com.vasl.samp.service.model.ApiEndpointInputModel;
import com.vasl.samp.service.model.ApiEndpointOutputModel;
import com.vasl.samp.service.model.ApiInputModel;
import com.vasl.samp.service.model.ApiOutputModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ApiFacade {

    private final ApiService apiService;
    private final ApiEndpointMapper apiEndpointMapper;
    private final ApiMapper apiMapper;


    public List<ApiOutputDto> getAllApi() {
        List<ApiOutputModel> apiOutputModels = apiService.getAll();
        return apiMapper.modelToDto(apiOutputModels);
    }

    public ApiOutputDto getApiById(String id) {
        ApiOutputModel apiOutputModel = apiService.getById(id);
        return apiMapper.toDto(apiOutputModel);
    }

    public ApiOutputDto createApi(ApiInputDto apiInputDto) {
        ApiInputModel apiInputModel = apiMapper.toModel(apiInputDto);
        ApiOutputModel apiOutputModel = apiService.create(apiInputModel);
        return apiMapper.toDto(apiOutputModel);
    }

    public ApiOutputDto updateApi(ApiInputDto apiInputDto, String id) {
        ApiInputModel apiInputModel = apiMapper.toModel(apiInputDto);
        ApiOutputModel apiOutputModel = apiService.update(id, apiInputModel);
        return apiMapper.toDto(apiOutputModel);
    }


    public List<ApiOutputDto> deleteApiById(String id) {
        List<ApiOutputModel> apiOutputModels = apiService.deleteById(id);
        return apiMapper.modelToDto(apiOutputModels);
    }


    public List<ApiEndpointOutputDto> insertEndpoint(String apiId, ApiEndpointInputDto dto) {
        ApiEndpointInputModel apiEndpointInputModel = apiEndpointMapper.toModel(dto);
        List<ApiEndpointOutputModel> apiEndpointOutputModels = apiService.insertEndpoint(apiId, apiEndpointInputModel);
        return apiEndpointMapper.modelToDto(apiEndpointOutputModels);
    }


    public List<ApiEndpointOutputDto> getAllEndpoints(String apiId) {
        List<ApiEndpointOutputModel> apiEndpointOutputModels = apiService.getAllEndpoints(apiId);
        return apiEndpointMapper.modelToDto(apiEndpointOutputModels);
    }

    public ApiEndpointOutputDto getEndpointById(String apiId, String endpointId) {
        ApiEndpointOutputModel apiEndpointOutputModel = apiService.getEndpointById(apiId, endpointId);
        return apiEndpointMapper.toDto(apiEndpointOutputModel);
    }



    public List<ApiEndpointOutputDto> updateEndpoint(String apiId, String endpointId, ApiEndpointInputDto dto) {
        ApiEndpointInputModel apiEndpointInputModel = apiEndpointMapper.toModel(dto);
        List<ApiEndpointOutputModel> apiEndpointOutputModels = apiService.updateEndpoint(apiId, endpointId, apiEndpointInputModel);
        return apiEndpointMapper.modelToDto(apiEndpointOutputModels);
    }

    public List<ApiEndpointOutputDto> upsertEndpoint(String apiId, ApiEndpointInputDto dto) {
        ApiEndpointInputModel apiEndpointInputModel = apiEndpointMapper.toModel(dto);
        List<ApiEndpointOutputModel>  apiEndpointOutputModels = apiService.upsertEndpoint(apiId, apiEndpointInputModel);
        return apiEndpointMapper.modelToDto(apiEndpointOutputModels);
    }

    public List<ApiEndpointOutputDto> deleteEndpoint(String apiId, String endpointId) {
        List<ApiEndpointOutputModel> apiEndpointOutputModels = apiService.deleteEndpoint(apiId, endpointId);
        return apiEndpointMapper.modelToDto(apiEndpointOutputModels);
    }


    }






















