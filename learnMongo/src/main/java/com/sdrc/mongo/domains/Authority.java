package com.sdrc.mongo.domains;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Authority {

	@Id
	private String id;
	private String authority;
	private String description;
	
	//private List<Designation> designations;
}
