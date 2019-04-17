package com.sdrc.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.sdrc.mongo.domains.Designation;

@Repository
public interface DesignationRepo extends MongoRepository<Designation, String>{

	@Query("{'name':?0}")
	Designation getByName(String name);
}
