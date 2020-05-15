package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ReservationDao;
import com.example.demo.vo.BossReservationVo;
import com.google.gson.Gson;

@RestController
public class ReservationController {
	
	@Autowired
	private ReservationDao rDao;

	public void setrDao(ReservationDao rDao) {
		this.rDao = rDao;
	}
	
	// 1) (사업자) 예약 관리 현황 목록보기
	@RequestMapping(value ="/bossReservationList.do", produces = "application/json;charset=UTF-8" )
	public String bossReservationList() {
		String str = "";
		List<BossReservationVo> bossRList = rDao.bossReservationList();
		Gson gson = new Gson();
		str = gson.toJson(bossRList);
		return str;
	}

}
