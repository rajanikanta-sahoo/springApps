package com.sdrc.mongo.domains;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class AreaLevel {

	@Id
	private String id;
	private Integer areaLevelId;
	private Integer areaLevel;
	private String areaLevelName;
	private Integer parentAreaLevel;
	
	
}
