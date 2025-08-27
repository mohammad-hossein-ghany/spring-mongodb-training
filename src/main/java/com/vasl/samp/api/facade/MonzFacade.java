package com.vasl.samp.api.facade;

import com.vasl.samp.api.facade.mapper.MonzPackageApiMapper;
import com.vasl.samp.api.facade.mapper.MonzPackageMapper;
import com.vasl.samp.api.facade.mapper.MonzPackagePlanMapper;
import com.vasl.samp.service.MonzService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MonzFacade {
    private final MonzService monzService;
    private final MonzPackageMapper monzPackageMapper;
    private final MonzPackageApiMapper monzPackageApiMapper;
    private final MonzPackagePlanMapper  monzPackagePlanMapper;

}
