package com.vasl.samp.service;

import com.vasl.samp.api.dto.MonzPackagePlanOutputDto;
import com.vasl.samp.service.model.*;

import java.util.List;

public interface MonzService {
    MonzPackageOutputModel createMonzPackage(MonzPackageInputModel model);
    List<MonzPackageApiOutputModel> addMonzPackageApi(String MonzPackageId, MonzPackageApiInputModel monzPackageApiInputModel);
    List<MonzPackagePlanOutputModel> addMonzPackagePlan(String monzPackageId, MonzPackagePlanInputModel monzPackagePlanInputModel);
}
