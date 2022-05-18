package com.moran.mapper;

import com.moran.domain.AuthVO;
import com.moran.domain.MemberVO;

public interface MemberMapper {
	MemberVO read(String userid);
	
	int insertMember(MemberVO vo);
	
	int insertAuth(AuthVO vo);
}
