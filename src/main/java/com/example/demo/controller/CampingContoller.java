package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.dao.CampingDao;
import com.example.demo.vo.BossListSalesVo;
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
	
	// 10) (사업자) 매출 현황 챠트
	@RequestMapping(value = "/bossChart.do", produces = "application/json;charset=UTF-8")
	public String bossChart(int cs_no) {
		String str = "";
		List<BossListSalesVo> chartList = cDao.bossChart(cs_no);
		Gson gson = new Gson();
		str = gson.toJson(chartList);
		return str;
	}
	
	// 9) (사업자) 월별 매출 총합
	@RequestMapping(value = "/bossListTotalMonth.do", produces = "application/json;charset=UTF-8")
	public String bossListTotalMonth(int cs_no, String month) {
		String str = "";
		HashMap map = new HashMap();
		map.put("cs_no", cs_no);
		map.put("month", month);
		BossListSalesVo svo = cDao.bossListTotalMonth(map);
		Gson gson = new Gson();
		str = gson.toJson(svo);
		return str;
	}
	
	// 8) (사업자) 캠핑물별 매출 총합 list
	@RequestMapping(value = "/bossListTotalCampingRoom.do", produces = "application/json;charset=UTF-8")
	public String bossListTotalCampingRoom(int cs_no, String month) {
		String str = "";
		HashMap map = new HashMap();
		map.put("cs_no", cs_no);
		map.put("month", month);
		List<BossListSalesVo> totalList = cDao.bossListTotalCampingRoom(map);
		Gson gson = new Gson();
		str = gson.toJson(totalList);
		return str;
	}
	
	// 7) (사업자) 매출 현황 목록보기
	@RequestMapping(value = "/bossListSales.do", produces = "application/json;charset=UTF-8")
	public String bossListSales(int cs_no, String month) {
		String str ="";
		HashMap map = new HashMap();
		map.put("cs_no", cs_no);
		map.put("month", month);
		List<BossListSalesVo> salesList = cDao.bossListSales(map);
		
		for(int i =0; i <salesList.size(); i++ ) {
			BossListSalesVo svo =  salesList.get(i);
			int r_cancel = svo.getR_cancel();
			int r_price = svo.getR_price();
			if(r_cancel > 0) {
				svo.setR_cancel(r_price);
				svo.setR_price(0);
				svo.setComm(0);
				svo.setMargin(0);
				salesList.set(i, svo);
			}else {
				svo.setR_cancel(0);
				salesList.set(i, svo);
			}
		}

		Gson gson = new Gson();
		str = gson.toJson(salesList);
		return str;
	}
	
	// 6) (사업자) 캠핑룸 삭제하기
	@RequestMapping("/deleteCampingRoom.do")
	public String deleteCampingRoom(int cr_no) {

		String str = "캠핑룸 삭제를 실패하였습니다.";
		int re = cDao.deleteCampingRoom(cr_no);
		if(re > 0) {
			str = "캠핑룸 삭제를 성공하였습니다.";
		}
		//System.out.println("캠핑룸 삭제 re:" + re);
		return str;
	}

	// 5) (사업자) 캠핑룸 수정하기
	@RequestMapping("/updateCampingRoom.do")
	public String updateCampingRoom(CampingRoomVo crvo) {
		String str = "캠핑룸 수정을 실패하였습니다.";
		int re = cDao.updateCampingRoom(crvo);
		if(re > 0) {
			str = "캠핑룸 수정을 성공하였습니다.";
		}
		System.out.println("캠핑룸 수정 re:" + re);
		return str;
	}
	
	// 4) (사업자) 캠핑룸 등록하기
	@RequestMapping("/insertCampingRoom.do")
	public String insertCampingRoom(CampingRoomVo crvo) {
		String str = "캠핑룸 등록을 성공하였습니다.";
		cDao.insertCampingRoom(crvo);
		return str;
	}
	
	// 3) (사업자) 캠핑룸 목록보기
	@RequestMapping(value = "/bossCampingRoomList.do", produces = "application/json;charset=UTF-8")
	public String bossCampingRoomList(int cs_no) {
		String str ="";
		List<CampingRoomVo> crList = cDao.bossCampingRoomList(cs_no);
		Gson gson = new Gson();
		str = gson.toJson(crList);
		return str;
	}
	
	// 2) (사업자) 캠핑장 상세보기
	@RequestMapping(value = "/bossGetCampingSpot.do", produces = "application/json;charset=UTF-8")
	public String bossGetCampingSpot(int cs_no) {
		String str = "";
		CampingSpotVo csVo = cDao.bossGetCampingSpot(cs_no);
		Gson gson = new Gson();
		str = gson.toJson(csVo);
		return str;
	}
	
	// 1) (사업자) 캠핑장 등록하기

	//public String insertCampingSpot(CampingSpotVo csvo, HttpServletRequest request) {
	@RequestMapping("/insertCampingSpot.do")
	public String insertCampingSpot(CampingSpotVo csvo, MultipartHttpServletRequest request) {		
		
		String str = "캠핑장 등록을 성공하였습니다.";
		
		//1.사업자등록증 업로드
		//String path = new HttpServletRequestWrapper(request).getRealPath("/");
		//String path = request.getRealPath("resources/static/img");
		String path = "C:\\teamProject\\testCampingSpot\\src\\main\\resources\\static\\img";
		
		System.out.println("사업자등록증 경로:" + path);
		MultipartFile uploadFile = csvo.getUploadFile();
		String cs_licence_fname =""; 	// 사업자등록증 파일이름
				
		if(uploadFile != null) {
			cs_licence_fname = uploadFile.getOriginalFilename();
			System.out.println("사업자등록증 fname:" + cs_licence_fname);

			try {
				byte []data = uploadFile.getBytes();
				FileOutputStream fos = new FileOutputStream(path + "/" + cs_licence_fname);
				//FileOutputStream fos = new FileOutputStream(path + cs_licence_fname);
				//System.out.println(fos);
				fos.write(data);
				fos.close();
								
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		//2.캠핑장이미지 업로드 
			
		String Cpath = "C:\\teamProject\\testCampingSpot\\src\\main\\resources\\static\\img";
		System.out.println("캠핑장이미지 경로: " +Cpath);

		MultipartFile CampingUploadFile = csvo.getCampingUploadFile();
		
		// 캠핑장 파일 이름들을 넣어 둘 변수
		String cs_camp_fnames =""; 	
		
		List<MultipartFile> campList = request.getFiles("CampingUploadFile");
		for (int i=0; i <campList.size(); i++) {
			MultipartFile multiF = campList.get(i);
			String cs_camp_fname = multiF.getOriginalFilename();
			cs_camp_fnames += cs_camp_fname;
			
			File outFile = new File(Cpath + "/" + cs_camp_fname);
			try {
				multiF.transferTo(outFile);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			// ,로 구분하기 위해
			if(i < campList.size()-1) {
				cs_camp_fnames += ",";
			}
		}
		
		csvo.setCs_camp_fname(cs_camp_fnames);
		System.out.println(csvo.getCs_camp_fname());
		
		String arr[] = csvo.getCs_camp_fname().split(",");
		System.out.println(arr);
		
/*// 업로드 1개만 될 때
		MultipartFile CampingUploadFile = csvo.getCampingUploadFile();
		String cs_camp_fname =""; 	// 캠핑장이미지 파일이름
		
		if(CampingUploadFile != null) {
			
			cs_camp_fname = CampingUploadFile.getOriginalFilename();
			System.out.println("캠핑장 fname:" + cs_camp_fname);
			
			try {
				byte []cdata = CampingUploadFile.getBytes();
				FileOutputStream cfos = new FileOutputStream(Cpath + "/" + cs_camp_fname);

				cfos.write(cdata);
				cfos.close();
								
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
*/
		
		// 3.지도 이미지 업로드
		String Mpath = "C:\\teamProject\\testCampingSpot\\src\\main\\resources\\static\\img";
		System.out.println("지도이미지 경로: " + Mpath);
		
		MultipartFile MapUploadFile = csvo.getMapUploadFile();
		String cs_map_fname = "";
		
		if(MapUploadFile != null) {
			cs_map_fname = MapUploadFile.getOriginalFilename();
			System.out.println("약도 fname:" + cs_map_fname);
			try {
				byte []mdata = MapUploadFile.getBytes();
				FileOutputStream mfos = new FileOutputStream(Mpath + "/" + cs_map_fname);
				
				mfos.write(mdata);
				mfos.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		csvo.setCs_licence_fname(cs_licence_fname);
		//csvo.setCs_camp_fname(cs_camp_fname);
		csvo.setCs_map_fname(cs_map_fname);
		
		cDao.insertCampingSpot(csvo);
		return str;
	}
	
// ================================================================================================	
	
	// (사업자) 캠핑장 목록보기 => 사용안함 xxx
	@RequestMapping(value = "/bossCampingSpotList.do", produces = "application/json;charset=UTF-8")
	public String bossCampingSpotList() {
		String str ="";
		List<CampingSpotVo> csList = cDao.bossCampingSpotList();
		Gson gson = new Gson();
		str = gson.toJson(csList);
		return str;
	}
}
