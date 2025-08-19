package com.vasl.samp.service;

import com.vasl.samp.service.model.ApiInputModel;
import com.vasl.samp.api.facade.mapper.ApiEndpointMapper;
import com.vasl.samp.api.facade.mapper.ApiMapper;
import com.vasl.samp.dal.entity.Api;
import com.vasl.samp.dal.entity.ApiEndpoint;
import com.vasl.samp.dal.repository.ApiEndpointRepository;
import com.vasl.samp.dal.repository.ApiRepository;
import com.vasl.samp.service.model.ApiEndpointInputModel;
import com.vasl.samp.service.model.ApiEndpointOutputModel;
import com.vasl.samp.service.model.ApiOutputModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {
    //Api tools
    private final ApiRepository apiRepository;
    private final ApiMapper apiMapper;

    //ApiEndpoint tools
    private final ApiEndpointRepository apiEndpointRepository;
    private final ApiEndpointMapper apiEndpointMapper;


    /*---------------------------------------->>[ApiServices]<<----------------------------------------*/

    public ApiOutputModel create(ApiInputModel model) {
        Api entity = apiMapper.toEntity(model);
        entity = apiRepository.save(entity);
        return apiMapper.toModel(entity);
    }

    public List<ApiOutputModel> getAll() {
        return apiRepository.findAll()
                .stream()
                .map(apiMapper::toModel)
                .toList();
    }

    public ApiOutputModel getById(String id) {
        Api entity = apiRepository.findById(id).orElseThrow(() -> new RuntimeException("entity not found"));
        return apiMapper.toModel(entity);
    }

    public ApiOutputModel update(String id, ApiInputModel model) {
        Api entity = apiRepository.findById(id).orElseThrow(() -> new RuntimeException("entity not found"));
        apiMapper.updateEntity(model, entity);
        entity = apiRepository.save(entity);
        return apiMapper.toModel(entity);
    }

    public List<ApiOutputModel> deleteById(String id) {

        apiRepository.findById(id)
                .ifPresentOrElse(
                        apiRepository::delete,
                        () -> {
                            throw new RuntimeException("entity not found");
                        }
                );

        return apiMapper.entityToModel(apiRepository.findAll());

           /*
        if (_apiRepository.existsById(id))
            _apiRepository.deleteById(id);
        else
            throw new RuntimeException("entity not found");
        */

    }



    /*---------------------------------------->>[EndpointServices]<<----------------------------------------*/

    public List<ApiEndpointOutputModel> insertEndpoint(String apiId, ApiEndpointInputModel apiEndpointInputModel) {
        Api apiEntity = apiRepository.findById(apiId).orElseThrow(() -> new RuntimeException("entity not found"));

        if (apiEntity.getEndpoints() == null) {
            apiEntity.setEndpoints(new ArrayList<>());
        }

        List<ApiEndpoint> endpoints = apiEntity.getEndpoints();
        endpoints.stream()
                .filter(e -> e.getName().equals(apiEndpointInputModel.getName()))
                .findFirst()
                .ifPresentOrElse(
                        e -> {
                            throw new RuntimeException(e.getName() + " endpoint already exists");
                        },
                        () -> {
                            ApiEndpoint apiEndpoint = apiEndpointMapper.toEntity(apiEndpointInputModel);
                            apiEndpoint.setId(UUID.randomUUID().toString().replace("-", ""));
                            endpoints.add(apiEndpoint);
                        });

        apiEntity = apiRepository.save(apiEntity);
        return apiEndpointMapper.toModel(apiEntity.getEndpoints());
    }


    public List<ApiEndpointOutputModel> getAllEndpoints(String ApiId) {
        Api apiEntity = apiRepository.findById(ApiId).orElseThrow(() -> new RuntimeException("entity not found"));
        return apiEndpointMapper.toModel(apiEntity.getEndpoints());
    }


    public ApiEndpointOutputModel getEndpointById(String ApiId, String apiEndpointId) {
        Api apiEntity = apiRepository.findById(ApiId).orElseThrow(() -> new RuntimeException("entity not found"));

        List<ApiEndpoint> endpoints = apiEntity.getEndpoints();
        ApiEndpoint endpoint = endpoints.stream()
                .filter(e -> e.getId().equals(apiEndpointId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("endpoint not found"));

        return apiEndpointMapper.toModel(endpoint);
    }


    public List<ApiEndpointOutputModel> updateEndpoint(String apiId, String endpointId, ApiEndpointInputModel model) {
        Api apiEntity = apiRepository.findById(apiId).orElseThrow(() -> new RuntimeException("entity not found"));
        ApiEndpoint apiEndpointEntity = apiEndpointRepository.findById(endpointId).orElseThrow(() -> new RuntimeException("endpoint not found"));

        List<ApiEndpoint> endpoints = apiEntity.getEndpoints();
        endpoints.stream().filter(e -> e.getId().equals(endpointId)).findFirst().ifPresentOrElse(
                e -> apiEndpointMapper.updateEntity(model, e),
                () -> {
                    throw new RuntimeException(endpointId + " endpoint not found");
                }
        );

        apiEntity = apiRepository.save(apiEntity);

        return apiEndpointMapper.toModel(apiEntity.getEndpoints());

    }

    public List<ApiEndpointOutputModel> upsertEndpoint(String apiId, ApiEndpointInputModel model) {
        Api apiEntity = apiRepository.findById(apiId).orElseThrow(() -> new RuntimeException("entity not found"));

        if (apiEntity.getEndpoints() == null) {
            apiEntity.setEndpoints(new ArrayList<>());
        }

        List<ApiEndpoint> endpoints = apiEntity.getEndpoints();
        endpoints.stream()
                .filter(e -> e.getName().equals(model.getName()))
                .findFirst()
                .ifPresentOrElse(
                        e -> BeanUtils.copyProperties(model, e),
                        () -> endpoints.add(apiEndpointMapper.toEntity(model)));

        apiEntity = apiRepository.save(apiEntity);
        return apiEndpointMapper.toModel(apiEntity.getEndpoints());
    }

    //delete ?

    public List<ApiEndpointOutputModel> deleteEndpoint(String apiId, String endpointId) {
        Api apiEntity = apiRepository.findById(apiId).orElseThrow(() -> new RuntimeException("entity not found"));

        boolean removed = apiEntity.getEndpoints().removeIf(e -> e.getId().equals(endpointId));

        if (!removed) {
            throw new RuntimeException(endpointId + " endpoint not found");
        }

        apiEntity = apiRepository.save(apiEntity);
        return apiEndpointMapper.toModel(apiEntity.getEndpoints());
    }


}
