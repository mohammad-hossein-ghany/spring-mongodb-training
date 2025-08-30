package com.vasl.samp.service;

import com.vasl.samp.api.facade.mapper.MonzUserPackageMapper;
import com.vasl.samp.dal.entity.MonzPackage;
import com.vasl.samp.dal.entity.MonzPackagePlan;
import com.vasl.samp.dal.entity.MonzUserPackage;
import com.vasl.samp.dal.repository.MonzRepository;
import com.vasl.samp.dal.repository.MonzUserPackageRepository;
import com.vasl.samp.service.model.MonzUserPackageOutputModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class MonzUserPackageServiceImpl implements MonzUserPackageService {
    private final MonzUserPackageRepository monzUserPackageRepository;
    private final MonzRepository monzRepository;
    private final MonzUserPackageMapper monzUserPackageMapper;

    @Override
    public MonzUserPackageOutputModel createMonzUserPackage(String packageId, String planId, String username, String userId) {

        MonzPackage monzPackage = monzRepository.findById(packageId).orElseThrow(() -> new RuntimeException("Package not found"));
        String purchasedById = UUID.randomUUID().toString().replace("-", "");

        if (monzPackage.getPlans() == null) {
            throw new RuntimeException("Plans not found");
        }

        List<MonzPackagePlan> monzPackagePlans = monzPackage.getPlans();

        MonzPackagePlan monzPackagePlan = monzPackagePlans.stream()
                .filter(plan -> plan.getId().equals(planId))
                .findFirst()
                .orElseThrow(
                        () -> new RuntimeException("Plan not found")
                );


        MonzUserPackage monzUserPackageEntity = new MonzUserPackage();

        monzUserPackageEntity.setPackageId(packageId);
        monzUserPackageEntity.setPlanId(planId);
        monzUserPackageEntity.setProviderId(monzPackage.getUserId());
        monzUserPackageEntity.setProviderUsername(monzPackagePlan.getName());
        monzUserPackageEntity.setUserId(userId);
        monzUserPackageEntity.setUsername(username);
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
}
