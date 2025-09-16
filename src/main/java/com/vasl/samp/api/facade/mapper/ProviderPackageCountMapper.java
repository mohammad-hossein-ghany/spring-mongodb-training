package com.vasl.samp.api.facade.mapper;

import com.vasl.samp.api.dto.ProviderPackageCountOutputDto;
import com.vasl.samp.dal.entity.ProviderPackageCount;
import com.vasl.samp.service.model.ProviderPackageCountOutputModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProviderPackageCountMapper {
    ProviderPackageCountOutputDto modelToDto(ProviderPackageCountOutputModel model);
    ProviderPackageCountOutputModel entityToModel(ProviderPackageCount entity);
}
