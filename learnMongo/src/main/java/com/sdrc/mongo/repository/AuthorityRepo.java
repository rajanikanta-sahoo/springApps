package com.sdrc.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sdrc.mongo.domains.Authority;

@Repository
public interface AuthorityRepo extends MongoRepository<Authority, String>{

	Authority findByAuthority(String name);
}
