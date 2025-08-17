package com.vasl.samp.api.controller;


import com.vasl.samp.api.dto.ApiEndpointInputDto;
import com.vasl.samp.api.dto.ApiEndpointOutputDto;
import com.vasl.samp.api.dto.ApiInputDto;
import com.vasl.samp.api.dto.ApiOutputDto;
import com.vasl.samp.api.facade.ApiFacade;
import com.vasl.samp.service.ApiServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/apim")
@RestController
public class ApiController {
    private final ApiServiceImpl _apiServiceImpl;
    private final ApiFacade apiFacade;

    @GetMapping("/api/get-all")
    public List<ApiOutputDto> getAllApi() {
        return _apiServiceImpl.getAll();
    }

    @GetMapping("/api/get-by-id/{id}")
    public ApiOutputDto getApiById(@PathVariable String id) {
        return _apiServiceImpl.getById(id);
    }

    @PostMapping("/api/create")
    public ApiOutputDto createApi(@RequestBody ApiInputDto apiInputDto) {
        return _apiServiceImpl.create(apiInputDto);
    }

    @PutMapping("/api/update/{id}")
    public ApiOutputDto updateApi(@RequestBody ApiInputDto apiInputDto, @PathVariable String id) {
        return _apiServiceImpl.update(id, apiInputDto);
    }


    @DeleteMapping("/api/delete/{id}")
    public List<ApiOutputDto> deleteApiById(@PathVariable String id) {
        return _apiServiceImpl.deleteById(id);
    }


    @PostMapping("/endpoint/insert/{apiId}")
    public List<ApiEndpointOutputDto> insertEndpoint(@PathVariable String apiId, @RequestBody ApiEndpointInputDto dto) {
        return apiFacade.insertEndpoint(apiId, dto);
    }


    @GetMapping("/endpoint/get-all/{apiId}")
    public List<ApiEndpointOutputDto> getAllEndpoints(@PathVariable String apiId) {
        return _apiServiceImpl.getAllEndpoints(apiId);
    }

    @GetMapping("/endpoint/get-by-id/{apiId}/{endpointId}")
    public ApiEndpointOutputDto getEndpointById(@PathVariable String apiId, @PathVariable String endpointId) {
        return _apiServiceImpl.getEndpointById(apiId, endpointId);
    }


}
