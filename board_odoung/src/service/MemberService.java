package service;

import dao.BoardDao;
import dao.MemberDao;
import dao.ReplyDao;
import domain.Member;

public class MemberService {
	private static MemberService memberService = new MemberService();
	public static MemberService getInstance() {
		return memberService;
	}
	private MemberService() {}
	
	private MemberDao memberDao = MemberDao.getInstance();
//	private BoardDao boardDao = BoardDao.getInstance();
//	private ReplyDao replyDao = ReplyDao.getInstance();
	
	public void register(Member member) {
		memberDao.register(member);
	}
	public Member login(Member member) {
		return memberDao.login(member.getId(), member.getPw());
	}
	
	public Member get(String id) {
		return memberDao.get(id);
	}
	
	public Member findBy(String email) {
		return memberDao.findBy(email);
	}
	public void updateAuthToken(Member member) {
		memberDao.updateAuthToken(member);
		
	}
	public void updateAuth(Member member) {
		memberDao.updateAuth(member);
		
	}
	public void modify(Member member) {
		memberDao.modify(member);		
	}
	public void remove(Member member) {
//		boardDao.modifyNullByWriter(member.getId());
//		replyDao.modifyNullByWriter(member.getId());
		memberDao.remove(member.getId());
		
	}
	
}
