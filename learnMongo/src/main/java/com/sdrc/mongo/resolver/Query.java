package com.sdrc.mongo.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.sdrc.mongo.domains.Employee;
import com.sdrc.mongo.repository.AuthorityRepo;
import com.sdrc.mongo.repository.DesignationRepo;
import com.sdrc.mongo.repository.EmployeeRepo;

public class Query implements GraphQLQueryResolver{

	@Autowired
	EmployeeRepo employeeRepo;
	@Autowired
	DesignationRepo designationRepo;
	@Autowired
	AuthorityRepo authorityRepo;
	
	public List<Employee> allEmployee() {
		return employeeRepo.findAll();
	}
	
	public Employee employee(String userName) {
		return employeeRepo.findByUserName(userName);
	}
	
}
