package com.vasl.samp.api.facade.mapper;

import com.vasl.samp.api.dto.ApiInputDto;
import com.vasl.samp.api.dto.ApiOutputDto;
import com.vasl.samp.api.dto.MonzPackageInputDto;
import com.vasl.samp.api.dto.MonzPackageOutputDto;
import com.vasl.samp.dal.entity.Api;
import com.vasl.samp.dal.entity.MonzPackage;
import com.vasl.samp.service.model.ApiInputModel;
import com.vasl.samp.service.model.ApiOutputModel;
import com.vasl.samp.service.model.MonzPackageInputModel;
import com.vasl.samp.service.model.MonzPackageOutputModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "spring", uses = {MonzPackageApiMapper.class , MonzPackagePlanMapper.class})
public interface MonzPackageMapper {
    //Input (MonzPackageInputDto -> MonzPackageInputModel -> MonzPackage)
    MonzPackageInputModel toModel(MonzPackageInputDto dto);
    MonzPackage toEntity(MonzPackageInputModel inputModel);
    List<MonzPackageInputModel> dtoToModel(List<MonzPackageInputDto> dto);
    List<MonzPackage> modelToEntity(List<MonzPackageInputModel> inputModel);


    //Output (MonzPackage -> MonzPackageOutputModel -> MonzPackageOutputDto)
    MonzPackageOutputModel toModel(MonzPackage entity);
    MonzPackageOutputDto toDto(MonzPackageOutputModel model);
    List<MonzPackageOutputModel>  entityToModel(List<MonzPackage> entities);
    List<MonzPackageOutputDto>  modelToDto(List<MonzPackageOutputModel> model);



    //entity <-> dto
    MonzPackageOutputDto toDto(MonzPackage entity);
    MonzPackage toEntity(MonzPackageInputDto dto);
    List<MonzPackageOutputDto> toDto(List<MonzPackage> entities);
    List<MonzPackage> toEntity(List<MonzPackageInputModel> dtos);


    //update
    void updateEntity(MonzPackageInputModel model, @MappingTarget MonzPackage entity);

}
