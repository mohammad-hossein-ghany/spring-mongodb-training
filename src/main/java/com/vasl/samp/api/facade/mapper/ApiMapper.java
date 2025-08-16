package com.vasl.samp.api.facade.mapper;

import com.vasl.samp.api.dto.ApiInputDto;
import com.vasl.samp.api.dto.ApiOutputDto;
import com.vasl.samp.dal.entity.Api;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ApiEndpointMapper.class})
public interface ApiMapper {
    ApiOutputDto toDto(Api entity);

    Api toEntity(ApiInputDto dto);

}
