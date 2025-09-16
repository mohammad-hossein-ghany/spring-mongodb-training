package com.vasl.samp.dal.repository;

import com.vasl.samp.dal.entity.ConsumerPackageCount;
import com.vasl.samp.dal.entity.MonzUserPackage;
import com.vasl.samp.dal.entity.findMonzeUserPackagesByNameAndTitleResult;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MonzUserPackageRepository extends MongoRepository<MonzUserPackage, String> {


    @Aggregation(pipeline = {
            "{ '$addFields': { 'packageIdObj': { '$toObjectId': '$packageId' } } }",
            "{ '$lookup': { 'from': 'monzPackage', 'localField': 'packageIdObj', 'foreignField': '_id', 'as': 'packageInfo' } }",
            "{ '$addFields': { 'packageInfo': { '$map': { 'input': '$packageInfo', 'as': 'pkg', 'in': { 'plans': '$$pkg.plans' } } } } }",
            "{ '$set': { 'plan': { '$reduce': { 'input': '$packageInfo', 'initialValue': [ ], 'in': { '$concatArrays': [ '$$value', '$$this.plans' ] } } } } }",
            "{ '$unset': ['packageInfo', 'packageIdObj'] }",
            "{ '$set': { 'plan': { '$filter': { 'input': '$plan', 'as': 'plan', 'cond': { '$eq': ['$$plan._id', '$planId'] } } } } }",
            "{ '$match': { 'plan': { '$elemMatch': { 'name': { '$regex': ?0 , '$options': 'i' }, 'title': { '$regex': ?1 , '$options': 'i' } } } } }",
            "{ '$group': { '_id': '$plan.name', 'count': { '$sum': 1 }, 'projectId': { '$push': '$_id' } } }",
            "{ '$project': { '_id': 0, 'name': '$_id', 'count': '$count', 'packageId': '$projectId' } }"
    })
    List<findMonzeUserPackagesByNameAndTitleResult> findMonzeUserPackagesByNameAndTitle(String name, String title);


    @Aggregation(pipeline = {
            "{ $match: { 'username': ?0 } }",
            "{ $group: { _id: '$username', consumerPackageCount: { $sum: 1 } } }",
            "{ $project: { _id: 0, consumerPackageCount: 1 } }"
    })
    ConsumerPackageCount consumerPackageCounter(String username);


}
