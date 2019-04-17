package com.sdrc.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.sdrc.mongo.domains.Employee;

@Repository
public interface EmployeeRepo extends MongoRepository<Employee, String>{

	List<Employee> findAll();
	
	Employee findByUserName(String uname);
	
	Object findDobByUserName(String uname);
	
	@Query(value="{}",fields="{userName:1,password:1,id:0}")
	List<Employee> findUsername();
	
	//Employee updateOneEmployee(Employee emp);
	
	Employee findByName(String name);
}
