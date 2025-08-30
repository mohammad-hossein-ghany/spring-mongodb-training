package com.vasl.samp.service;

import com.vasl.samp.service.model.MonzUserPackageOutputModel;
import com.vasl.samp.service.model.PurchasedInputModel;

public interface MonzUserPackageService {
    MonzUserPackageOutputModel createMonzUserPackage(PurchasedInputModel purchasedInputModel);
}
