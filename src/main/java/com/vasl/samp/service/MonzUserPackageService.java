package com.vasl.samp.service;

import com.vasl.samp.service.model.MonzUserPackageOutputModel;

public interface MonzUserPackageService {
    MonzUserPackageOutputModel createMonzUserPackage(String packageId, String planId, String username, String userId);
}
