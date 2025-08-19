package com.vasl.samp.service;

import com.vasl.samp.api.dto.ApiEndpointInputDto;
import com.vasl.samp.api.dto.ApiEndpointOutputDto;
import com.vasl.samp.api.dto.ApiOutputDto;
import com.vasl.samp.service.model.ApiEndpointInputModel;
import com.vasl.samp.service.model.ApiEndpointOutputModel;
import com.vasl.samp.service.model.ApiInputModel;
import com.vasl.samp.service.model.ApiOutputModel;

import java.util.List;

public interface ApiService {

    //ApiServices
    ApiOutputModel create(ApiInputModel model);
    public List<ApiOutputModel> getAll();
    public ApiOutputModel getById(String id);
    public ApiOutputModel update(String id, ApiInputModel model);
    public List<ApiOutputModel> deleteById(String id);


    //EndpointServices
    List<ApiEndpointOutputModel> insertEndpoint(String apiId, ApiEndpointInputModel apiEndpointInputModel);
    public List<ApiEndpointOutputModel> getAllEndpoints(String ApiId);
    public ApiEndpointOutputModel getEndpointById(String ApiId, String apiEndpointId);
    public List<ApiEndpointOutputModel> updateEndpoint(String apiId, String endpointId, ApiEndpointInputModel model);
    public List<ApiEndpointOutputModel> upsertEndpoint(String apiId, ApiEndpointInputModel model);
    public List<ApiEndpointOutputModel> deleteEndpoint(String apiId, String endpointId);

}
