package service;

import dto.Member;

public interface MemberService {
	Member login(String id, String password) throws Exception;
	void join(Member member) throws Exception;
	
	void memberchange(Member member) throws Exception;

	String idforget(String email) throws Exception;
	String pwforget(String id, String email) throws Exception;
}
