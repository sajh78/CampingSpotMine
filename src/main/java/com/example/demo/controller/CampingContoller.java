package com.example.demo.controller;

import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	// 6) (사업자) 캠핑룸 삭제하기
	@RequestMapping("/deleteCampingRoom.do")
	public String deleteCampingRoom(int cr_no) {

		String str = "캠핑룸 삭제를 실패하였습니다.";
		int re = cDao.deleteCampingRoom(cr_no);
		if(re > 0) {
			str = "캠핑룸 삭제를 성공하였습니다.";
		}
		System.out.println("캠핑룸 삭제 re:" + re);
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
	public String bossCampingRoomList() {
		String str ="";
		List<CampingRoomVo> crList = cDao.bossCampingRoomList();
		Gson gson = new Gson();
		str = gson.toJson(crList);
		return str;
	}
	
	// 2) (사업자) 캠핑장 목록보기
	@RequestMapping(value = "/bossCampingSpotList.do", produces = "application/json;charset=UTF-8")
	public String bossCampingSpotList() {
		String str ="";
		List<CampingSpotVo> csList = cDao.bossCampingSpotList();
		Gson gson = new Gson();
		str = gson.toJson(csList);
		return str;
	}
	
	@RequestMapping("/insertCampingSpot.do")
	// 1) (사업자) 캠핑장 등록하기
	public String insertCampingSpot(CampingSpotVo csvo, HttpServletRequest request) {
		String str = "캠핑장 등록을 성공하였습니다.";
		
		//이미지 업로드
		//String path = new HttpServletRequestWrapper(request).getRealPath("/");
		//String path = request.getRealPath("resources/static/img");
		String path = "C:\\teamProject\\testCampingSpot\\src\\main\\resources\\static\\img";
		
		System.out.println(path);
		MultipartFile uploadFile = csvo.getUploadFile();
		String cs_licence_fname ="";
		if(uploadFile != null) {
			cs_licence_fname = uploadFile.getOriginalFilename();
			System.out.println("오리지널 fname: " + cs_licence_fname);
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
		
		csvo.setCs_licence_fname(cs_licence_fname);
		
		cDao.insertCampingSpot(csvo);
		return str;
	}
	
}
