package com.vasl.samp.api.controller;


import com.vasl.samp.api.dto.MonzUserPackageOutputDto;
import com.vasl.samp.api.dto.purchasedInputDto;
import com.vasl.samp.api.facade.MonzUserPackageFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/monotize/User")
@RestController
public class MonzUserPackageController {
    private final MonzUserPackageFacade monzUserPackageFacade;

    @PostMapping("/create")
    public MonzUserPackageOutputDto createMonzUserPackage(@RequestBody purchasedInputDto purchasedInputDto) {
        return monzUserPackageFacade.createMonzUserPackage(purchasedInputDto.getPackageId(), purchasedInputDto.getPlanId(), purchasedInputDto.getUsername(), purchasedInputDto.getUserId());
    }


}
