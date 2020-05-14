package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBmanager;
import com.example.demo.vo.CampingRoomVo;
import com.example.demo.vo.CampingSpotVo;

@Repository
public class CampingDao {
	
	// 4) (사업자) 캠핑룸 등록 dao
	public int insertCampingRoom(CampingRoomVo crvo) {
		return DBmanager.insertCampingRoom(crvo);
	}
	
	// 3) (사업자) 캠핑룸 목록 dao
	public List<CampingRoomVo> bossCampingRoomList(){
		return DBmanager.bossCampingRoomList();
	}
	
	// 3) (사업자) 캠핑장 목록 dao
	public List<CampingSpotVo> bossCampingSpotList(){
		return DBmanager.bossCampingSpotList();
	}
	
	// 1) (사업자) 캠핑장 등록 dao
	public int insertCampingSpot(CampingSpotVo csvo) {
		return DBmanager.insertCampingSpot(csvo);
	}

}
