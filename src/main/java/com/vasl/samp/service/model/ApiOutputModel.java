package com.vasl.samp.service.model;

import com.vasl.samp.dal.entity.ApiConfiguration;
import com.vasl.samp.dal.entity.ApiEndpoint;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.*;

import java.util.List;

@Data
public class ApiOutputModel {
    //BaseEntity
//    @Id
    private String id;
//    @Version
    protected Long version;
//    @CreatedDate
    protected Long createdDate;
//    @LastModifiedDate
    protected Long lastModifiedDate;


    //Api
    private String name;
    private String title;
    private String description;
    private ApiConfiguration apiConfiguration;
    private String url;
    private Integer maxSpeed;
    private Boolean stateful;
    private Boolean internalForward;
    private Boolean disable;
    private String errorField;
    private String termsOfService;
    private List<String> consumes;
    private List<String> produces;
    private String wsdl;
    private String wsdlUsername;
    private String wsdlPassword;
    private String soapBindingName;
    private Integer soapProtocol;
    private Boolean soapQualified;
    private Boolean generateSoapGateway;
    private List<ApiEndpoint> endpoints;
    private ObjectId userId;
    private ObjectId createdBy;
    private String username;
    //    private ApiStatus status;
    private Boolean active;
    private List<ObjectId> documentations;

    private String apiVersion;
    private String context;
    private List<String> tag;

    private String rootVersionId;

    private Boolean userForwardEnabled;

    private String connectorConfigId;

    @Transient
    private String consumerUrl;


    public enum FieldName {

        ID("id"),
        NAME("name"),
        TITLE("title"),
        ENDPOINTS("endpoints"),
        USERID("userId"),
        USERNAME("username"),
        ACTIVE("active"),
        CONTEXT("context"),
        API_VERSION("apiVersion"),
        PATH("endpoints.path"),
        ROOT_VERSION_ID("rootVersionId"),
        CREATED_DATE("createdDate"),
        LAST_MODIFIED_DATE("lastModifiedDate"),
        CATEGORY("apiConfiguration.category"),
        ;


        private final String value;

        FieldName(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }

    }

    @Data
    public static class ApiCountResult {
        private long count;
    }
}
