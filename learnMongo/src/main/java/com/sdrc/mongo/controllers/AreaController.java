package com.sdrc.mongo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdrc.mongo.domains.Area;
import com.sdrc.mongo.domains.Area1;
import com.sdrc.mongo.domains.AreaLevel;
import com.sdrc.mongo.repository.Area1Repo;
import com.sdrc.mongo.services.AreaService;

@Controller
public class AreaController {

	
	@Autowired
	AreaService areaService;
	
	@Autowired
	Area1Repo areaRepo;
	
	@RequestMapping("/saveData")
	@ResponseBody
	public Area1 saveArea(@RequestParam String areaName, @RequestParam String areaCode) {
		Area1 area = null;
		area = areaService.saveArea(areaName, areaCode);
		
		return area;
	}
	
	@RequestMapping("/areaLevel")
	@ResponseBody
	public AreaLevel saveAreaLevel(@RequestParam String areaLevelName,@RequestParam int levelId,@RequestParam int level,@RequestParam int parent) {
		AreaLevel areaLevel = null;
		areaLevel = areaService.saveAreaLevel(areaLevelName, levelId, level, parent);
		return areaLevel;
	}
	
	@RequestMapping("/saveArea1")
	@ResponseBody
	public Area saveArea1(@RequestParam int id, @RequestParam String areaCode,@RequestParam String areaName,@RequestParam int parentId,
			@RequestParam int areaLevelId) {
		Area area = null;
		area = areaService.saveAreaByUri(id, areaCode, areaName, parentId, areaLevelId);
		return area;
	}
	
	@RequestMapping("/getAllArea")
	@ResponseBody
	public List<Area> getAllAreas(){
		List<Area> areaList= areaService.getAllArea();
		
		return areaList;
	}
	
	@RequestMapping("/getAllAreaPage")
	@ResponseBody
	public List<Area> getAllAreasPage(){
		List<Area> areaList= areaService.getAllAreaPage();
		
		return areaList;
	}
	
	@RequestMapping("/getAllAreaLevel")
	@ResponseBody
	public List<AreaLevel> getAllAreaLevel(){
		List<AreaLevel> areaLevelList= areaService.getAllAreaLevels();
		
		return areaLevelList;
	}
	
	@RequestMapping("/getAreaLevel")
	@ResponseBody
	public AreaLevel getAreaLevel(@RequestParam int id) {
		return areaService.getOneAreaLevel(id);
	}
	
	@RequestMapping("/getTotalArea")
	@ResponseBody
	public long getCounts() {
		return areaService.getCount();
	}
	
	@RequestMapping("/getTotalAreaBy")
	@ResponseBody
	public long getCountsBy(@RequestParam int id) {
		return areaService.getCountBy(id);
	}
	
	
	@RequestMapping("/saveExcel")
	@ResponseBody
	public String  saveExcel() {
		String status = "problem";
		List areas = areaService.saveAreaByExcele();
		if(areas.size() > 0) {
			status = "Done";
		}
		return status;
	}
	@RequestMapping("/getAreaByLevele")
	@ResponseBody
	public List<Area>  getAreaByAreaLevel(@RequestParam String levelName) {
		
		return areaService.getAreaByAreaLevel(levelName);
	}
	
	@RequestMapping("/getTest1")
	@ResponseBody
	public List<Area>  getTest1() {
		
		return areaService.getTest1();
	}
	
	@RequestMapping("/getAreaIn")
	@ResponseBody
	public List<Area>  getAreaIn(@RequestParam String level) {
		
		return areaService.getIn(level);
	}
	
}
