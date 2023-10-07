package dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.Member;
import util.MybatisSqlSessionFactory;

public class MemberDAOImpl implements MemberDAO {
	SqlSession sqlSession = MybatisSqlSessionFactory.getSqlSessionFactory().openSession();

	@Override
	public void insertMember(Member member) throws Exception {
		sqlSession.insert("mapper.member.insertMember", member);
		sqlSession.commit();

	}

	@Override
	public Member selectMember(String id) throws Exception {
		return sqlSession.selectOne("mapper.member.selectMember", id);
	}
	
	@Override
	public void updateMember(Member member) throws Exception {
		sqlSession.update("mapper.member.updateMember", member);
		sqlSession.commit();
	}

	@Override
	public String selectMemberId(String email) throws Exception {
		return sqlSession.selectOne("mapper.member.selectMemberId", email);
	}

	@Override
	public String selectMemberPw(String id, String email) throws Exception {
		Member member = selectMember(id);
		if(member == null) throw new Exception("아이디가 존재하지 않습니다.");
		return sqlSession.selectOne("mapper.member.selectMemberPw", email);
	}

	
}
