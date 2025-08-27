package com.vasl.samp.dal.repository;

import com.vasl.samp.dal.entity.Api;
import com.vasl.samp.dal.entity.ApiEndpoint;


public interface CustomApiRepository {

    void updateApiEndpoint(Api api, String endpointId, ApiEndpoint apiEndpoint);
}
