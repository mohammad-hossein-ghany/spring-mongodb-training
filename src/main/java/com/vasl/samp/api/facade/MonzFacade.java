package com.vasl.samp.api.facade;


import com.vasl.samp.api.dto.*;
import com.vasl.samp.api.facade.mapper.MonzPackageApiMapper;
import com.vasl.samp.api.facade.mapper.MonzPackageMapper;
import com.vasl.samp.api.facade.mapper.MonzPackagePlanMapper;
import com.vasl.samp.service.MonzService;
import com.vasl.samp.service.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class MonzFacade {
    private final MonzService monzService;
    private final MonzPackageMapper monzPackageMapper;
    private final MonzPackageApiMapper monzPackageApiMapper;
    private final MonzPackagePlanMapper monzPackagePlanMapper;

    /*---------------------------------------->>[MonzPackage-Facade]<<----------------------------------------*/

    public MonzPackageOutputDto CreateMonzPackage(MonzPackageInputDto monzPackageInputDto) {
        MonzPackageInputModel monzPackageInputModel = monzPackageMapper.toModel(monzPackageInputDto);
        MonzPackageOutputModel monzPackageOutputModel = monzService.createMonzPackage(monzPackageInputModel);
        return monzPackageMapper.toDto(monzPackageOutputModel);
    }



    /*---------------------------------------->>[MonzPackageApi-Facade]<<----------------------------------------*/

    public List<MonzPackageApiOutputDto> addMonzPackageApi(String MonzPackageId, MonzPackageApiInputDto monzPackageApiInputDto) {
        MonzPackageApiInputModel monzPackageApiInputModel = monzPackageApiMapper.toModel(monzPackageApiInputDto);
        List<MonzPackageApiOutputModel> monzPackageApiOutputModels = monzService.addMonzPackageApi(MonzPackageId, monzPackageApiInputModel);
        return monzPackageApiMapper.modelToDto(monzPackageApiOutputModels);

    }

    public List<MonzPackagePlanOutputDto> addMonzPackagePlan(String monzPackageId, MonzPackagePlanInputDto monzPackagePlanInputDto) {
        MonzPackagePlanInputModel monzPackagePlanInputModel = monzPackagePlanMapper.toModel(monzPackagePlanInputDto);
        List<MonzPackagePlanOutputModel> monzPackagePlanOutputModel = monzService.addMonzPackagePlan(monzPackageId, monzPackagePlanInputModel);
        return monzPackagePlanMapper.modelToDto(monzPackagePlanOutputModel);
    }
}