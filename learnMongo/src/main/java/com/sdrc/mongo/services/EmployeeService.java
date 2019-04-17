package com.sdrc.mongo.services;

import java.util.List;

import com.sdrc.mongo.domains.Authority;
import com.sdrc.mongo.domains.Designation;
import com.sdrc.mongo.domains.Employee;

public interface EmployeeService {

	public Employee saveEmployees(Employee emp,String desig);
	
	public Designation getDesignationByName(String desig);
	
	public Designation saveDesignation(String name,List<String>autho);
	
	public Authority saveAuthority(String name,String discription);
	
	public Object getEmployeeDOB(String name);
	
	public List<Employee> getEmployees();
	
	public Employee updateEmployee(String oldName,String newName);
	
	public Employee getEmpByUName(String uname);
}
