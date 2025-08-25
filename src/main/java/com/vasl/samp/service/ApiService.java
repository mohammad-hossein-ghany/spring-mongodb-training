package com.vasl.samp.service;

import com.vasl.samp.service.model.*;

import java.util.List;

public interface ApiService {

    //Api-Services
    ApiOutputModel createApi(ApiInputModel model);
    public List<ApiOutputModel> getAllApi();
    public ApiOutputModel getApiById(String id);
    public ApiOutputModel updateApi(String id, ApiInputModel model);
    public List<ApiOutputModel> deleteApiById(String id);


    //ApiEndpoint-Services
    List<ApiEndpointOutputModel> insertEndpoint(String apiId, ApiEndpointInputModel apiEndpointInputModel);
    public List<ApiEndpointOutputModel> getAllEndpoints(String ApiId);
    public ApiEndpointOutputModel getEndpointById(String ApiId, String apiEndpointId);
    public List<ApiEndpointOutputModel> updateEndpoint(String apiId, String endpointId, ApiEndpointInputModel model);
    public List<ApiEndpointOutputModel> upsertEndpoint(String apiId, ApiEndpointInputModel model);
    public List<ApiEndpointOutputModel> deleteEndpoint(String apiId, String endpointId);

    //ApiEndpointMethod-Services
    List<ApiEndpointMethodOutputModel> insertEndpointMethod(String apiId, String apiEndpointId, ApiEndpointMethodInputModel apiEndpointInputModel);
    public List<ApiEndpointMethodOutputModel> getAllEndpointMethods(String ApiId, String apiEndpointId);
    public ApiEndpointMethodOutputModel getEndpointMethodById(String ApiId, String apiEndpointId, String apiEndpointMethodId);
    public List<ApiEndpointMethodOutputModel> updateEndpointMethod(String apiId, String endpointId, String apiEndpointMethodId, ApiEndpointMethodInputModel model);
    public List<ApiEndpointMethodOutputModel> upsertEndpointMethod(String apiId, String apiEndpointId, ApiEndpointInputModel model);
    public List<ApiEndpointMethodOutputModel> deleteEndpointMethod(String apiId, String endpointId, String apiEndpointMethodId);
}
