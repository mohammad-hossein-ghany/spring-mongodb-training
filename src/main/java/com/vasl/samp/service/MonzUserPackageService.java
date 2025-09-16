package com.vasl.samp.service;

import com.vasl.samp.api.dto.ProviderPackageCountOutputDto;
import com.vasl.samp.dal.entity.ConsumerPackageCount;
import com.vasl.samp.dal.entity.findMonzeUserPackagesByNameAndTitleResult;
import com.vasl.samp.service.model.ConsumerPackageCountOutputModel;
import com.vasl.samp.service.model.MonzUserPackageOutputModel;
import com.vasl.samp.service.model.PurchasedInputModel;

import java.util.List;

public interface MonzUserPackageService {
    MonzUserPackageOutputModel createMonzUserPackage(PurchasedInputModel purchasedInputModel);

    List<findMonzeUserPackagesByNameAndTitleResult> getMonzUserPackages(String name, String title);

    ConsumerPackageCountOutputModel consumerPackageCounter(String username);
}

