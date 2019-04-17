package com.sdrc.mongo.domains;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Designation {

	@Id
	private String id;
	private String name;
	
	List<Authority> authoritys;
}
