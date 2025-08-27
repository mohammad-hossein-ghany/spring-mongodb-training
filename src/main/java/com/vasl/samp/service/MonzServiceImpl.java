package com.vasl.samp.service;


import com.vasl.samp.api.dto.MonzPackagePlanOutputDto;
import com.vasl.samp.api.facade.mapper.MonzPackageApiMapper;
import com.vasl.samp.api.facade.mapper.MonzPackageMapper;
import com.vasl.samp.api.facade.mapper.MonzPackagePlanMapper;
import com.vasl.samp.dal.entity.MonzPackage;
import com.vasl.samp.dal.entity.MonzPackageApi;
import com.vasl.samp.dal.entity.MonzPackagePlan;
import com.vasl.samp.dal.repository.MonzRepository;
import com.vasl.samp.service.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MonzServiceImpl implements MonzService {

    private final MonzRepository monzRepository;
    private final MonzPackageMapper monzPackageMapper;
    private final MonzPackageApiMapper monzPackageApiMapper;
    private final MonzPackagePlanMapper monzPackagePlanMapper;


    /*---------------------------------------->>[MonzPackage-Services]<<----------------------------------------*/

    @Override
    public MonzPackageOutputModel createMonzPackage(MonzPackageInputModel model) {
        MonzPackage entity = monzPackageMapper.toEntity(model);
        entity = monzRepository.save(entity);
        return monzPackageMapper.toModel(entity);
    }



    /*---------------------------------------->>[MonzPackageApi-Services]<<----------------------------------------*/

    @Override
    public List<MonzPackageApiOutputModel> addMonzPackageApi(String MonzPackageId, MonzPackageApiInputModel monzPackageApiInputModel) {
        MonzPackage monzPackage = monzRepository.findById(MonzPackageId).orElseThrow(() -> new RuntimeException("MonzPackage not found"));

        if (monzPackage.getApis() == null) {
            monzPackage.setApis(new ArrayList<>());
        }

        List<MonzPackageApi> monzPackageApis = monzPackage.getApis();
        MonzPackageApi monzPackageApi = monzPackageApiMapper.toEntity(monzPackageApiInputModel);
        monzPackageApi.setId(UUID.randomUUID().toString().replace("-", ""));
        monzPackageApis.add(monzPackageApi);

        monzPackage = monzRepository.save(monzPackage);

        return monzPackageApiMapper.entityToModel(monzPackage.getApis());
    }



    /*---------------------------------------->>[MonzPackagePlan-Services]<<----------------------------------------*/

    @Override
    public List<MonzPackagePlanOutputModel> addMonzPackagePlan(String monzPackageId, MonzPackagePlanInputModel monzPackagePlanInputModel) {
        MonzPackage monzPackage = monzRepository.findById(monzPackageId).orElseThrow(() -> new RuntimeException("MonzPackage not found"));

        if (monzPackage.getPlans() == null) {
            monzPackage.setPlans(new ArrayList<>());
        }

        List<MonzPackagePlan> monzPackagePlans = monzPackage.getPlans();
        MonzPackagePlan monzPackagePlan = monzPackagePlanMapper.toEntity(monzPackagePlanInputModel);
        monzPackagePlan.setId(UUID.randomUUID().toString().replace("-", ""));
        monzPackagePlans.add(monzPackagePlan);

        monzPackage = monzRepository.save(monzPackage);

        return monzPackagePlanMapper.entityToModel(monzPackage.getPlans());
    }

}












