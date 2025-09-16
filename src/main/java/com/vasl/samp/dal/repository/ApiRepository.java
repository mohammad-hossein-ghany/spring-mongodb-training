package com.vasl.samp.dal.repository;

import com.vasl.samp.dal.entity.Api;
import com.vasl.samp.dal.entity.ApiIsUsedInMonzPackage;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ApiRepository extends MongoRepository<Api, String> {

    Optional<Api> findByName(String name);

    List<Api> findAllByTitle(String title);

    Api findByTitle(String title);





//    @Aggregation(pipeline = {
//            "{ '$project': { " +
//                    "    'hasConnector': { " +
//                    "      '$anyElementTrue': { " +
//                    "        '$map': { " +
//                    "          'input': { $ifNull: ['$apis', []] }, " +
//                    "          'as': 'api', " +
//                    "          'in': { '$eq': [ '$$api.connectorId', { '$toObjectId': ?0 } ] } " +
//                    "        } " +
//                    "      } " +
//                    "    } " +
//                    "  } }",
//            "{ '$group': { '_id': 'null', 'exists': { '$max': '$hasConnector' } } }",
//            "{ '$project': { '_id': 0, 'exists': '$exists' } }"
//    })







}



