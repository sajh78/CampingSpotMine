package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBmanager;
import com.example.demo.vo.CampingReviewReVo;
import com.example.demo.vo.CampingReviewVo;

@Repository
public class CampingReviewDao {
	
	// 6) 캠핑 리뷰 댓글 확인
	public CampingReviewReVo checkReviewRe(int cre_no) {
		return DBmanager.checkReviewRe(cre_no);
	}
	
	// 5) 캠핑 리뷰 댓글 삭제
	public int bossDeleteCampingReviewRe(int cre_re_no) {
		return DBmanager.bossDeleteCampingReviewRe(cre_re_no);
	}
	
	// 4) 캠핑 리뷰 댓글 list
	public List<CampingReviewReVo> bossCRRList(){
		return DBmanager.bossCRRList();
	}

	// 3) 캠핑 리뷰 댓글 번호
	public int nextNo() {
		return DBmanager.nextNo();
	}

	// 2) (사업자) 리뷰 댓글 등록
	public int bossInsertCampingReviewRe(CampingReviewReVo crrVo) {
		return DBmanager.bossInsertCampingReviewRe(crrVo);
	}
	
	// 1) (사업자) 리뷰 관리 목록
	public List<CampingReviewVo> bossCampingReviewList(){
		return DBmanager.bossCampingReviewList();
	}
}
