package com.vasl.samp.service;

import com.vasl.samp.api.dto.ApiEndpointInputDto;
import com.vasl.samp.api.dto.ApiEndpointOutputDto;
import com.vasl.samp.api.dto.ApiInputDto;
import com.vasl.samp.api.dto.ApiOutputDto;
import com.vasl.samp.api.facade.mapper.ApiEndpointMapper;
import com.vasl.samp.api.facade.mapper.ApiMapper;
import com.vasl.samp.dal.entity.Api;
import com.vasl.samp.dal.entity.ApiEndpoint;
import com.vasl.samp.dal.repository.ApiEndpointRepository;
import com.vasl.samp.dal.repository.ApiRepository;
import com.vasl.samp.service.model.ApiEndpointInputModel;
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
    private final ApiEndpointRepository _apiEndpointRepository;
    private final ApiEndpointMapper _apiEndpointMapper;

    public ApiOutputDto create(ApiInputDto dto) {
        Api entity = apiMapper.toEntity(dto);
        entity = apiRepository.save(entity);
        return apiMapper.toDto(entity);
    }

    public List<ApiOutputDto> getAll() {
        return apiRepository.findAll()
                .stream()
                .map(apiMapper::toDto)
                .toList();
    }

    public ApiOutputDto getById(String id) {
        Api entity = apiRepository.findById(id).orElseThrow(() -> new RuntimeException("entity not found"));
        return apiMapper.toDto(entity);
    }

    public ApiOutputDto update(String id, ApiInputDto dto) {
        Api entity = apiRepository.findById(id).orElseThrow(() -> new RuntimeException("entity not found"));
        apiMapper.updateEntityFromDto(dto, entity);
        entity = apiRepository.save(entity);
        return apiMapper.toDto(entity);
    }

    public List<ApiOutputDto> deleteById(String id) {

        apiRepository.findById(id)
                .ifPresentOrElse(
                        e -> {
                            apiRepository.delete(e);
                        },
                        () -> {
                            throw new RuntimeException("entity not found");
                        }
                );

        return apiMapper.toDtoList(apiRepository.findAll());

           /*
        if (_apiRepository.existsById(id))
            _apiRepository.deleteById(id);
        else
            throw new RuntimeException("entity not found");
        */

    }


    //EndpoinServices

    public List<ApiEndpointOutputDto> insertEndpoint(String apiId, ApiEndpointInputModel apiEndpointInputModel) {
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
                            ApiEndpoint apiEndpoint = _apiEndpointMapper.toEntity(apiEndpointInputModel);
                            apiEndpoint.setId(UUID.randomUUID().toString().replace("-", ""));
                            endpoints.add(apiEndpoint);
                        });

        apiEntity = apiRepository.save(apiEntity);
        return _apiEndpointMapper.toDtoList(apiEntity.getEndpoints());
    }


    public List<ApiEndpointOutputDto> getAllEndpoints(String ApiId) {
        Api apiEntity = apiRepository.findById(ApiId).orElseThrow(() -> new RuntimeException("entity not found"));
        return _apiEndpointMapper.toDtoList(apiEntity.getEndpoints());
    }


    public ApiEndpointOutputDto getEndpointById(String ApiId, String apiEndpointId) {
        Api apiEntity = apiRepository.findById(ApiId).orElseThrow(() -> new RuntimeException("entity not found"));

        List<ApiEndpoint> endpoints = apiEntity.getEndpoints();
        ApiEndpoint endpoint = endpoints.stream()
                .filter(e -> e.getId().equals(apiEndpointId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("endpoint not found"));

        return _apiEndpointMapper.toDto(endpoint);
    }


    public List<ApiEndpointOutputDto> updateEndpoint(String apiId, String endpointId, ApiEndpointInputDto endpointDto) {
        Api apiEntity = apiRepository.findById(apiId).orElseThrow(() -> new RuntimeException("entity not found"));
        ApiEndpoint apiEndpointEntity = _apiEndpointRepository.findById(endpointId).orElseThrow(() -> new RuntimeException("endpoint not found"));

        List<ApiEndpoint> endpoints = apiEntity.getEndpoints();
        endpoints.stream().filter(e -> e.getId().equals(endpointId)).findFirst().ifPresentOrElse(
                e -> _apiEndpointMapper.updateEntityFromDto(endpointDto, e),
                () -> {
                    throw new RuntimeException(endpointId + " endpoint not found");
                }
        );

        apiEntity = apiRepository.save(apiEntity);

        return _apiEndpointMapper.toDtoList(apiEntity.getEndpoints());

    }

    public List<ApiEndpointOutputDto> upsertEndpoint(String apiId, ApiEndpointInputDto dto) {
        Api apiEntity = apiRepository.findById(apiId).orElseThrow(() -> new RuntimeException("entity not found"));

        if (apiEntity.getEndpoints() == null) {
            apiEntity.setEndpoints(new ArrayList<>());
        }

        List<ApiEndpoint> endpoints = apiEntity.getEndpoints();
        endpoints.stream()
                .filter(e -> e.getName().equals(dto.getName()))
                .findFirst()
                .ifPresentOrElse(
                        e -> BeanUtils.copyProperties(dto, e),
                        () -> endpoints.add(_apiEndpointMapper.toEntity(dto)));

        apiEntity = apiRepository.save(apiEntity);
        return _apiEndpointMapper.toDtoList(apiEntity.getEndpoints());
    }

    //delete ?

    public List<ApiEndpointOutputDto> deleteEndpoint(String apiId, String endpointId) {
        Api apiEntity = apiRepository.findById(apiId).orElseThrow(() -> new RuntimeException("entity not found"));

        boolean removed = apiEntity.getEndpoints().removeIf(e -> e.getId().equals(endpointId));

        if (!removed) {
            throw new RuntimeException(endpointId + " endpoint not found");
        }

        apiEntity = apiRepository.save(apiEntity);
        return _apiEndpointMapper.toDtoList(apiEntity.getEndpoints());
    }


}
