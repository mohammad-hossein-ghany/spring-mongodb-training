package com.vasl.samp.api.facade.mapper;

import com.vasl.samp.api.dto.ApiEndpointInputDto;
import com.vasl.samp.api.dto.ApiEndpointOutputDto;
import com.vasl.samp.api.dto.ApiInputDto;
import com.vasl.samp.api.dto.ApiOutputDto;
import com.vasl.samp.dal.entity.Api;
import com.vasl.samp.dal.entity.ApiEndpoint;
import com.vasl.samp.service.model.ApiEndpointInputModel;
import com.vasl.samp.service.model.ApiEndpointOutputModel;
import com.vasl.samp.service.model.ApiInputModel;
import com.vasl.samp.service.model.ApiOutputModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ApiEndpointMapper.class})
public interface ApiMapper {

    //Input (ApiInputDto -> ApiInputModel -> Api)
    ApiInputModel toModel(ApiInputDto dto);
    Api toEntity(ApiInputModel inputModel);


    //Output (Api -> ApiOutputModel -> ApiOutputDto)
    ApiOutputModel toModel(Api entity);
    ApiOutputDto toDto(ApiOutputModel dto);


    //entity <-> dto
    ApiOutputDto toDto(Api entity);
    Api toEntity(ApiInputDto dto);
    List<ApiOutputDto> toDto(List<Api> entities);
    List<Api> toEntity(List<ApiInputDto> dtos);


    //update
    void updateEntityFromDto(ApiInputDto dto, @MappingTarget Api entity);

}
