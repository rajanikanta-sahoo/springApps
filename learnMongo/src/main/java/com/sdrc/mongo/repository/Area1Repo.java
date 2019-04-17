package com.sdrc.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sdrc.mongo.domains.Area1;

@Repository
public interface Area1Repo extends MongoRepository<Area1, Integer>{
	@SuppressWarnings("unchecked")
	Area1 save(Area1 area);

}
