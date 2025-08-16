package com.vasl.samp.api.facade.mapper;

import com.vasl.samp.api.dto.ApiEndpointInputDto;
import com.vasl.samp.api.dto.ApiEndpointOutputDto;
import com.vasl.samp.dal.entity.ApiEndpoint;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ApiEndpointMapper {
    ApiEndpointOutputDto toDto(ApiEndpoint entity);

    ApiEndpoint toEntity(ApiEndpointInputDto dto);

    void updateEntityFromDTO(ApiEndpointInputDto dto, @MappingTarget ApiEndpoint entity);

}
