package com.moran.service;

import java.util.List;

import com.moran.domain.BoardAttachVO;
import com.moran.domain.BoardVO;
import com.moran.domain.Criteria;

public interface BoardService {
	int register(BoardVO boardVO);
	
	BoardVO get(Long bno);
	
	boolean modify(BoardVO boardVO);
	
	boolean remove(Long bno);
	
	List<BoardVO> getList(Criteria cri);

	int getTotalCount(Criteria cri);
	
	List<BoardAttachVO> getAttachs(Long bno);
}
