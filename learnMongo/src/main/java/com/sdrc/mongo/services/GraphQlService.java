package com.sdrc.mongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdrc.mongo.domains.Employee;
import com.sdrc.mongo.repository.AreaRepo;
import com.sdrc.mongo.repository.EmployeeRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;


@Service
public class GraphQlService {

	@Autowired
	EmployeeRepo employeeRepo;
	@Autowired 
	AreaRepo areaRepo;
	
	@GraphQLQuery(name="employees")
	public List<Employee> getEmployees(){
		return employeeRepo.findAll();
	}
	
	@GraphQLQuery(name="employee")
	public Employee getOneEmployees(@GraphQLArgument(name = "id") String uname ){
		return employeeRepo.findByUserName(uname);
	}
}