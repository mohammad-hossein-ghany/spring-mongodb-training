package com.vasl.samp.service;

import com.vasl.samp.api.facade.mapper.ApiEndpointMethodMapper;
import com.vasl.samp.dal.entity.ApiEndpointMethod;
import com.vasl.samp.dal.repository.MonzRepository;
import com.vasl.samp.service.model.*;
import com.vasl.samp.api.facade.mapper.ApiEndpointMapper;
import com.vasl.samp.api.facade.mapper.ApiMapper;
import com.vasl.samp.dal.entity.Api;
import com.vasl.samp.dal.entity.ApiEndpoint;
import com.vasl.samp.dal.repository.ApiRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {
    //Api tools
    private final ApiRepository apiRepository;
    private final ApiMapper apiMapper;
    private final MonzRepository monzRepository;

    //ApiEndpoint tools
    private final ApiEndpointMapper apiEndpointMapper;

    //ApiEndpointMethod tools
    private final ApiEndpointMethodMapper apiEndpointMethodMapper;


    /*---------------------------------------->>[Api-Services]<<----------------------------------------*/
    @Override
    public ApiOutputModel createApi(ApiInputModel model) {
        Api entity = apiMapper.toEntity(model);
        entity = apiRepository.save(entity);
        return apiMapper.toModel(entity);
    }

    @Override
    public List<ApiOutputModel> getAllApi() {
        return apiRepository.findAll()
                .stream()
                .map(apiMapper::toModel)
                .toList();
    }

    @Override
    public ApiOutputModel getApiById(String id) {
        Api entity = apiRepository.findById(id).orElseThrow(() -> new RuntimeException("entity not found"));
        return apiMapper.toModel(entity);
    }

    @Override
    public ApiOutputModel updateApi(String id, ApiInputModel model) {
        Api entity = apiRepository.findById(id).orElseThrow(() -> new RuntimeException("entity not found"));
        apiMapper.updateEntity(model, entity);
        entity = apiRepository.save(entity);
        return apiMapper.toModel(entity);
    }

    @Override
    public List<ApiOutputModel> deleteApiById(String id) {

        if (monzRepository.existsApiInMonzePackageByApiId(new ObjectId(id)).getExists()) {
            boolean exist = monzRepository.existsApiInMonzePackageByApiId(new ObjectId(id)).getExists();
            throw new RuntimeException("api already exists in monze package");
        }

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



    /*---------------------------------------->>[ApiEndpoint-Services]<<----------------------------------------*/

    @Override
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


    @Override
    public List<ApiEndpointOutputModel> getAllEndpoints(String ApiId) {
        Api apiEntity = apiRepository.findById(ApiId).orElseThrow(() -> new RuntimeException("entity not found"));
        return apiEndpointMapper.toModel(apiEntity.getEndpoints());
    }


    @Override
    public ApiEndpointOutputModel getEndpointById(String ApiId, String apiEndpointId) {
        Api apiEntity = apiRepository.findById(ApiId).orElseThrow(() -> new RuntimeException("entity not found"));

        List<ApiEndpoint> endpoints = apiEntity.getEndpoints();
        ApiEndpoint endpoint = endpoints.stream()
                .filter(e -> apiEndpointId.equals(e.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("endpoint not found"));

        return apiEndpointMapper.toModel(endpoint);
    }


    @Override
    public List<ApiEndpointOutputModel> updateEndpoint(String apiId, String endpointId, ApiEndpointInputModel model) {
        Api apiEntity = apiRepository.findById(apiId).orElseThrow(() -> new RuntimeException("entity not found"));
        // apiEndpointRepository.findById(endpointId).orElseThrow(() -> new RuntimeException("endpoint not found"));

        List<ApiEndpoint> endpoints = apiEntity.getEndpoints();
        endpoints.stream()
                .filter(e -> endpointId.equals(e.getId()))
                .findFirst()
                .ifPresentOrElse(
                        e -> apiEndpointMapper.updateEntity(model, e),
                        () -> {
                            throw new RuntimeException(endpointId + " endpoint not found");
                        }
                );

        apiEntity = apiRepository.save(apiEntity);

        return apiEndpointMapper.toModel(apiEntity.getEndpoints());

    }

    @Override
    public List<ApiEndpointOutputModel> upsertEndpoint(String apiId, ApiEndpointInputModel model) {
        Api apiEntity = apiRepository.findById(apiId).orElseThrow(() -> new RuntimeException("entity not found"));

        if (apiEntity.getEndpoints() == null) {
            apiEntity.setEndpoints(new ArrayList<>());
        }

        List<ApiEndpoint> endpoints = apiEntity.getEndpoints();
        endpoints.stream()
                .filter(e -> Objects.equals(model.getName(), e.getName()))
                .findFirst()
                .ifPresentOrElse(
                        e -> apiEndpointMapper.updateEntity(model, e),
                        () -> {
                            ApiEndpoint apiEndpoint = apiEndpointMapper.toEntity(model);
                            apiEndpoint.setId(UUID.randomUUID().toString().replace("-", ""));
                            endpoints.add(apiEndpoint);
                        });


        apiEntity = apiRepository.save(apiEntity);
        return apiEndpointMapper.toModel(apiEntity.getEndpoints());
    }

    //delete ?

    @Override
    public List<ApiEndpointOutputModel> deleteEndpoint(String apiId, String endpointId) {
        Api apiEntity = apiRepository.findById(apiId).orElseThrow(() -> new RuntimeException("entity not found"));

        boolean removed = apiEntity.getEndpoints().removeIf(e -> endpointId.equals(e.getId()));

        if (!removed) {
            throw new RuntimeException(endpointId + " endpoint not found");
        }

        apiEntity = apiRepository.save(apiEntity);
        return apiEndpointMapper.toModel(apiEntity.getEndpoints());
    }

    /*---------------------------------------->>[ApiEndpointMethod-Services]<<----------------------------------------*/

    @Override
    public List<ApiEndpointMethodOutputModel> insertEndpointMethod(String apiId, String apiEndpointId, ApiEndpointMethodInputModel apiEndpointInputModel) {
        return List.of();
    }

    @Override
    public List<ApiEndpointMethodOutputModel> getAllEndpointMethods(String apiId, String apiEndpointId) {
//        Api apiEntity = apiRepository.findById(ApiId).orElseThrow(() -> new RuntimeException("entity not found"));

        Api api = apiRepository.findById(apiId).orElseThrow(() -> new RuntimeException("entity not found"));

        if (api.getEndpoints() == null) {
            api.setEndpoints(new ArrayList<>());
        }

        ApiEndpoint apiEndpoint =
                api.getEndpoints()
                        .stream()
                        .filter(e -> apiEndpointId.equals(e.getId()))
                        .findFirst()
                        .orElseThrow(
                                () -> new RuntimeException(apiEndpointId + " endpoint not found")
                        );

        return apiEndpointMethodMapper.entityToModel(apiEndpoint.getMethods());

    }

    @Override
    public ApiEndpointMethodOutputModel getEndpointMethodById(String apiId, String apiEndpointId, String apiEndpointMethodId) {


        Api api = apiRepository.findById(apiId).orElseThrow(() -> new RuntimeException("entity not found"));

        if (api.getEndpoints() == null) {
            api.setEndpoints(new ArrayList<>());
        }

        ApiEndpoint apiEndpoint =
                api.getEndpoints()
                        .stream()
                        .filter(e -> apiEndpointId.equals(e.getId()))
                        .findFirst()
                        .orElseThrow(
                                () -> new RuntimeException(apiEndpointId + " endpoint not found")
                        );

        if (apiEndpoint.getMethods() == null) {
            apiEndpoint.setMethods(new ArrayList<>());
        }

        ApiEndpointMethod apiEndpointMethod =
                apiEndpoint.getMethods()
                        .stream()
                        .filter(e -> apiEndpointMethodId.equals(e.getId()))
                        .findFirst()
                        .orElseThrow(
                                () -> new RuntimeException(apiEndpointMethodId + " endpoint method not found")
                        );

        return apiEndpointMethodMapper.toModel(apiEndpointMethod);
    }

    @Override
    public List<ApiEndpointMethodOutputModel> updateEndpointMethod(String apiId, String apiEndpointId, String apiEndpointMethodId, ApiEndpointMethodInputModel model) {

        Api api = apiRepository.findById(apiId).orElseThrow(() -> new RuntimeException("entity not found"));

        if (api.getEndpoints() == null) {
            throw new RuntimeException(apiEndpointId + " endpoint not found");
        }

        ApiEndpoint apiEndpoint =
                api.getEndpoints()
                        .stream()
                        .filter(e -> apiEndpointId.equals(e.getId()))
                        .findFirst()
                        .orElseThrow(
                                () -> new RuntimeException(apiEndpointId + " endpoint not found")
                        );

        if (apiEndpoint.getMethods() == null) {
            throw new RuntimeException(apiEndpointMethodId + " endpoint method not found");
        }


        apiEndpoint.getMethods()
                .stream()
                .filter(e -> apiEndpointMethodId.equals(e.getId()))
                .findFirst()
                .ifPresentOrElse(
                        e -> apiEndpointMethodMapper.updateEntity(model, e),
                        () ->
                        {
                            throw new RuntimeException(apiEndpointMethodId + " endpoint method not found");
                        }
                );

        apiRepository.save(api);

        return getAllEndpointMethods(apiId, apiEndpointId);
    }

    @Override
    public List<ApiEndpointMethodOutputModel> upsertEndpointMethod(String apiId, String apiEndpointId, ApiEndpointInputModel model) {
        return List.of();
    }

    @Override
    public List<ApiEndpointMethodOutputModel> deleteEndpointMethod(String apiId, String apiEndpointId, String apiEndpointMethodId) {

        Api api = apiRepository.findById(apiId).orElseThrow(() -> new RuntimeException("entity not found"));

        if (api.getEndpoints() == null) {
            throw new RuntimeException(apiEndpointId + " endpoint not found");
        }

        ApiEndpoint apiEndpoint =
                api.getEndpoints()
                        .stream()
                        .filter(e -> apiEndpointId.equals(e.getId()))
                        .findFirst()
                        .orElseThrow(
                                () -> new RuntimeException(apiEndpointId + " endpoint not found")
                        );

        if (apiEndpoint.getMethods() == null) {
            throw new RuntimeException(apiEndpointMethodId + " endpoint method not found");
        }


//        apiEndpoint.getMethods()
//                .stream()
//                .filter(e -> apiEndpointMethodId.equals(e.getId()))
//                .findFirst()
//                .ifPresentOrElse(
//                        e -> apiendpointmethod,
//                        () ->
//                        {
//                            throw new RuntimeException(apiEndpointMethodId + " endpoint method not found");
//                        }
//                );

        boolean removed = apiEndpoint.getMethods().removeIf(e -> apiEndpointMethodId.equals(e.getId()));

        if (!removed) {
            throw new RuntimeException(" endpoint method not found");
        }

        api = apiRepository.save(api);
        return getAllEndpointMethods(apiId, apiEndpointId);
    }


}
