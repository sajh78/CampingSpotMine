package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBmanager;
import com.example.demo.vo.BossReservationVo;
import com.example.demo.vo.ReserveSearchVo;

@Repository
public class ReservationDao {
	
	// 6) (사업자) 달력 예약 : 예약 완료
	public List<BossReservationVo> bossListCalendarCP(HashMap map){
		return DBmanager.bossListCalendarCP(map);
	}
	
	// 5) (사업자) 달력 예약 : 예약가능 
	public List<BossReservationVo> bossListCalendarPS(HashMap map){
		return DBmanager.bossListCalendarPS(map);
	}
	
	// 4) (사업자) 사업자페이지 메인 예약목록
	public List<BossReservationVo> businessMyPageReservationList(int cs_no){
		return DBmanager.businessMyPageReservationList(cs_no);
	}
			
	// 3) (사업자) 취소승인 업데이트
	public int updateCancelStatus(int r_no) {
		return DBmanager.updateCancelStatus(r_no);
	}

	// 2) (사업자) 예약승인 업데이트
	public int updateReserveStatus(int r_no) {
		return DBmanager.updateReserveStatus(r_no);
	}
	
	// 1) (사업자) 예약 관리 현황 목록보기
	public List<BossReservationVo> bossReservationList(int cs_no){
		return DBmanager.bossReservationList(cs_no);
	}
	
// ================================================================================================		
		
		// (사업자) 예약 상세정보 => 안씀 xxx
		public BossReservationVo getBossReservationList(int r_no) {
			return DBmanager.getBossReservationList(r_no);
		}
		
		// (사업자) 예약 달력보기 => 안씀 xxx
		public List<ReserveSearchVo> listCalendar(HashMap map) {
			return DBmanager.listCalendar(map);
		}

		
}
