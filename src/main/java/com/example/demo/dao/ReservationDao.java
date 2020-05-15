package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBmanager;
import com.example.demo.vo.BossReservationVo;

@Repository
public class ReservationDao {
	
	// 1) (사업자) 예약 관리 현황 목록보기
	public List<BossReservationVo> bossReservationList(){
		return DBmanager.bossReservationList();
	}

}
