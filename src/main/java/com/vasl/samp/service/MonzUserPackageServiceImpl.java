package com.vasl.samp.service;

import com.vasl.samp.api.dto.ProviderPackageCountOutputDto;
import com.vasl.samp.api.facade.mapper.ConsumerPackageCountMapper;
import com.vasl.samp.api.facade.mapper.MonzUserPackageMapper;
import com.vasl.samp.dal.entity.*;
import com.vasl.samp.dal.repository.MonzRepository;
import com.vasl.samp.dal.repository.MonzUserPackageRepository;
import com.vasl.samp.service.model.ConsumerPackageCountOutputModel;
import com.vasl.samp.service.model.MonzUserPackageOutputModel;
import com.vasl.samp.service.model.PurchasedInputModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class MonzUserPackageServiceImpl implements MonzUserPackageService {
    private final MonzUserPackageRepository monzUserPackageRepository;
    private final MonzRepository monzRepository;
    private final MonzUserPackageMapper monzUserPackageMapper;
    private final ConsumerPackageCountMapper consumerPackageCountMapper;

    @Override
    public MonzUserPackageOutputModel createMonzUserPackage(PurchasedInputModel purchasedInputModel) {

        MonzPackage monzPackage = monzRepository.findById(purchasedInputModel.getPackageId()).orElseThrow(() -> new RuntimeException("Package not found"));
        String purchasedById = UUID.randomUUID().toString().replace("-", "");

        if (monzPackage.getPlans() == null) {
            throw new RuntimeException("Plans not found");
        }

        List<MonzPackagePlan> monzPackagePlans = monzPackage.getPlans();

        MonzPackagePlan monzPackagePlan = monzPackagePlans.stream()
                .filter(plan -> plan.getId().equals(purchasedInputModel.getPlanId()))
                .findFirst()
                .orElseThrow(
                        () -> new RuntimeException("Plan not found")
                );


        MonzUserPackage monzUserPackageEntity = new MonzUserPackage();

        monzUserPackageEntity.setPackageId(purchasedInputModel.getPackageId());
        monzUserPackageEntity.setPlanId(purchasedInputModel.getPlanId());
        monzUserPackageEntity.setProviderId(monzPackage.getUserId());
        monzUserPackageEntity.setProviderUsername(monzPackagePlan.getName());
        monzUserPackageEntity.setUserId(purchasedInputModel.getUserId());
        monzUserPackageEntity.setUsername(purchasedInputModel.getUsername());
        monzUserPackageEntity.setStartUsageTime(new Date().getTime());
        monzUserPackageEntity.setExpireTime(LocalDateTime.now().plusMonths(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
//      monzUserPackageEntity.setExpireTime(Instant.now().plus(1, ChronoUnit.MONTHS).toEpochMilli());
        monzUserPackageEntity.setActive(true);
        monzUserPackageEntity.setDurationDays(monzPackagePlan.getDurationDays());
        monzUserPackageEntity.setExpired(false);
        monzUserPackageEntity.setPackageName(monzPackage.getName());
        monzUserPackageEntity.setPackageTitle(monzPackage.getTitle());
        monzUserPackageEntity.setPlanName(monzPackagePlan.getName());
        monzUserPackageEntity.setPlanTitle(monzPackagePlan.getTitle());
        monzUserPackageEntity.setPurchasedBy(purchasedById);
        monzUserPackageEntity.setAutoPurchasable(true);
        monzUserPackageEntity.setExpireNotified(false);

        MonzUserPackage newMonzUserPackage = monzUserPackageRepository.save(monzUserPackageEntity);
        return monzUserPackageMapper.toModel(newMonzUserPackage);
    }

    @Override
    public List<findMonzeUserPackagesByNameAndTitleResult> getMonzUserPackages(String name, String title) {
        return monzUserPackageRepository.findMonzeUserPackagesByNameAndTitle(name, title);

    }

    @Override
    public ConsumerPackageCountOutputModel consumerPackageCounter(String username) {
        ConsumerPackageCount entity = monzUserPackageRepository.consumerPackageCounter(username);
        return consumerPackageCountMapper.entityToModel(entity);
    }


}



















