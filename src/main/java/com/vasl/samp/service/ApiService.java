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
import jakarta.websocket.Endpoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLPeerUnverifiedException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiService {
    //Api tools
    private final ApiRepository _apiRepository;
    private final ApiMapper _apiMapper;

    //ApiEndpoint tools
    private final ApiEndpointRepository _apiEndpointRepository;
    private final ApiEndpointMapper _apiEndpointMapper;

    public ApiOutputDto create(ApiInputDto dto) {
        Api entity = _apiMapper.toEntity(dto);
        entity = _apiRepository.save(entity);
        return _apiMapper.toDto(entity);
    }

    public List<ApiOutputDto> getAll() {
        return _apiRepository.findAll()
                .stream()
                .map(_apiMapper::toDto)
                .toList();
    }

    public ApiOutputDto getById(String id) {
        Api entity = _apiRepository.findById(id).orElseThrow(() -> new RuntimeException("entity not found"));
        return _apiMapper.toDto(entity);
    }

    public ApiOutputDto update(String id, ApiInputDto dto) {
        Api entity = _apiRepository.findById(id).orElseThrow(() -> new RuntimeException("entity not found"));
        _apiMapper.updateEntityFromDto(dto, entity);
        entity = _apiRepository.save(entity);
        return _apiMapper.toDto(entity);
    }

    public void deleteById(String id) {
        if (_apiRepository.existsById(id))
            _apiRepository.deleteById(id);
        else
            throw new RuntimeException("entity not found");
/*
        Api entity = _repository.findById(id).orElseThrow(() -> new RuntimeException("entity not found"));
        _repository.save(entity);
*/
    }


    //EndpoinServices

    public List<ApiEndpointOutputDto> insertEndpoint(String id, ApiEndpointInputDto dto) {
        Api apiEntity = _apiRepository.findById(id).orElseThrow(() -> new RuntimeException("entity not found"));

        if (apiEntity.getEndpoints() == null) {
            apiEntity.setEndpoints(new ArrayList<>());
        }

        List<ApiEndpoint> endpoints = apiEntity.getEndpoints();
        endpoints.stream()
                .filter(e -> e.getName().equals(dto.getName())).
                findFirst()
                .ifPresentOrElse(
                        e -> {
                            throw new RuntimeException(e.getName() + " endpoint already exists");
                        },
                        () -> endpoints.add(_apiEndpointMapper.toEntity(dto)));

        apiEntity = _apiRepository.save(apiEntity);
        return _apiEndpointMapper.toDtoList(apiEntity.getEndpoints());
    }


    public List<ApiEndpointOutputDto> getAllEndpoints(String ApiId) {
        Api apiEntity = _apiRepository.findById(ApiId).orElseThrow(() -> new RuntimeException("entity not found"));
        return _apiEndpointMapper.toDtoList(apiEntity.getEndpoints());
    }


    public ApiEndpointOutputDto getEndpointById(String ApiId, String apiEndpointId) {
        Api apiEntity = _apiRepository.findById(ApiId).orElseThrow(() -> new RuntimeException("entity not found"));

        List<ApiEndpoint> endpoints = apiEntity.getEndpoints();
        ApiEndpoint endpoint = endpoints.stream()
                .filter(e -> e.getId().equals(apiEndpointId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("endpoint not found"));

        return _apiEndpointMapper.toDto(endpoint);
    }


    public List<ApiEndpointOutputDto> updateEndpoint(String apiId, String endpointId, ApiEndpointInputDto endpointDto) {
        Api apiEntity = _apiRepository.findById(apiId).orElseThrow(() -> new RuntimeException("entity not found"));
        ApiEndpoint apiEndpointEntity = _apiEndpointRepository.findById(endpointId).orElseThrow(() -> new RuntimeException("endpoint not found"));

        List<ApiEndpoint> endpoints = apiEntity.getEndpoints();
        endpoints.stream().filter(e -> e.getId().equals(endpointId)).findFirst().ifPresentOrElse(
                e -> _apiEndpointMapper.updateEntityFromDto(endpointDto, e),
                () -> {
                    throw new RuntimeException(endpointId + " endpoint not found");
                }
        );

        apiEntity = _apiRepository.save(apiEntity);

        return _apiEndpointMapper.toDtoList(apiEntity.getEndpoints());

    }

    public List<ApiEndpointOutputDto> upsertEndpoint(String apiId, ApiEndpointInputDto dto) {
        Api apiEntity = _apiRepository.findById(apiId).orElseThrow(() -> new RuntimeException("entity not found"));

        if (apiEntity.getEndpoints() == null) {
            apiEntity.setEndpoints(new ArrayList<>());
        }

        List<ApiEndpoint> endpoints = apiEntity.getEndpoints();
        endpoints.stream()
                .filter(e -> e.getName().equals(dto.getName())).
                findFirst()
                .ifPresentOrElse(
                        e -> BeanUtils.copyProperties(dto, e),
                        () -> endpoints.add(_apiEndpointMapper.toEntity(dto)));

        apiEntity = _apiRepository.save(apiEntity);
        return _apiEndpointMapper.toDtoList(apiEntity.getEndpoints());
    }

    //delete ?

    public List<ApiEndpointOutputDto> deleteEndpoint(String apiId, String endpointId) {
        Api apiEntity = _apiRepository.findById(apiId).orElseThrow(() -> new RuntimeException("entity not found"));

        boolean removed = apiEntity.getEndpoints().removeIf(e -> e.getId().equals(endpointId));

        if(!removed){
            throw new RuntimeException(endpointId + " endpoint not found");
        }

        apiEntity = _apiRepository.save(apiEntity);
        return _apiEndpointMapper.toDtoList(apiEntity.getEndpoints());
    }



}
