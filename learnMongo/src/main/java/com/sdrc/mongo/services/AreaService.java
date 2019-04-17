package com.sdrc.mongo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sdrc.mongo.domains.Area;
import com.sdrc.mongo.domains.Area1;
import com.sdrc.mongo.domains.AreaLevel;


public interface AreaService {

public Area1 saveArea(String areaName, String AreaCode);

public AreaLevel saveAreaLevel(String AreaLevelName,int areaLevelId,int areaLevel,int parentId);

public Area saveAreaByUri(int id,String areaCode,String areaName,int parentId,int areaLevelId);

public List<Area> saveAreaByExcele();

public List<Area> getAllArea();

public List<AreaLevel> getAllAreaLevels();

public AreaLevel getOneAreaLevel(int id);

public long getCount();

public long getCountBy(int id);

public List<Area>getAreaByAreaLevel(String levelName);

public List<Area>getAllAreaPage();

public List<Area> getTest1();

public List<Area> getIn(String name);
	
}
