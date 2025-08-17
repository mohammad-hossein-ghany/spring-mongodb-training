package com.vasl.samp.dal.repository;

import com.vasl.samp.dal.entity.ApiEndpoint;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApiEndpointRepository extends MongoRepository<ApiEndpoint, String> {
}
