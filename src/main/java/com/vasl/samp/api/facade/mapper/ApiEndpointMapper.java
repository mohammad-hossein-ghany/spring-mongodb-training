package com.vasl.samp.api.facade.mapper;

import com.vasl.samp.api.dto.ApiEndpointInputDto;
import com.vasl.samp.api.dto.ApiEndpointOutputDto;
import com.vasl.samp.dal.entity.ApiEndpoint;
import com.vasl.samp.service.model.ApiEndpointInputModel;
import com.vasl.samp.service.model.ApiEndpointOutputModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApiEndpointMapper {

    //Input (ApiEndpointInputDto -> ApiEndpointInputModel -> ApiEndpoint)
    ApiEndpointInputModel toModel(ApiEndpointInputDto dto);
    ApiEndpoint toEntity(ApiEndpointInputModel inputModel);


    //Output (ApiEndpoint -> ApiEndpointOutputModel -> ApiEndpointOutputDto)
    ApiEndpointOutputModel toModel(ApiEndpoint entity);
    ApiEndpointOutputDto  toDto(ApiEndpointOutputModel dto);
    List<ApiEndpointOutputModel> toModel(List<ApiEndpoint> entity);


    //entity <-> dto
    ApiEndpointOutputDto toDto(ApiEndpoint entity);
    ApiEndpoint toEntity(ApiEndpointInputDto dto);
    List<ApiEndpointOutputDto> toDto(List<ApiEndpoint> entities);
    List<ApiEndpoint> toEntity(List<ApiEndpointInputDto> dtos);


    //update
    void updateEntity(ApiEndpointInputModel model, @MappingTarget ApiEndpoint entity);


}
