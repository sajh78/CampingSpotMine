package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBmanager;
import com.example.demo.vo.CampingRoomVo;
import com.example.demo.vo.CampingSpotVo;

@Repository
public class CampingDao {
	
	
	// 6) (사업자) 캠핑룸 삭제 dao
	public int deleteCampingRoom(int cr_no) {
		return DBmanager.deleteCampingRoom(cr_no);
	}
	
	// 5) (사업자) 캠핑룸 업데이트 dao
	public int updateCampingRoom(CampingRoomVo crvo) {
		return DBmanager.updateCampingRoom(crvo);
	}
	
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
