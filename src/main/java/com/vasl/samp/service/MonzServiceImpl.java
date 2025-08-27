package com.vasl.samp.service;


import com.vasl.samp.api.facade.mapper.ApiMapper;
import com.vasl.samp.api.facade.mapper.MonzPackageMapper;
import com.vasl.samp.dal.repository.ApiRepository;
import com.vasl.samp.dal.repository.MonzRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonzServiceImpl implements MonzService {

    private final MonzRepository monzRepository;
    private final MonzPackageMapper monzMapper;
}












