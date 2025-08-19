package com.vasl.samp.api.controller;


import com.vasl.samp.api.dto.ApiEndpointInputDto;
import com.vasl.samp.api.dto.ApiEndpointOutputDto;
import com.vasl.samp.api.dto.ApiInputDto;
import com.vasl.samp.api.dto.ApiOutputDto;
import com.vasl.samp.api.facade.ApiFacade;
import com.vasl.samp.service.model.ApiEndpointOutputModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/apim")
@RestController
public class ApiController {
    private final ApiFacade apiFacade;

    @GetMapping("/api/get-all")
    public List<ApiOutputDto> getAllApi() {
        return apiFacade.getAllApi();
    }

    @GetMapping("/api/get-by-id/{id}")
    public ApiOutputDto getApiById(@PathVariable String id) {
        return apiFacade.getApiById(id);
    }

    @PostMapping("/api/create")
    public ApiOutputDto createApi(@RequestBody ApiInputDto apiInputDto) {
        return apiFacade.createApi(apiInputDto);
    }

    @PutMapping("/api/update/{id}")
    public ApiOutputDto updateApi(@RequestBody ApiInputDto apiInputDto, @PathVariable String id) {
        return apiFacade.updateApi(apiInputDto, id);
    }


    @DeleteMapping("/api/delete/{id}")
    public List<ApiOutputDto> deleteApiById(@PathVariable String id) {
        return apiFacade.deleteApiById(id);
    }


    @PostMapping("/endpoint/insert/{apiId}")
    public List<ApiEndpointOutputDto> insertEndpoint(@PathVariable String apiId, @RequestBody ApiEndpointInputDto dto) {
        return apiFacade.insertEndpoint(apiId, dto);
    }


    @GetMapping("/endpoint/get-all/{apiId}")
    public List<ApiEndpointOutputDto> getAllEndpoints(@PathVariable String apiId) {
        return apiFacade.getAllEndpoints(apiId);
    }

    @GetMapping("/endpoint/get-by-id/{apiId}/{endpointId}")
    public ApiEndpointOutputDto getEndpointById(@PathVariable String apiId, @PathVariable String endpointId) {
        return apiFacade.getEndpointById(apiId, endpointId);
    }

    // delete upsert update

    @PatchMapping("/endpoint/update/{apiId}/{endpointId}")
    public List<ApiEndpointOutputDto> updateEndpoint(@PathVariable String apiId, @PathVariable String endpointId, @RequestBody ApiEndpointInputDto dto) {
        return apiFacade.updateEndpoint(apiId, endpointId, dto);
    }

    @DeleteMapping("/endpoint/delete/{apiId}/{endpointId}")
    public List<ApiEndpointOutputDto> deleteEndpoint(@PathVariable String apiId, @PathVariable String endpointId) {
        return apiFacade.deleteEndpoint(apiId, endpointId);
    }


    @PostMapping("/endpoint/upsert/{apiId}")
    public List<ApiEndpointOutputDto> upsertEndpoint(@PathVariable String apiId,@RequestBody ApiEndpointInputDto dto) {
        return apiFacade.upsertEndpoint(apiId, dto);
    }


}


















