package dao;

import java.util.List;

import model.Member;

public interface IMemberDao {

	public int insertMember(Member member);

	// 회원가입 시 가입된 이메일인지 중복체크를 위해 확인한다
	public int checkMember(String mEmail);

	// 로그인, 탈퇴 시 비번이랑 맞는지 체크한다.
	public Member selectMember(int mId);

	// 주어진 이메일과 pwd일치하는지 확인하기 위해 pwd를 가져온다
	public String checkPassword(String email);

	public Member selectMemberFromEmail(String email);

	// 회원탈퇴 시 정보를 삭제한다. (isWithdraw값을 1로 변경)
	public int deleteMember(int mId);
	
	//모든 회원정보를 가져온다(메인페이지)
	public List<Member> selectAllMember();

}
