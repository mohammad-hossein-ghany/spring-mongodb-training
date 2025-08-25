package com.vasl.samp.api.dto;

import com.vasl.samp.dal.entity.ApiMethod;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;


@NoArgsConstructor
@Data
public class ApiEndpointMethodInputDto {

        @Field("id")
        private String id;

        private ApiMethod method;
        private String title;
        private String description;
        private String body;

        private String hostRedirect;
        private Integer timeout;
        private String inputSample;
        private String responseSample;
        private Boolean disable;
        private String errorField;


        private String securityId;

        private List<String> consumes;
        private List<String> produces;
        private List<String> tag;

        ///Soap
        private String namespace;
        private String inputName;
        private String outputName;
        private String soapAction;
        private String soapRequest;
        private String soapResponse;
        private Boolean qualified;
//    private ApiSecurityScheme securityScheme;
//    private List<PolicyData> pre;
//    private List<PolicyData> post;
//    private List<PolicyData> error;
//    private List<PolicyData> config;
//    private List<PolicyData> integration;
//
//    private List<PolicyData> preTrace;
//    private List<PolicyData> postTrace;

//    private ObjectId connectorId;

        private Boolean active;
        private Boolean published;

//    private ApiEndpointMethodType type;

//    private ApiEndpointMethodStatus status;
    }



