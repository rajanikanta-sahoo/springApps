package com.sdrc.mongo.services.dataFetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sdrc.mongo.domains.Employee;
import com.sdrc.mongo.repository.EmployeeRepo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class EmployeeDataFetcher implements DataFetcher<Employee> {

	@Autowired
	EmployeeRepo employeeRepo;
	
	@Override
	public Employee get(DataFetchingEnvironment environment) {
		String uname = environment.getArgument("userName");
		return employeeRepo.findByUserName(uname);
	}

}
