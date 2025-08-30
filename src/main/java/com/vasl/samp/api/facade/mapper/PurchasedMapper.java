package com.vasl.samp.api.facade.mapper;

import com.vasl.samp.api.dto.PurchasedInputDto;

import com.vasl.samp.service.model.PurchasedInputModel;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PurchasedMapper {

    //Input (PurchasedInputDto -> PurchasedInputModel)
    PurchasedInputModel toModel(PurchasedInputDto dto);

    List<PurchasedInputModel> dtoToModel(List<PurchasedInputDto> dto);





}
