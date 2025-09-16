package com.vasl.samp.api.controller;

import com.vasl.samp.api.dto.*;
import com.vasl.samp.api.facade.ApiFacade;
import com.vasl.samp.api.facade.MonzFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/monotize/package")
@RestController
public class MonzController {
    private final MonzFacade monzFacade;

    /*---------------------------------------->>[MonzPackage-Controller]<<----------------------------------------*/

    @PostMapping("/createMonz")
    public MonzPackageOutputDto createMonzPackage(@RequestBody MonzPackageInputDto monzPackageInputDto) {
        return monzFacade.CreateMonzPackage(monzPackageInputDto);
    }


    @GetMapping("/provider-package-count")
    public ProviderPackageCountOutputDto ProviderPackageCounter(@RequestParam String username){
        return monzFacade.ProviderPackageCounter(username);
    }



    /*---------------------------------------->>[MonzPackageApi-Controller]<<----------------------------------------*/

    @PostMapping("/package-api/insert/{MonzPackageId}")
    public List<MonzPackageApiOutputDto> addMonzPackageApi(@PathVariable String MonzPackageId, @RequestBody MonzPackageApiInputDto monzPackageApiInputDto) {
        return monzFacade.addMonzPackageApi(MonzPackageId, monzPackageApiInputDto);
    }


    /*---------------------------------------->>[MonzPackagePlan-Controller]<<----------------------------------------*/

    @PostMapping("/package-plan/insert/{monzPackageId}")
    public List<MonzPackagePlanOutputDto> addMonzPackagePlan( @PathVariable String monzPackageId,@RequestBody MonzPackagePlanInputDto monzPackagePlanInputDto) {
        return monzFacade.addMonzPackagePlan(monzPackageId , monzPackagePlanInputDto);
    }




}



















