package com.vasl.samp.api.dto;

import com.vasl.samp.dal.entity.ApiEndpointMethod;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@ToString
public class ApiEndpointInputDto {

    private String name;
    private String title;
    private String description;
    private String url;
    private List<ApiEndpointMethod> methods;
    private String type;

    //todo : should add index annotation
    private String workflowKey;

    private Boolean shouldValidate;

    private String mapSucField;
    private String mapSucValue;
    private String mapMsgField;

//    private ApiEndpointStatus status;

    private String path;
    private String licenseId;

    @Field("ownerId")
    private String ownerId;

    public enum FieldName {

        ID("id"),
        NAME("name"),
        TITLE("title"),
        TYPE("type");


        private final String value;

        FieldName(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }

    }


}










