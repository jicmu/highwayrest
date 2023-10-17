package service.member;

import dao.member.MemberDao;
import data.entity.Member;

import net.nurigo.java_sdk.api.Message;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.json.simple.JSONObject;
import sql.Factory;

import java.util.ArrayList;
import java.util.HashMap;


public class MemberService {

	private SqlSessionFactory sqlSessionFactory;

	public MemberService() {
		sqlSessionFactory = Factory.getSqlSessionFactory();
	}

	public void addMember(Member m){
		//커넥션 수립
		SqlSession session = sqlSessionFactory.openSession();//session open
		//dao interface 구현체를 받아옴
		//dao는 기존의 dao객체와 같다
		MemberDao dao = (MemberDao) session.getMapper(MemberDao.class);//session을 통해 맵퍼 객체 획득
		//dao 메서드를 호출하여 db작업 수행
		dao.insert(m);
		//트랜잭션 커밋(쓰기작업에 필요)
		session.commit();
		//커넥션 닫음
		session.close();
	}

	public Member getMember(String id){
		SqlSession session = sqlSessionFactory.openSession();
		MemberDao dao = (MemberDao) session.getMapper(MemberDao.class);
		Member m = dao.select(id);
		session.close();
		return m;
	}

	public Member getMemberByEmail(String email) {
		SqlSession session = sqlSessionFactory.openSession();
		MemberDao dao = (MemberDao) session.getMapper(MemberDao.class);
		Member m = dao.selectByEmail(email);
		session.close();
		return m;
	}

	public Member getMemberByNickname(String nickname) {
		SqlSession session = sqlSessionFactory.openSession();
		MemberDao dao = (MemberDao) session.getMapper(MemberDao.class);
		Member m = dao.selectByNickname(nickname);
		session.close();
		return m;
	}

	public Member getMemberByTel(String tel) {
		SqlSession session = sqlSessionFactory.openSession();
		MemberDao dao = (MemberDao) session.getMapper(MemberDao.class);
		Member m = dao.selectByTel(tel);
		session.close();
		return m;
	}

	public void editMember(Member m){
		SqlSession session = sqlSessionFactory.openSession();
		MemberDao mapper = (MemberDao) session.getMapper(MemberDao.class);
		mapper.update(m);
		session.commit();
		session.close();
	}

	public void delMember(String id){
		SqlSession session = sqlSessionFactory.openSession();
		MemberDao mapper = (MemberDao) session.getMapper(MemberDao.class);
		mapper.delete(id);
		session.commit();
		session.close();
	}

	public ArrayList<Member> getAll(){
		SqlSession session = sqlSessionFactory.openSession();
		MemberDao dao = (MemberDao) session.getMapper(MemberDao.class);
		ArrayList<Member> list = dao.selectAll();
		session.close();
		return list;
	}

	public void certifiedTel(String tel, int randomNumber) {
		String apiKey = "NCS3OWJHKSI1POSQ";
		String apiSecret = "JRZORHDQEKRVH28SLOP2I0IJRV44FSZ1";
		Message sms = new Message(apiKey, apiSecret);

		// 4 params(to, from, type, text) are mandatory. must be filled
		HashMap<String, String> params = new HashMap<>();
		params.put("to", tel); // 수신 번호
		params.put("from", "01066086682"); // 발신 번호
		params.put("type", "SMS");
		params.put("text", "인증번호 " + "["+randomNumber+"]" + "를 화면에 입력해주세요."); // 문자 내용 입력
		params.put("app_version", "test app 1.2");

		try {
			System.out.println("params: " + params);
			JSONObject obj = sms.send(params);
			System.out.println("coolsms: " + obj.toString());
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
			System.out.println("error: " + e.getCause());
		}
	}
}