package com.example.demo.db;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.CampingRoomVo;
import com.example.demo.vo.CampingSpotVo;


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
	public static List<CampingRoomVo> bossCampingRoomList(){
		List<CampingRoomVo> crList = null;
		SqlSession session = factory.openSession();
		crList = session.selectList("campingRoom.listCampingRoom");
		session.close();
		return crList;
 	}
	
	// 2) (사업자) 캠핑장 목록 메소드
	public static List<CampingSpotVo> bossCampingSpotList(){
		List<CampingSpotVo> csList = null;
		SqlSession session = factory.openSession();
		csList = session.selectList("campingSpot.bossCampingSpotList");
		session.close();
		return csList;
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
	
}
