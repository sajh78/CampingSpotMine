package com.example.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ReservationDao;
import com.example.demo.vo.BossReservationVo;
import com.example.demo.vo.ReserveSearchVo;
import com.google.gson.Gson;

@RestController
public class ReservationController {
	
	@Autowired
	private ReservationDao rDao;

	public void setrDao(ReservationDao rDao) {
		this.rDao = rDao;
	}
	
	// 4) (사업자) 예약 달력 목록
	@RequestMapping(value = "/listCalendar.do", produces = "application/json;charset=UTF-8")
	public String listCalendar(Date rs_date, int cr_no) {
		String str ="";
		HashMap map = new HashMap<>();
		map.put("rs_date", rs_date);
		map.put("cr_no", cr_no);
		List<ReserveSearchVo> rsList = rDao.listCalendar(map);
		Gson gson = new Gson();
		str = gson.toJson(rsList);
		return str;
	}
	
	// 3) (사업자) 취소 승인 업데이트
	@RequestMapping("/updateCancelStatus.do")
	public String updateCancelStatus(int r_no) {
		String str ="취소 승인되었습니다.";
		int re = rDao.updateCancelStatus(r_no);
		System.out.println("취소승인:"+re);
		return str;
	}
	
	// 2) (사업자) 예약 승인 업데이트
	@RequestMapping("/updateReserveStatus.do")
	public String updateReserveStatus(int r_no) {
		String str ="예약 승인되었습니다.";
		int re = rDao.updateReserveStatus(r_no);
		System.out.println("예약승인:"+re);
		return str;
	}
	
	// 1) (사업자) 예약 관리 현황 목록보기
	@RequestMapping(value ="/bossReservationList.do", produces = "application/json;charset=UTF-8" )
	public String bossReservationList(int cs_no) {
		String str = "";
		List<BossReservationVo> bossRList = rDao.bossReservationList(cs_no);
		Gson gson = new Gson();
		str = gson.toJson(bossRList);
		return str;
	}
	
	// (사업자) 예약 상세정보 => 사용안함 xxx
	@RequestMapping(value = "/getBossReservationList.do", produces = "application/json;charset=UTF-8")
	public BossReservationVo getBossReservationList(int r_no) {
		BossReservationVo brvo = rDao.getBossReservationList(r_no);
		return brvo;
	}

}
