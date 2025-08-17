package com.vasl.samp.api.controller;

import com.vasl.samp.dal.entity.Api;
import com.vasl.samp.dal.entity.ApiEndpoint;
import com.vasl.samp.dal.repository.ApiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/api/apim")
@RestController
public class OldApiController {


    private final ApiRepository apiRepository;
//    private final CustomApiRepository customApiRepository;

    //region Sample API
    @GetMapping(path = "/by-name/{name}")
    public Api getByName(@PathVariable String name) {
        return apiRepository.findByName(name).orElseThrow(() -> new RuntimeException("API Not Found"));
    }

    @GetMapping(path = "/by-name")
    public Api getByName2(@RequestParam String name) {
        return apiRepository.findByName(name).orElseThrow(() -> new RuntimeException("API Not Found"));
    }
    //endregion

    @GetMapping("/by-id/{id}")
    public Api getById(@PathVariable String id) {
        return apiRepository.findById(id).orElseThrow(() -> new RuntimeException("API Not Found"));
    }

    @GetMapping("/all")
    public Page<Api> getAll(Pageable pageable) {
        return apiRepository.findAll(pageable);
    }

    @GetMapping("/count")
    public long count() {
        return apiRepository.count();
    }


    @GetMapping("/all-by-title/{title}")
    public List<Api> getListByTitle(@PathVariable String title) {
        return apiRepository.findAllByTitle(title);
    }

    @GetMapping("/title-by-id/{id}")
    public String getTitleById(@PathVariable String id) {
        return apiRepository.findById(id).orElseThrow(() -> new RuntimeException("API Not Found")).getTitle();
    }


//
//    @GetMapping(path = "")
//    public Api getByApiAndTitle(@RequestParam String api, @RequestParam String title){
//        return null;
//    }


    @PostMapping("/api")
    public Api addApi(@RequestBody Api api) {
        return apiRepository.save(api);
    }

    @GetMapping("endpoints-by-id/{id}")
    public List<ApiEndpoint> getEndPointsById(@PathVariable String id) {
        return apiRepository.findById(id).orElseThrow(() -> new RuntimeException("API Not Found")).getEndpoints();
    }


    @PostMapping("/endpoint/{id}")
    public Api addEndpoint(@PathVariable String id, @RequestBody ApiEndpoint apiEndpoint) {
        Api api = apiRepository.findById(id).orElseThrow(() -> new RuntimeException("API Not Found"));
        api.getEndpoints().add(apiEndpoint);

        return apiRepository.save(api);
    }

/*
    @PostMapping("/endpoint/validating/{id}")
    public Api addEndpointWithenValidation(@PathVariable String id, @RequestBody ApiEndpoint apiEndpoint) {
        Api api = apiRepository.findById(id).orElseThrow(() -> new RuntimeException("API Not Found"));

        List<ApiEndpoint> endpoints = api.getEndpoints();
        endpoints.forEach(endpoint -> {
            if (endpoint.getName().equals(apiEndpoint.getName())) {
                throw new RuntimeException("Duplicated endpoint");
            }
        });

        endpoints.forEach(endpoint -> {
            if (endpoint.getName().equals(apiEndpoint.getName())) {
                customApiRepository.updateApiEndpoint(api, endpoint.getId(), apiEndpoint);
            }
        });


        api.getEndpoints().add(apiEndpoint);
        return apiRepository.save(api);
    }
*/


    //upsert ==> update + insert
    @PostMapping("/endpoint/validate/{id}")
    public Api updateEndpoint(@PathVariable String id, @RequestBody ApiEndpoint apiEndpoint) {

        Api api = apiRepository.findById(id).orElseThrow(() -> new RuntimeException("API Not Found"));


        if (api.getEndpoints() == null) {
            api.setEndpoints(new ArrayList<>());
        }

        List<ApiEndpoint> endpoints = api.getEndpoints();
        endpoints.stream().filter(e -> e.getName().equals(apiEndpoint.getName())).findFirst()
                .ifPresentOrElse(e -> BeanUtils.copyProperties(apiEndpoint, e), () -> endpoints.add(apiEndpoint));

        //region Commented Code
        /*
               endpoints.stream().filter(e -> e.getName().equals(apiEndpoint.getName()))
                .forEach(e -> BeanUtils.copyProperties(apiEndpoint, e));
         */


        /*
        endpoints.stream().filter(e -> e.getName().equals(apiEndpoint.getName()))
                .findFirst().ifp
                .ifPresent(e -> BeanUtils.copyProperties(apiEndpoint, e));

                .forEach(e -> BeanUtils.copyProperties(apiEndpoint, e));
        */


        /*        endpoints.forEach(endpoint -> {
            if(endpoint.getName().equals(apiEndpoint.getName())) {

            }
        });
        */
        //endregion
        return apiRepository.save(api);
    }


    @GetMapping("/endpoint/validate5/{id}")
    public void updatingEndpoint(@PathVariable String id, @RequestBody ApiEndpoint apiEndpoint) {
        Api api = apiRepository.findById(id).orElseThrow(() -> new RuntimeException("API Not Found"));
        List<ApiEndpoint> endpoints = api.getEndpoints();

        endpoints.stream().filter(e -> e.getName().equals(apiEndpoint.getName())).findFirst()
                .ifPresentOrElse(
                        (endpoint) -> {
                            throw new RuntimeException("duplicated endpoint");
                        }
                        ,
                        () -> {
                            throw new RuntimeException("duplicated endpoint");
                        }
                );
    }






/*
//    @PatchMapping
    public ResponseEntity<Api> updateEndpoint(@PathVariable String id, @RequestBody Api api) {




        return null;
    }
*/


}
