package com.sdrc.mongo.services.dataFetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sdrc.mongo.domains.Employee;
import com.sdrc.mongo.repository.EmployeeRepo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllEmployeesDataFetcher implements DataFetcher<List<Employee>> {

	@Autowired
	EmployeeRepo employeeRepo;

	@Override
	public List<Employee> get(DataFetchingEnvironment environment) {
		
		return employeeRepo.findAll();
	}
	
}
