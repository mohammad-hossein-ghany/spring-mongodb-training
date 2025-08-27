package com.vasl.samp.dal.repository;

import com.vasl.samp.dal.entity.MonzPackage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MonzRepository extends MongoRepository<MonzPackage , String> {
}
