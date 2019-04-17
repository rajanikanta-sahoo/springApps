package com.sdrc.mongo.domains;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Employee {
	@Id
	private String id;
	private String name;
	private String userName;
	private String password;
	private String companyEmail;
	private String currentAddress;
	private Date dob;
	private String email;
	private String emergencyNo;	
	private String fatherNo;
	private String fatherName;
	private String gender;
	private String mobile;
	private String motherName;
	private String motherNumber;
	private String permanentAddress;
	private boolean credentialexpired;
	private boolean enabled;
	private boolean expired;
	private boolean locked;
	
//	@DBRef
//	private List<Authority> authority;
	
	@DBRef
	private List<Designation> designation;
}
