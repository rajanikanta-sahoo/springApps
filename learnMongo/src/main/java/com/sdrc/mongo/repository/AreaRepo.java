package com.sdrc.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;
import com.sdrc.mongo.domains.Area;

@Repository
public interface AreaRepo extends MongoRepository<Area, String>{
	@SuppressWarnings("unchecked")
	Area save(Area area);
	
	List<Area> findAll();
	Area findByAreaId(Integer areaId);
	

	@Query("{'AreaLevelId' : {$in:?0}}")
	List<Area> findByAreaLevelIn(List<DBObject> areaLevelDBObjects);
	
	long count();
	
	long countByIsLive(Integer id);
	
	//List<Area> findByAreaLevelGroupByParentId(int id);
	
	@Query("{},{areaCode:1,areaName:1,_id:0}")
	List<Area> testQuery1();
	
//	@Query("find({},{areaCode:1,areaName:1,_id:0})")
//	List<Area> testQuery2();
	
	List<Area> findByAreaLevelIdIn(List id);

}
