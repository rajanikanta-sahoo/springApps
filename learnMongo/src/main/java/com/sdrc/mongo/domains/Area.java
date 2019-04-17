package com.sdrc.mongo.domains;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Area {

	@Id
	private String id;
	private Integer areaId;
	private String areaCode;
	private String areaName;
	private String CreatedBy;
	private Date CreatedDate;
	private Integer isLive;
	private Integer parentId;
	private String updatedBy;
	private Date updateDate;
	@DBRef
	private AreaLevel areaLevelId;
}
