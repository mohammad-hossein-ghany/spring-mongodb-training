package com.vasl.samp.dal.entity;

import com.vasl.samp.api.dto.MonzUserPackageOutputDto;
import lombok.Data;

import java.util.List;

@Data
public class findMonzeUserPackagesByNameAndTitleResult {
    List<String> name;
    Integer count;
    List<String>  packageId;
}
