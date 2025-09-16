package com.vasl.samp.dal.repository;

import com.vasl.samp.api.dto.ProviderPackageCountOutputDto;
import com.vasl.samp.dal.entity.ApiIsUsedInMonzPackage;
import com.vasl.samp.dal.entity.MonzPackage;
import com.vasl.samp.dal.entity.ProviderPackageCount;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MonzRepository extends MongoRepository<MonzPackage , String> {


    @Aggregation(pipeline = {
            "{ $match: { 'username': ?0 } }",
            "{ $group: { _id: '$username', ProviderPackageCount: { $sum: 1 } } }",
            "{ $project: { _id: 0, ProviderPackageCount: 1 } }"
    })
    ProviderPackageCount getProviderPackageCountByUsername(String username);

    @Aggregation(pipeline = {
            "{ '$unwind': { 'path': '$apis'} }",
            "{ '$project': { 'exist': { '$eq': [ '$apis.connectorId',  ?0 ] } } }",
            "{ '$group': { '_id': 0, 'exist': { '$max': '$exist' } } }",
            "{ '$project': { '_id': 0, 'exists': '$exist' } }"
    })
    ApiIsUsedInMonzPackage existsApiInMonzePackageByApiId(ObjectId connectorId);

}
