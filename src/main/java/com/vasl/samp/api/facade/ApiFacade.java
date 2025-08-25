package com.vasl.samp.api.facade;


import com.vasl.samp.api.dto.*;
import com.vasl.samp.api.facade.mapper.ApiEndpointMapper;
import com.vasl.samp.api.facade.mapper.ApiEndpointMethodMapper;
import com.vasl.samp.api.facade.mapper.ApiMapper;
import com.vasl.samp.service.ApiService;
import com.vasl.samp.service.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ApiFacade {

    private final ApiService apiService;
    private final ApiEndpointMapper apiEndpointMapper;
    private final ApiMapper apiMapper;
    private final ApiEndpointMethodMapper apiEndpointMethodMapper;

    /*---------------------------------------->>[Api-Facade]<<----------------------------------------*/

    public List<ApiOutputDto> getAllApi() {
        List<ApiOutputModel> apiOutputModels = apiService.getAllApi();
        return apiMapper.modelToDto(apiOutputModels);
    }

    public ApiOutputDto getApiById(String id) {
        ApiOutputModel apiOutputModel = apiService.getApiById(id);
        return apiMapper.toDto(apiOutputModel);
    }

    public ApiOutputDto createApi(ApiInputDto apiInputDto) {
        ApiInputModel apiInputModel = apiMapper.toModel(apiInputDto);
        ApiOutputModel apiOutputModel = apiService.createApi(apiInputModel);
        return apiMapper.toDto(apiOutputModel);
    }

    public ApiOutputDto updateApi(ApiInputDto apiInputDto, String id) {
        ApiInputModel apiInputModel = apiMapper.toModel(apiInputDto);
        ApiOutputModel apiOutputModel = apiService.updateApi(id, apiInputModel);
        return apiMapper.toDto(apiOutputModel);
    }


    public List<ApiOutputDto> deleteApiById(String id) {
        List<ApiOutputModel> apiOutputModels = apiService.deleteApiById(id);
        return apiMapper.modelToDto(apiOutputModels);
    }


    /*---------------------------------------->>[ApiEndpoint-Facade]<<----------------------------------------*/

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
        List<ApiEndpointOutputModel> apiEndpointOutputModels = apiService.upsertEndpoint(apiId, apiEndpointInputModel);
        return apiEndpointMapper.modelToDto(apiEndpointOutputModels);
    }

    public List<ApiEndpointOutputDto> deleteEndpoint(String apiId, String endpointId) {
        List<ApiEndpointOutputModel> apiEndpointOutputModels = apiService.deleteEndpoint(apiId, endpointId);
        return apiEndpointMapper.modelToDto(apiEndpointOutputModels);
    }


    /*---------------------------------------->>[ApiEndpointMethod-Facade]<<----------------------------------------*/

    public List<ApiEndpointMethodOutputDto> insertEndpointMethod(String apiId, String apiEndpointId, ApiEndpointMethodInputDto apiEndpointInputDto) {
        ApiEndpointMethodInputModel apiEndpointMethodInputModel = apiEndpointMethodMapper.toModel(apiEndpointInputDto);
        List<ApiEndpointMethodOutputModel> apiEndpointMethodOutputModels = apiService.insertEndpointMethod(apiId, apiEndpointId, apiEndpointMethodInputModel);
        return apiEndpointMethodMapper.modelToDto(apiEndpointMethodOutputModels);
    }

    public List<ApiEndpointMethodOutputDto> getAllEndpointMethods(String ApiId, String apiEndpointId){
        List<ApiEndpointMethodOutputModel> apiEndpointMethodOutputModels = apiService.getAllEndpointMethods(ApiId,apiEndpointId);
        return apiEndpointMethodMapper.modelToDto(apiEndpointMethodOutputModels);
    }

    public ApiEndpointMethodOutputDto getEndpointMethodById(String ApiId, String apiEndpointId, String apiEndpointMethodId) {
        ApiEndpointMethodOutputModel apiEndpointMethodOutputModel = apiService.getEndpointMethodById(ApiId, apiEndpointId, apiEndpointMethodId);
        return apiEndpointMethodMapper.toDto(apiEndpointMethodOutputModel);
    }
//    public List<ApiEndpointMethodOutputModel> updateEndpointMethod(String apiId, String endpointId, String apiEndpointMethodId, ApiEndpointMethodInputModel model);
//    public List<ApiEndpointMethodOutputModel> upsertEndpointMethod(String apiId, String apiEndpointId, ApiEndpointInputModel model);
//    public List<ApiEndpointMethodOutputModel> deleteEndpointMethod(String apiId, String endpointId, String apiEndpointMethodId);


}



























