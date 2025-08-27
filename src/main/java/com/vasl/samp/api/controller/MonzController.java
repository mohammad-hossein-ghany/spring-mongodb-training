package com.vasl.samp.api.controller;

import com.vasl.samp.api.facade.ApiFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/monotize")
@RestController
public class MonzController {
    private final ApiFacade  apiFacade;


}
