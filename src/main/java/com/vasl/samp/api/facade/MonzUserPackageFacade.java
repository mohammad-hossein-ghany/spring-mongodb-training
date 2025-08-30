package com.vasl.samp.api.facade;


import com.vasl.samp.api.dto.MonzUserPackageOutputDto;
import com.vasl.samp.api.facade.mapper.MonzPackageMapper;
import com.vasl.samp.api.facade.mapper.MonzUserPackageMapper;
import com.vasl.samp.service.MonzUserPackageService;
import com.vasl.samp.service.model.MonzUserPackageOutputModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MonzUserPackageFacade {
    private final MonzUserPackageService monzUserPackageService;
    private final MonzPackageMapper monzPackageMapper;
    private final MonzUserPackageMapper monzUserPackageMapper;

    public MonzUserPackageOutputDto createMonzUserPackage(String packageId, String planId, String username, String userId) {
        MonzUserPackageOutputModel monzUserPackageOutputModel = monzUserPackageService.createMonzUserPackage(packageId, planId, username, userId);
        return monzUserPackageMapper.toDto(monzUserPackageOutputModel);
    }

}
