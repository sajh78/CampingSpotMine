package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CampingDao;
import com.example.demo.vo.CampingRoomVo;
import com.example.demo.vo.CampingSpotVo;
import com.google.gson.Gson;

@RestController
public class CampingContoller {
	
	@Autowired
	private CampingDao cDao;

	public void setcDao(CampingDao cDao) {
		this.cDao = cDao;
	}
	
	// 4) (사업자) 캠핑룸 등록하기
	@RequestMapping("/insertCampingRoom")
	public String insertCampingRoom(CampingRoomVo crvo) {
		String str = "캠핑룸 등록을 성공하였습니다.";
		cDao.insertCampingRoom(crvo);
		return str;
	}
	
	// 3) (사업자) 캠핑룸 목록보기
	@RequestMapping(value = "/bossCampingRoomList", produces = "application/json;charset=UTF-8")
	public String bossCampingRoomList() {
		String str ="";
		List<CampingRoomVo> crList = cDao.bossCampingRoomList();
		Gson gson = new Gson();
		str = gson.toJson(crList);
		return str;
	}
	
	// 2) (사업자) 캠핑장 목록보기
	@RequestMapping(value = "/bossCampingSpotList", produces = "application/json;charset=UTF-8")
	public String bossCampingSpotList() {
		String str ="";
		List<CampingSpotVo> csList = cDao.bossCampingSpotList();
		Gson gson = new Gson();
		str = gson.toJson(csList);
		return str;
	}
	
	@RequestMapping("/insertCampingSpot")
	// 1) (사업자) 캠핑장 등록하기
	public String insertCampingSpot(CampingSpotVo csvo) {
		String str = "캠핑장 등록을 성공하였습니다.";
		cDao.insertCampingSpot(csvo);
		return str;
	}
	
}
