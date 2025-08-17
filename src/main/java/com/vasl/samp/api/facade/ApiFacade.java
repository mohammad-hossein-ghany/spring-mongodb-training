package com.vasl.samp.api.facade;


import com.vasl.samp.api.dto.ApiEndpointInputDto;
import com.vasl.samp.api.dto.ApiEndpointOutputDto;
import com.vasl.samp.api.facade.mapper.ApiEndpointMapper;
import com.vasl.samp.api.facade.mapper.ApiMapper;
import com.vasl.samp.service.ApiService;
import com.vasl.samp.service.model.ApiEndpointInputModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ApiFacade {

    private final ApiService apiService;
    private final ApiEndpointMapper apiEndpointMapper;

    public List<ApiEndpointOutputDto> insertEndpoint(String apiId, @RequestBody ApiEndpointInputDto dto) {
        ApiEndpointInputModel apiEndpointInputModel = apiEndpointMapper.toModel(dto);
        return apiService.insertEndpoint(apiId, apiEndpointInputModel);
    }
}
