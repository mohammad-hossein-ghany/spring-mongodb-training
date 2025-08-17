package com.vasl.samp.api.controller;


import com.vasl.samp.api.dto.ApiInputDto;
import com.vasl.samp.api.dto.ApiOutputDto;
import com.vasl.samp.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/apim")
@RestController
public class ApiController {
    private final ApiService _apiService;

    @GetMapping("/api/get-all")
    public List<ApiOutputDto> getAllApi() {
        return _apiService.getAll();
    }

    @GetMapping("/api/get-by-id/{id}")
    public ApiOutputDto getApiById(@PathVariable String id) {
        return _apiService.getById(id);
    }

    @PostMapping("/api/create")
    public ApiOutputDto createApi(@RequestBody ApiInputDto apiInputDto) {
        return _apiService.create(apiInputDto);
    }

    @PutMapping("/api/update/{id}")
    public ApiOutputDto updateApi(@RequestBody ApiInputDto apiInputDto ,@PathVariable String id) {
        return _apiService.update(id , apiInputDto);
    }


    @DeleteMapping("/api/delete/{id}")
    public List<ApiOutputDto> deleteApiById(@PathVariable String id) {
        return _apiService.deleteById(id);
    }

}
