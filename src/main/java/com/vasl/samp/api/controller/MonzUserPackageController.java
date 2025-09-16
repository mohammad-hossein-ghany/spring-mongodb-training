package com.vasl.samp.api.controller;


import com.vasl.samp.api.dto.ConsumerPackageCountOutputDto;
import com.vasl.samp.api.dto.MonzUserPackageOutputDto;
import com.vasl.samp.api.dto.PurchasedInputDto;
import com.vasl.samp.api.facade.MonzUserPackageFacade;
import com.vasl.samp.dal.entity.findMonzeUserPackagesByNameAndTitleResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v3/monzUserPackage")
@RestController
public class MonzUserPackageController {
    private final MonzUserPackageFacade monzUserPackageFacade;

    @PostMapping()
    public MonzUserPackageOutputDto buyPackage(@RequestBody PurchasedInputDto purchasedInputDto) {
        return monzUserPackageFacade.createMonzUserPackage(purchasedInputDto);
    }

    @GetMapping()
    public List<findMonzeUserPackagesByNameAndTitleResult> getMonzUserPackages(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "") String title) {

        return monzUserPackageFacade.getMonzUserPackages(name, title);
    }


    @GetMapping("/consumer-package-count")
    public ConsumerPackageCountOutputDto ConsumerPackageCounter(@RequestParam String username){
        return monzUserPackageFacade.consumerPackageCounter(username);
    }

}
