package com.vasl.samp.dal.repository;

import com.vasl.samp.dal.entity.Api;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ApiRepository extends MongoRepository<Api, String> {

    Optional<Api> findByName(String name);

    List<Api> findAllByTitle(String title);

    Api findByTitle(String title);


}
