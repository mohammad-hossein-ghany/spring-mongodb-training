package com.vasl.samp.dal.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

@Data
public class BaseEntity {
    @Id
    private String id;
    @Version
    protected Long version;
    @CreatedDate
    protected Long createdDate;
    @LastModifiedDate
    protected Long lastModifiedDate;

}