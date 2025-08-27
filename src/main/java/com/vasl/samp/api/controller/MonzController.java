package com.vasl.samp.api.controller;

import com.vasl.samp.api.dto.*;
import com.vasl.samp.api.facade.ApiFacade;
import com.vasl.samp.api.facade.MonzFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/monotize")
@RestController
public class MonzController {
    private final MonzFacade monzFacade;

    /*---------------------------------------->>[MonzPackage-Controller]<<----------------------------------------*/

    @PostMapping("/createMonz")
    public MonzPackageOutputDto createMonzPackage(@RequestBody MonzPackageInputDto monzPackageInputDto) {
        return monzFacade.CreateMonzPackage(monzPackageInputDto);
    }




    /*---------------------------------------->>[MonzPackageApi-Controller]<<----------------------------------------*/

    @PostMapping("/packageapi/insert/{MonzPackageId}")
    public List<MonzPackageApiOutputDto> addMonzPackageApi(@PathVariable String MonzPackageId, @RequestBody MonzPackageApiInputDto monzPackageApiInputDto) {
        return monzFacade.addMonzPackageApi(MonzPackageId, monzPackageApiInputDto);
    }


    /*---------------------------------------->>[MonzPackageApi-Controller]<<----------------------------------------*/

    public List<MonzPackagePlanOutputDto> addMonzPackagePlan(String monzPackageId, MonzPackagePlanInputDto monzPackagePlanInputDto) {
        return monzFacade.addMonzPackagePlan(monzPackageId , monzPackagePlanInputDto);
    }


}
