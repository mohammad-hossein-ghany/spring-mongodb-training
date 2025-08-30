package com.vasl.samp.api.controller;


import com.vasl.samp.api.dto.MonzUserPackageOutputDto;
import com.vasl.samp.api.dto.PurchasedInputDto;
import com.vasl.samp.api.facade.MonzUserPackageFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v3/monzUserPackage")
@RestController
public class MonzUserPackageController {
    private final MonzUserPackageFacade monzUserPackageFacade;

    @PostMapping()
    public MonzUserPackageOutputDto buyPackage(@RequestBody PurchasedInputDto purchasedInputDto) {
        return monzUserPackageFacade.createMonzUserPackage(purchasedInputDto);
    }

}
