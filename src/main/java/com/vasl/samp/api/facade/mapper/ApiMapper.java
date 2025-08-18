package com.vasl.samp.api.facade.mapper;

import com.vasl.samp.api.dto.ApiInputModel;
import com.vasl.samp.api.dto.ApiOutputDto;
import com.vasl.samp.dal.entity.Api;
import com.vasl.samp.service.model.ApiOutputModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ApiEndpointMapper.class})
public interface ApiMapper {

    //Input (ApiInputDto -> ApiInputModel -> Api)
    com.vasl.samp.service.model.ApiInputModel toModel(ApiInputModel dto);
    Api toEntity(com.vasl.samp.service.model.ApiInputModel inputModel);


    //Output (Api -> ApiOutputModel -> ApiOutputDto)
    ApiOutputModel toModel(Api entity);
    ApiOutputDto toDto(ApiOutputModel dto);


    //entity <-> dto
    ApiOutputDto toDto(Api entity);
    Api toEntity(ApiInputModel dto);
    List<ApiOutputDto> toDto(List<Api> entities);
    List<Api> toEntity(List<ApiInputModel> dtos);


    //update
    void updateEntityFromDto(ApiInputModel dto, @MappingTarget Api entity);

}
