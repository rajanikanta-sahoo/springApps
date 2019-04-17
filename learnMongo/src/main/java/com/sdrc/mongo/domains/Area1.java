package com.sdrc.mongo.domains;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Area1 {

	@Id
	private String id;
	private String areaName;
	private String areaCode;
}
