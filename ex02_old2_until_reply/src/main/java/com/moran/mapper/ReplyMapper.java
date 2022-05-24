package com.moran.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.moran.domain.CriteriaReply;
import com.moran.domain.ReplyVO;

public interface ReplyMapper {
	int insert(ReplyVO vo);
	
	int insertSelectKey(ReplyVO vo);
	
	ReplyVO read(Long rno);
	
	int delete(Long rno);

	int update(ReplyVO vo);
	
	List<ReplyVO> getListWithPaging(@Param("bno") Long bno, @Param("cri") CriteriaReply cri);

}
