package com.vasl.samp.api.facade.mapper;

import com.vasl.samp.api.dto.ApiEndpointInputDto;
import com.vasl.samp.api.dto.ApiEndpointOutputDto;
import com.vasl.samp.dal.entity.ApiEndpoint;
import com.vasl.samp.service.model.ApiEndpointInputModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApiEndpointMapper {
    ApiEndpointOutputDto toDto(ApiEndpoint entity);

    ApiEndpoint toEntity(ApiEndpointInputModel inputModel);

    void updateEntityFromDto(ApiEndpointInputDto dto, @MappingTarget ApiEndpoint entity);

    List<ApiEndpointOutputDto> toDtoList(List<ApiEndpoint> entities);

    ApiEndpointInputModel toModel(ApiEndpointInputDto dto);
}
