package com.vasl.samp.api.facade.mapper;


import com.vasl.samp.api.dto.MonzPackagePlanInputDto;
import com.vasl.samp.api.dto.MonzPackagePlanOutputDto;
import com.vasl.samp.dal.entity.MonzPackagePlan;
import com.vasl.samp.service.model.MonzPackagePlanInputModel;
import com.vasl.samp.service.model.MonzPackagePlanOutputModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "spring")
public interface MonzPackagePlanMapper {
    //Input (MonzPackagePlanInputDto -> MonzPackagePlanInputModel -> MonzPackagePlan)
    MonzPackagePlanInputModel toModel(MonzPackagePlanInputDto dto);
    MonzPackagePlan toEntity(MonzPackagePlanInputModel inputModel);
    List<MonzPackagePlanInputModel> dtoToModel(List<MonzPackagePlanInputDto> dto);
    List<MonzPackagePlan> modelToEntity(List<MonzPackagePlanInputModel> inputModel);


    //Output (MonzPackagePlan -> MonzPackagePlanOutputModel -> MonzPackagePlanOutputDto)
    MonzPackagePlanOutputModel toModel(MonzPackagePlan entity);
    MonzPackagePlanOutputDto toDto(MonzPackagePlanOutputModel model);
    List<MonzPackagePlanOutputModel>  entityToModel(List<MonzPackagePlan> entities);
    List<MonzPackagePlanOutputDto>  modelToDto(List<MonzPackagePlanOutputModel> model);



    //entity <-> dto
    MonzPackagePlanOutputDto toDto(MonzPackagePlan entity);
    MonzPackagePlan toEntity(MonzPackagePlanInputDto dto);
    List<MonzPackagePlanOutputDto> toDto(List<MonzPackagePlan> entities);
    List<MonzPackagePlan> toEntity(List<MonzPackagePlanInputModel> dtos);


    //update
    void updateEntity(MonzPackagePlanInputModel model, @MappingTarget MonzPackagePlan entity);

}
