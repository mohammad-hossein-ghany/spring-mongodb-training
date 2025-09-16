package com.vasl.samp.api.facade.mapper;

import com.vasl.samp.api.dto.ConsumerPackageCountOutputDto;
import com.vasl.samp.dal.entity.ConsumerPackageCount;
import com.vasl.samp.service.model.ConsumerPackageCountOutputModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsumerPackageCountMapper {
    ConsumerPackageCountOutputDto modelToDto(ConsumerPackageCountOutputModel model);

    ConsumerPackageCountOutputModel entityToModel(ConsumerPackageCount entity);

}
