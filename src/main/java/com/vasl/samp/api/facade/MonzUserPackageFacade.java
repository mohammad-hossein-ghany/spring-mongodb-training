package com.vasl.samp.api.facade;


import com.vasl.samp.api.dto.MonzUserPackageOutputDto;
import com.vasl.samp.api.dto.PurchasedInputDto;
import com.vasl.samp.api.facade.mapper.MonzPackageMapper;
import com.vasl.samp.api.facade.mapper.MonzUserPackageMapper;
import com.vasl.samp.api.facade.mapper.PurchasedMapper;
import com.vasl.samp.service.MonzUserPackageService;
import com.vasl.samp.service.model.MonzUserPackageOutputModel;
import com.vasl.samp.service.model.PurchasedInputModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MonzUserPackageFacade {
    private final MonzUserPackageService monzUserPackageService;
    private final MonzPackageMapper monzPackageMapper;
    private final MonzUserPackageMapper monzUserPackageMapper;
    private final PurchasedMapper purchasedMapper;

    public MonzUserPackageOutputDto createMonzUserPackage(PurchasedInputDto purchasedInputDto) {
        PurchasedInputModel purchasedInputModel = purchasedMapper.toModel(purchasedInputDto);
        MonzUserPackageOutputModel monzUserPackageOutputModel = monzUserPackageService.createMonzUserPackage(purchasedInputModel);
        return monzUserPackageMapper.toDto(monzUserPackageOutputModel);
    }

}
