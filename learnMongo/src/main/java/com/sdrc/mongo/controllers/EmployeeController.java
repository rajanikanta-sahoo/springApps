package com.sdrc.mongo.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdrc.mongo.domains.Authority;
import com.sdrc.mongo.domains.Designation;
import com.sdrc.mongo.domains.Employee;
import com.sdrc.mongo.repository.EmployeeRepo;
import com.sdrc.mongo.services.EmployeeGraphQlServices;
import com.sdrc.mongo.services.EmployeePdfDetails;
import com.sdrc.mongo.services.EmployeeService;
import com.sdrc.mongo.services.GraphQlService;

import graphql.ExecutionResult;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	@Autowired
	EmployeeRepo employeeRepo;
	@Autowired
	EmployeePdfDetails employeePdfDetails;
	@Autowired
	EmployeeGraphQlServices graphQLService;
	
	@Autowired
	GraphQlService graphQlService;
	
	@RequestMapping("/saveEmployee")
	@ResponseBody
	public Employee saveEmployee(@RequestParam String name,@RequestParam String userName,@RequestParam String password,
			@RequestParam String companyEmail,@RequestParam String currentAddress,@RequestParam String email,@RequestParam String emergencyNo,
			@RequestParam String fatherNo,@RequestParam String fatherName,@RequestParam String gender,@RequestParam String mobile,
			@RequestParam String motherName,@RequestParam String motherNumber,@RequestParam String permanentAddress,@RequestParam String desig) {
		Employee emp = new Employee();
		emp.setCompanyEmail(companyEmail);
		
		emp.setCurrentAddress(currentAddress);
		emp.setDob(new Date());
		emp.setEmail(email);
		emp.setEmergencyNo(emergencyNo);
		
		
		emp.setFatherName(fatherName);
		emp.setFatherNo(fatherNo);
		emp.setGender(gender);
		
		emp.setMobile(mobile);
		emp.setMotherName(motherName);
		emp.setMotherNumber(motherNumber);
		emp.setName(name);
		emp.setPassword(password);
		emp.setPermanentAddress(permanentAddress);
		emp.setUserName(userName);
		emp = employeeService.saveEmployees(emp, desig);
		
		return emp;
	}
	
	@RequestMapping("/saveAtho")
	@ResponseBody
	public Authority saveAutho(@RequestParam String name,@RequestParam String descrip) {
		Authority authority = employeeService.saveAuthority(name, descrip);
		return authority;
	}
	
	@RequestMapping("/saveDesig")
	@ResponseBody
	public Designation saveDesig(@RequestParam String name,@RequestParam String aut1,@RequestParam String aut2,@RequestParam String aut3) {
		
		List<String> authoes = new ArrayList<>();
		
		if(!aut1.isEmpty()) {authoes.add(aut1);}
		if(!aut2.isEmpty()) {authoes.add(aut2);}
		if(!aut3.isEmpty()) {authoes.add(aut3);}
		
		Designation desigs = employeeService.saveDesignation(name, authoes);
		return desigs;
	}
	
	@RequestMapping("/showAllEmployee")
	@ResponseBody
	public List<Employee> showAllEmployee(){
		return employeeRepo.findAll();
	}
	
	@RequestMapping("/showEmployee")
	@ResponseBody
	public Employee showEmployee(@RequestParam String name){
		Employee emp = employeeRepo.findByUserName(name);
		//System.out.println("data-->> "+((Authority)(((Designation)(emp.getDesignation()).toArray()[0]).getAuthoritys()).toArray()[0]).getDescription());
		return emp;
	}
	
	@RequestMapping("/showDesig")
	@ResponseBody
	public Designation showDesig(@RequestParam String name){
		return employeeService.getDesignationByName(name);
	}
	
	@RequestMapping("/showDate")
	@ResponseBody
	public Object showDate(@RequestParam String name){
		return employeeService.getEmployeeDOB(name);
	}
	
	@RequestMapping("/getEmployee")
	@ResponseBody
	public List<Employee> getEmployee(){
		return employeeService.getEmployees();
	}
	
	@RequestMapping("/updateEmp")
	@ResponseBody
	public Object updateEmployeeName(@RequestParam String oldname, @RequestParam String newname){
		return employeeService.updateEmployee(oldname, newname);
	}
	
	@RequestMapping("/getEmpByName")
	@ResponseBody
	public Employee getEmpName(@RequestParam String uname) {
		return null;
	}
	
	@RequestMapping("/getEmpPdf")
	@ResponseBody
	public File getEmpPdf(){
		return employeePdfDetails.creatPdfDetails();
	}
	
	@RequestMapping("/graphEmp")
	@ResponseBody
	public ResponseEntity<Object> graphEmps(@RequestBody String query){System.out.println("h1");
		ExecutionResult execute = graphQLService.getGraphQL().execute(query);
//		System.out.println((Employee)execute.getData() +"");
//		System.out.println((graphQLService.getGraphQL().execute(query)).getClass());
		return new ResponseEntity<>(execute, HttpStatus.OK);
	}
//	@RequestMapping("/getEmps")
//	@ResponseBody
//	public List<Employee> getEmps(@RequestBody String query){
//		
//		return graphQlService.getEmployees();
//	}
}
