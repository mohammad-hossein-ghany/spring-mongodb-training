package com.vasl.samp.api.facade.mapper;


import com.vasl.samp.api.dto.ApiInputDto;
import com.vasl.samp.api.dto.ApiOutputDto;
import com.vasl.samp.api.dto.MonzUserPackageInputDto;
import com.vasl.samp.api.dto.MonzUserPackageOutputDto;
import com.vasl.samp.dal.entity.Api;
import com.vasl.samp.dal.entity.MonzUserPackage;
import com.vasl.samp.service.model.ApiInputModel;
import com.vasl.samp.service.model.ApiOutputModel;
import com.vasl.samp.service.model.MonzUserPackageInputModel;
import com.vasl.samp.service.model.MonzUserPackageOutputModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MonzUserPackageMapper {
    //Input (MonzUserPackageInputDto -> MonzUserPackageInputDtoInputModel -> MonzUserPackageInputDto)
    MonzUserPackageInputModel toModel(MonzUserPackageInputDto dto);
    MonzUserPackage toEntity(MonzUserPackageInputModel inputModel);
    List<MonzUserPackageInputModel> dtoToModel(List<MonzUserPackageInputDto> dto);
    List<MonzUserPackage> modelToEntity(List<MonzUserPackageInputModel> inputModel);


    //Output (MonzUserPackage -> MonzUserPackageOutputModel -> MonzUserPackageOutputDto)
    MonzUserPackageOutputModel toModel(MonzUserPackage entity);
    MonzUserPackageOutputDto toDto(MonzUserPackageOutputModel model);
    List<MonzUserPackageOutputModel>  entityToModel(List<MonzUserPackage> entities);
    List<MonzUserPackageOutputDto>  modelToDto(List<MonzUserPackageOutputModel> model);



    //entity <-> dto
    MonzUserPackageOutputDto toDto(MonzUserPackage entity);
    MonzUserPackage toEntity(MonzUserPackageInputDto dto);
    List<MonzUserPackageOutputDto> toDto(List<MonzUserPackage> entities);
    List<MonzUserPackage> toEntity(List<MonzUserPackageInputModel> dtos);


    //update
    void updateEntity(MonzUserPackageInputModel model, @MappingTarget MonzUserPackage entity);
}
