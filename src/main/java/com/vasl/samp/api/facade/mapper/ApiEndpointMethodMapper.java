package com.vasl.samp.api.facade.mapper;

import com.vasl.samp.api.dto.ApiEndpointMethodInputDto;
import com.vasl.samp.api.dto.ApiEndpointMethodOutputDto;
import com.vasl.samp.api.dto.ApiInputDto;
import com.vasl.samp.api.dto.ApiOutputDto;
import com.vasl.samp.dal.entity.Api;
import com.vasl.samp.dal.entity.ApiEndpointMethod;
import com.vasl.samp.service.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring" )
public interface ApiEndpointMethodMapper {



        //Input (ApiInputDto -> ApiInputModel -> Api)
        ApiEndpointMethodInputModel toModel(ApiEndpointMethodInputDto dto);
        ApiEndpointMethod toEntity(ApiEndpointMethodInputModel inputModel);
        List<ApiEndpointMethodInputModel> dtoToModel(List<ApiEndpointMethodInputDto> dto);
        List<ApiEndpointMethod> modelToEntity(List<ApiEndpointMethodInputModel> inputModel);


        //Output (Api -> ApiOutputModel -> ApiOutputDto)
        ApiEndpointMethodOutputModel toModel(ApiEndpointMethod entity);
        ApiEndpointMethodOutputDto toDto(ApiEndpointMethodOutputModel model);
        List<ApiEndpointMethodOutputModel>  entityToModel(List<ApiEndpointMethod> entities);
        List<ApiEndpointMethodOutputDto>  modelToDto(List<ApiEndpointMethodOutputModel> model);



        //entity <-> dto
        ApiEndpointMethodOutputDto toDto(ApiEndpointMethod entity);
        ApiEndpointMethod toEntity(ApiEndpointMethodInputDto dto);
        List<ApiEndpointMethodOutputDto> toDto(List<ApiEndpointMethod> entities);
        List<ApiEndpointMethod> toEntity(List<ApiEndpointMethodInputModel> dtos);

        //
        List<ApiEndpointMethod> outputModelToEntity(List<ApiEndpointMethodOutputModel> model);

        //update
        void updateEntity(ApiEndpointMethodInputModel model, @MappingTarget ApiEndpointMethod entity);
    }









