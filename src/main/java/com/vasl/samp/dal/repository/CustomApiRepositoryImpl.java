package com.vasl.samp.dal.repository;

import com.vasl.samp.dal.entity.Api;
import com.vasl.samp.dal.entity.ApiEndpoint;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomApiRepositoryImpl implements CustomApiRepository {

//    private final ApiRepository apiRepository;

    //region commented repository
    @Override
    public void updateApiEndpoint(Api api, String endpointId,  ApiEndpoint apiEndpoint) {
/*
        api.getEndpoints().forEach(endpoint -> {
            if (endpointId.equals(endpoint.getId())) {
                endpoint.setDescription(apiEndpoint.getDescription());
                endpoint.setMethods(apiEndpoint.getMethods());
                endpoint.setPath(apiEndpoint.getPath());
            }
        });

        apiRepository.save(api);
*/
    }
    //endregion




















}
