package com.sdrc.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sdrc.mongo.domains.AreaLevel;

@Repository
public interface AreaLevelRepo extends MongoRepository<AreaLevel, String> {

	@SuppressWarnings("unchecked")
	AreaLevel save(AreaLevel areaLevel);
	
	List<AreaLevel> findAll();
	AreaLevel findByAreaLevelId(Integer id);
	
	AreaLevel findByAreaLevelName(String name);
}
