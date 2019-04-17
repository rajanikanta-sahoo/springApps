package com.sdrc.mongo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.sdrc.mongo.domains.Authority;
import com.sdrc.mongo.domains.Designation;
import com.sdrc.mongo.domains.Employee;
import com.sdrc.mongo.repository.AuthorityRepo;
import com.sdrc.mongo.repository.DesignationRepo;
import com.sdrc.mongo.repository.EmployeeRepo;

@Service
public class EmployeeServiceImp implements EmployeeService{

	@Autowired
	EmployeeRepo employeeRepo;
	@Autowired
	DesignationRepo designationRepo;
	@Autowired
	AuthorityRepo authorityRepo;
	
	@Override
	public Employee saveEmployees(Employee emp,String desig) {
		emp.setCredentialexpired(false);
		emp.setEnabled(true);
		emp.setExpired(false);
		emp.setLocked(false);
		Designation designation = getDesignationByName(desig);
		List<Designation> desigList = new ArrayList<>();
		desigList.add(designation);
		emp.setDesignation(desigList);
		
		employeeRepo.save(emp);
		
		return emp;
	}

	@Override
	public Designation getDesignationByName(String desig) {
		Designation designation = designationRepo.getByName(desig);
		return designation;
	}

	@Override
	public Designation saveDesignation(String name,List<String>autho) {
		Designation desig = new Designation();
		List<Authority>athoList = new ArrayList<>();
		autho.forEach(d->{
			Authority atho = authorityRepo.findByAuthority(d);
			athoList.add(atho);
		});
		desig.setName(name);
		desig.setAuthoritys(athoList);
		desig = designationRepo.save(desig);
		return desig;
	}

	@Override
	public Authority saveAuthority(String name, String discription) {
		Authority authority = new Authority();
		authority.setAuthority(name);
		authority.setDescription(discription);
		authority = authorityRepo.save(authority);
		return authority;
	}

	@Override
	public Object getEmployeeDOB(String name) {
		Object date= employeeRepo.findDobByUserName(name);
		return date;
	}

	@Override
	public List<Employee> getEmployees() {
		
		return employeeRepo.findUsername();
	}

	@Override
	public Employee updateEmployee(String oldName,String newName) {
		Employee emp = employeeRepo.findByName(oldName);
		emp.setName(newName);
		emp = employeeRepo.save(emp);
		return emp;
	}

	@Override
	public Employee getEmpByUName(String uname) {
		Query query = new Query();
		query.addCriteria(Criteria.where("uname").is(uname));
		//MongoOperations mongoTamplate = new MongoTemplate(mongoDbFactory);
		//Employee emp = 
		return null;
	}
	
	

}
