package com.vasl.samp.api.facade.mapper;

import com.vasl.samp.api.dto.ApiInputDto;
import com.vasl.samp.api.dto.ApiOutputDto;
import com.vasl.samp.dal.entity.Api;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ApiEndpointMapper.class})
public interface ApiMapper {
    ApiOutputDto toDto(Api entity);

    Api toEntity(ApiInputDto dto);

    List<ApiOutputDto> toDtoList(List<Api> entities);

    void updateEntityFromDto(ApiInputDto dto, @MappingTarget Api entity);
}
