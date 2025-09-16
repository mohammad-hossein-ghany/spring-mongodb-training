package com.vasl.samp.api.facade;


import com.vasl.samp.api.dto.ConsumerPackageCountOutputDto;
import com.vasl.samp.api.dto.MonzUserPackageOutputDto;
import com.vasl.samp.api.dto.PurchasedInputDto;
import com.vasl.samp.api.facade.mapper.ConsumerPackageCountMapper;
import com.vasl.samp.api.facade.mapper.MonzPackageMapper;
import com.vasl.samp.api.facade.mapper.MonzUserPackageMapper;
import com.vasl.samp.api.facade.mapper.PurchasedMapper;
import com.vasl.samp.dal.entity.findMonzeUserPackagesByNameAndTitleResult;
import com.vasl.samp.service.MonzUserPackageService;
import com.vasl.samp.service.model.ConsumerPackageCountOutputModel;
import com.vasl.samp.service.model.MonzUserPackageOutputModel;
import com.vasl.samp.service.model.PurchasedInputModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class MonzUserPackageFacade {
    private final MonzUserPackageService monzUserPackageService;
    private final MonzPackageMapper monzPackageMapper;
    private final MonzUserPackageMapper monzUserPackageMapper;
    private final PurchasedMapper purchasedMapper;
    private final ConsumerPackageCountMapper consumerPackageCountMapper;

    public MonzUserPackageOutputDto createMonzUserPackage(PurchasedInputDto purchasedInputDto) {
        PurchasedInputModel purchasedInputModel = purchasedMapper.toModel(purchasedInputDto);
        MonzUserPackageOutputModel monzUserPackageOutputModel = monzUserPackageService.createMonzUserPackage(purchasedInputModel);
        return monzUserPackageMapper.toDto(monzUserPackageOutputModel);
    }

    public List<findMonzeUserPackagesByNameAndTitleResult> getMonzUserPackages(String name, String title) {
        return monzUserPackageService.getMonzUserPackages(name,title);
//        return monzUserPackageMapper.modelToDto(monzUserPackageOutputModels);
    }

    public ConsumerPackageCountOutputDto consumerPackageCounter(String username) {
        ConsumerPackageCountOutputModel consumerPackageCountOutputModel = monzUserPackageService.consumerPackageCounter(username);
        return consumerPackageCountMapper.modelToDto(consumerPackageCountOutputModel);
    }
}
