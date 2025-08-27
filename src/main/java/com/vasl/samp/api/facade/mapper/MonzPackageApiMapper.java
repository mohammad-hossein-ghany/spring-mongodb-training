package com.vasl.samp.api.facade.mapper;

import com.vasl.samp.api.dto.MonzPackageApiInputDto;
import com.vasl.samp.api.dto.MonzPackageApiOutputDto;
import com.vasl.samp.dal.entity.MonzPackageApi;
import com.vasl.samp.service.model.MonzPackageApiInputModel;
import com.vasl.samp.service.model.MonzPackageApiOutputModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MonzPackageApiMapper {
    //Input (MonzPackageApiInputDto -> MonzPackageApiInputModel -> MonzPackageApi)
    MonzPackageApiInputModel toModel(MonzPackageApiInputDto dto);
    MonzPackageApi toEntity(MonzPackageApiInputModel inputModel);
    List<MonzPackageApiInputModel> dtoToModel(List<MonzPackageApiInputDto> dto);
    List<MonzPackageApi> modelToEntity(List<MonzPackageApiInputModel> inputModel);


    //Output (MonzPackageApi -> MonzPackageApiOutputModel -> MonzPackageApiOutputDto)
    MonzPackageApiOutputModel toModel(MonzPackageApi entity);
    MonzPackageApiOutputDto toDto(MonzPackageApiOutputModel model);
    List<MonzPackageApiOutputModel>  entityToModel(List<MonzPackageApi> entities);
    List<MonzPackageApiOutputDto>  modelToDto(List<MonzPackageApiOutputModel> model);



    //entity <-> dto
    MonzPackageApiOutputDto toDto(MonzPackageApi entity);
    MonzPackageApi toEntity(MonzPackageApiInputDto dto);
    List<MonzPackageApiOutputDto> toDto(List<MonzPackageApi> entities);
    List<MonzPackageApi> toEntity(List<MonzPackageApiInputModel> dtos);


    //update
    void updateEntity(MonzPackageApiInputModel model, @MappingTarget MonzPackageApi entity);

}
