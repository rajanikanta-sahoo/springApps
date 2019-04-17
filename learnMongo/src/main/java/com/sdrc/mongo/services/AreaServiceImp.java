package com.sdrc.mongo.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.sdrc.mongo.domains.Area;
import com.sdrc.mongo.domains.Area1;
import com.sdrc.mongo.domains.AreaLevel;
import com.sdrc.mongo.repository.Area1Repo;
import com.sdrc.mongo.repository.AreaLevelRepo;
import com.sdrc.mongo.repository.AreaRepo;

@Service
public class AreaServiceImp implements AreaService {

	@Autowired
	Area1Repo area1Repo;
	@Autowired
	AreaLevelRepo arealevelRepo;
	@Autowired
	AreaRepo areaRepo;
	
	@Override
	public Area1 saveArea(String areaName, String areaCode) {
		Area1 area= new Area1();
		area.setAreaCode(areaCode);
		area.setAreaName(areaName);
		area = area1Repo.save(area);
		
		return area;
	}

	@Override
	public AreaLevel saveAreaLevel(String areaLevelName, int areaLevelId, int areaLevel, int parentId) {
		// TODO Auto-generated method stub
		AreaLevel arealevel = new AreaLevel();
		arealevel.setAreaLevel(areaLevel);
		arealevel.setAreaLevelId(areaLevelId);
		arealevel.setAreaLevelName(areaLevelName);
		arealevel.setParentAreaLevel(parentId);
		arealevelRepo.save(arealevel);
		
		return arealevel;
	}

	@Override
	public Area saveAreaByUri(int id, String areaCode, String areaName, int parentId, int areaLevelId) {
		
		Area area = new Area();
		area.setAreaCode(areaCode);
		area.setAreaId(id);
		area.setAreaLevelId(arealevelRepo.findByAreaLevelId(areaLevelId));
		area.setAreaName(areaName);
		area.setCreatedBy(null);
		area.setCreatedDate(null);
		area.setIsLive(1);
		area.setParentId(parentId);
		area.setUpdateDate(null);
		area.setUpdatedBy(null);
		areaRepo.save(area);
		
		return area;
	}
	
	@Override
	public List<Area>getAllArea(){
		
		return areaRepo.findAll();
		//return areaRepo.findAll(new Sort(Sort.Direction.ASC, "areaName"));
		
	}
	@Override
	public List<Area>getAllAreaPage(){
		Pageable pageableRequest = PageRequest.of(0, 10);
		//return areaRepo.findAll(new Sort(Sort.Direction.ASC, "areaName"));
		Page<Area> areas = areaRepo.findAll(pageableRequest);;
		return areas.getContent();
	}

	@Override
	public List<Area> saveAreaByExcele() {
		List<Area> areas = new ArrayList<Area>();
		List<Area> allAreas= new ArrayList<Area>();
		Map<String,Area>areasMap = new HashMap<>();
		Area area = null;
		XSSFWorkbook workbook = null;
		try {
			
			
			workbook = new XSSFWorkbook(new File("C:\\Users\\SDRC_DEV\\Desktop\\areaList.xlsx"));
		    Integer areaId = null;
		    String areaCode = null;
		    String areaName = null;
		    Integer parentId = null;
		    //String parentIds = null;
		    Integer areaLevele = null;
		    int areaCount = (int) getCount();
		    
		    
		    
			XSSFSheet areaSheet = workbook.getSheetAt(0);
			Map<String, Integer> areaMap = new HashMap<>();
			
			allAreas = getAllArea();
		    allAreas.forEach(d ->{
		    	areasMap.put(d.getAreaCode(), d);
		    	areaMap.put(d.getAreaCode(),d.getAreaId() );
		    	
		    });
			
			for (int row = 1; row <= areaSheet.getLastRowNum(); row++) {
				area = new Area();
				XSSFRow xssfRow = areaSheet.getRow(row);
				for (int cols = 0; cols < 5; cols++) {
					Cell cell = xssfRow.getCell(cols);
					switch (cols) {
					case 0:
						areaId = areaCount + row;
						break;
					case 1:
						areaCode = cell.getStringCellValue();
						break;
					case 2:
						areaName = cell.getStringCellValue();
						break;
					case 3:
						parentId = areaMap.get(cell.getStringCellValue());
						
						break;
					case 4:
						areaLevele = (int) cell.getNumericCellValue();
						break;
					}
				}
				
				if(!areasMap.containsKey(areaCode)) {
				area.setAreaCode(areaCode);
				area.setAreaId(areaId);
				area.setAreaLevelId(arealevelRepo.findByAreaLevelId(areaLevele));
				area.setAreaName(areaName);
				area.setCreatedBy(null);
				area.setCreatedDate(null);
				area.setIsLive(1);
				area.setParentId(parentId);
				area.setUpdateDate(null);
				area.setUpdatedBy(null);
				area=areaRepo.save(area);
				areas.add(area);
				//System.out.println("AreaCode-> "+ area.getAreaCode());
				areaMap.put(area.getAreaCode(), area.getAreaId());
				}
			}
			
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			 try {
				workbook.close();
			} catch (IOException e) {
				
				//e.printStackTrace();
			}
		}
		return areas;
	}

	@Override
	public List<AreaLevel> getAllAreaLevels() {
		List<AreaLevel> allAreaLevels = arealevelRepo.findAll();
		return allAreaLevels;
	}

	@Override
	public AreaLevel getOneAreaLevel(int id) {
		
		return arealevelRepo.findByAreaLevelId(id);
	}

	@Override
	public long getCount() {
		
		return areaRepo.count();
	}
	
	@Override
	public long getCountBy(int id) {
		
		return areaRepo.countByIsLive(id);
	}

	@Override
	public List<Area> getAreaByAreaLevel(String levelName) {
		List<Area> areaList = null;
		AreaLevel areaLevel = arealevelRepo.findByAreaLevelName(levelName);
		
		List<DBObject> dbObjects = new ArrayList<>(); 
		DBObject dbObject =  new BasicDBObject();
		dbObject.put("$ref", "areaLevel");
		dbObject.put("$id", new ObjectId(areaLevel.getId()) );
		dbObjects.add(dbObject);
		
		areaList = areaRepo.findByAreaLevelIdIn(dbObjects);
		return areaList;
	}

	@Override
	public List<Area> getTest1() {
		
		return areaRepo.testQuery1();
	}
	
	@Override
	public List<Area> getIn(String name) {
		AreaLevel areaLevel = arealevelRepo.findByAreaLevelName(name);
		List<AreaLevel> levelList = new ArrayList<>();
		levelList.add(areaLevel);
		return areaRepo.findByAreaLevelIdIn(levelList);
	}
	
}
