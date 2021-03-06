package com.example.demo.db;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.BossListSalesVo;
import com.example.demo.vo.BossReservationVo;
import com.example.demo.vo.CampingReviewReVo;
import com.example.demo.vo.CampingReviewVo;
import com.example.demo.vo.CampingRoomVo;
import com.example.demo.vo.CampingSpotVo;
import com.example.demo.vo.ReserveSearchVo;


public class DBmanager {
	
	public static SqlSessionFactory factory;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("com/example/demo/db/SqlMapConfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// 23) (사업자) 리뷰 댓글 상세보기
	public static CampingReviewReVo getCampingReviewRe(int cre_no) {
		SqlSession session = factory.openSession();
		CampingReviewReVo crrVo = session.selectOne("CampingReview.getCampingReveiwRe", cre_no);
		session.close();
		return crrVo;
	}
	
	// 22) (사업자) 달력 예약 : 예약 완료
	public static List<BossReservationVo> bossListCalendarCP(HashMap map){
		List<BossReservationVo> cpList = null;
		SqlSession session = factory.openSession();
		cpList = session.selectList("reservation.bossListCalendarCP", map);
		session.close();
		return cpList;
	}
	
	// 21) (사업자) 달력 예약 : 예약가능 
	public static List<BossReservationVo> bossListCalendarPS(HashMap map){
		List<BossReservationVo> psList = null;
		SqlSession session = factory.openSession();
		psList = session.selectList("reservation.bossListCalendarPS", map);
		session.close();
		return psList;
	}
	
	// 20) (사업자) 사업자페이지 메인 예약목록
	public static List<BossReservationVo> businessMyPageReservationList(int cs_no) {
		List<BossReservationVo> mpList = null;
		SqlSession session = factory.openSession();
		mpList = session.selectList("reservation.businessMyPageReservationList", cs_no);
		session.close();
		return mpList;
	}
	
	
	// 19) (사업자) 매출 현황 챠트
	public static List<BossListSalesVo> bossChart(int cs_no){
		List<BossListSalesVo> chartList = null;
		SqlSession session = factory.openSession();
		chartList = session.selectList("campingRoom.chart", cs_no);
		session.close();
		return chartList;
	}

	// 18) (사업자) 월별 매출 총합
	public static BossListSalesVo bossListTotalMonth(HashMap map){
		BossListSalesVo svo = null;
		SqlSession session = factory.openSession();
		svo = session.selectOne("campingRoom.totalMonth", map);
		session.close();
		return svo;
	}
	
	// 17) (사업자) 캠핑룸별 매출 총합 list
	public static List<BossListSalesVo> bossListTotalCampingRoom(HashMap map){
		List<BossListSalesVo> salesList = null;
		SqlSession session = factory.openSession();
		salesList = session.selectList("campingRoom.totalByCR", map);
		session.close();
		return salesList;
	}
	
	// 16) (사업자) 매출 현황 목록
	public static List<BossListSalesVo> bossListSales(HashMap map){
		List<BossListSalesVo> salesList = null;
		SqlSession session = factory.openSession();
		salesList = session.selectList("campingRoom.bossListSales", map);
		session.close();
		return salesList;
	}
		
	// 15) (사업자) 취소승인
	public static int updateCancelStatus(int r_no) {
		int re = -1;
		SqlSession session = factory.openSession();
		re = session.update("reservation.updateCstatus", r_no);
		session.commit();
		session.close();
		return re;
	}
	
	// 14) (사업자) 예약승인
	public static int updateReserveStatus(int r_no) {
		int re = -1;
		SqlSession session = factory.openSession();
		re = session.update("reservation.updateRstatus", r_no);
		session.commit();
		session.close();
		return re;
	}
	
	// 13) (사업자) 캠핑 리뷰 댓글 확인
	public static CampingReviewReVo checkReviewRe(int cre_no) {
		CampingReviewReVo crrVo = null;
		SqlSession session = factory.openSession();
		crrVo = session.selectOne("CampingReview.countRepleAtReview", cre_no);
		session.close();
		return crrVo;
	}
	
	// 12) 캠핑 리뷰 댓글 삭제
	public static int bossDeleteCampingReviewRe(int cre_re_no) {
		int re = -1;
		SqlSession session = factory.openSession();
		re = session.insert("CampingReview.deleteCreRe", cre_re_no);
		session.commit();
		session.close();
		return re;
	}
	
	// 11) 캠핑 리뷰 댓글 list
	public static List<CampingReviewReVo> bossCRRList(){
		List<CampingReviewReVo> crrList = null;
		SqlSession session = factory.openSession();
		crrList = session.selectList("CampingReview.bossCRRList");
		session.close();
		return crrList;
	}
	
	// 10) 캠핑 리뷰 답글 번호
	public static int nextNo() {
		int cre_re_no =0;
		SqlSession session = factory.openSession();
		cre_re_no = session.selectOne("CampingReview.nextNo");
		session.close();
		return cre_re_no;
	}
	
	// 9) (사업자) 리뷰 댓글 등록 메소드
	public static int bossInsertCampingReviewRe(CampingReviewReVo crrVo) {
		int re = -1;			
		SqlSession session = factory.openSession();
		re = session.insert("CampingReview.insertCreRe", crrVo);
		session.commit();
		session.close();
		return re;
	}
	
	// 8) (사업자) 리뷰 관리 메소드
	public static List<CampingReviewVo> bossCampingReviewList(int cs_no){
		List<CampingReviewVo> creList = null;
		SqlSession session = factory.openSession();
		creList = session.selectList("CampingReview.bossCampingReviewList",cs_no);
		session.close();
		return creList;
	}
	
	// 7) (사업자) 예약 관리 현황 메소드
	public static List<BossReservationVo> bossReservationList(int cs_no){
		List<BossReservationVo> bossRList = null;
		SqlSession session = factory.openSession();
		bossRList = session.selectList("reservation.bossReservationList", cs_no);
		session.close();
		return bossRList;
 	}
	
	// 6) (사업자) 캠핑룸 삭제 메소드
	public static int deleteCampingRoom(int cr_no) {
		int re = -1;
		SqlSession session = factory.openSession();
		re = session.delete("campingRoom.deleteCampingRoom", cr_no);
		session.commit();
		session.close();
		return re;
	}
	
	// 5) (사업자) 캠핑룸 업데이트 메소드
	public static int updateCampingRoom(CampingRoomVo crvo) {
		int re = -1;			
		SqlSession session = factory.openSession();
		re = session.update("campingRoom.updateCampingRoom", crvo);
		session.commit();
		session.close();
		return re;
	}
	
	// 4) (사업자) 캠핑룸 등록 메소드
	public static int insertCampingRoom(CampingRoomVo crvo) {
		int re = -1;			
		SqlSession session = factory.openSession();
		re = session.insert("campingRoom.insertCampingRoom", crvo);
		session.commit();
		session.close();
		return re;
	}
	
	// 3) (사업자) 캠핑룸 목록 메소드
	public static List<CampingRoomVo> bossCampingRoomList(int cs_no){
		List<CampingRoomVo> crList = null;
		SqlSession session = factory.openSession();
		crList = session.selectList("campingRoom.listCampingRoom", cs_no);
		session.close();
		return crList;
 	}
	
	// 2) (사업자) 캠핑장 정보 상세 
	public static CampingSpotVo bossGetCampingSpot(int cs_no) {
		SqlSession session = factory.openSession();
		CampingSpotVo csVo = session.selectOne("campingSpot.bossGetCampingSpot", cs_no);
		session.close();
		return csVo;
	}
	
	// 1) (사업자) 캠핑장 등록 메소드
	public static int insertCampingSpot(CampingSpotVo csvo) {
		int re = -1;
		SqlSession session = factory.openSession();
		re = session.insert("campingSpot.insertCampingSpot", csvo);
		session.commit();
		session.close();
		return re;	
	}	
	
// ================================================================================================	
	
	// (사업자) 예약정보 상세 => 사용안함
	public static BossReservationVo getBossReservationList(int r_no) {
		SqlSession session = factory.openSession();
		BossReservationVo bossRvo = session.selectOne("reservation.getBossReservationList", r_no);
		session.close();
		return bossRvo;
	}
	
	// (사업자) 캠핑장 목록 => 사용안함 xxx
	public static List<CampingSpotVo> bossCampingSpotList(){
		List<CampingSpotVo> csList = null;
		SqlSession session = factory.openSession();
		csList = session.selectList("campingSpot.bossCampingSpotList");
		session.close();
		return csList;
 	}
	
	// (사업자) 리뷰 댓글 목록보기
	public static List<CampingReviewReVo> ListCampingReviewRe(String mb_id){
		List<CampingReviewReVo> crrList = null;
		SqlSession session = factory.openSession();
		crrList = session.selectList("CampingReview.ListCRR", mb_id);
		session.close();
		return crrList;
	}
	
	// (사업자) 예약 달력 목록
	public static List<ReserveSearchVo> listCalendar(HashMap map) {
		List<ReserveSearchVo> rsList = null;
		SqlSession session = factory.openSession();
		rsList = session.selectList("", "");
		return rsList;
	}
}
