package com.moran.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moran.domain.BoardAttachVO;
import com.moran.domain.BoardVO;
import com.moran.domain.Criteria;
import com.moran.mapper.BoardAttachMapper;
import com.moran.mapper.BoardMapper;
import com.moran.mapper.ReplyMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service @AllArgsConstructor
@Log4j
public class BoardServiceImpl implements BoardService{
	private BoardMapper boardMapper;
	private BoardAttachMapper boardAttachMapper;
	private ReplyMapper replyMapper;
	
	@Transactional
	public int register(BoardVO boardVO) {
		log.info("register("+boardVO+")");
		// boardVO.bno == null
		int result = boardMapper.insertSelectKey(boardVO);
		
		boardVO.getAttachs().forEach(attach -> {
			attach.setBno(boardVO.getBno());
			boardAttachMapper.insert(attach);
		});
		// boardVO.bno == not null
		return result;
	}

	@Override
	public BoardVO get(Long bno) {
		return boardMapper.read(bno);
	}

	@Override
	@Transactional
	public boolean modify(BoardVO boardVO) {
		// 게시글의 첨부파일 일괄삭제
		boardAttachMapper.deleteAll(boardVO.getBno());
		
		// 첨부파일 재등록
		boardVO.getAttachs().forEach(attach -> {
			attach.setBno(boardVO.getBno());
			boardAttachMapper.insert(attach);
		});
		
		return boardMapper.update(boardVO) > 0;
	}

	@Override
	@Transactional
	public boolean remove(Long bno) {
		replyMapper.deleteAll(bno);
		boardAttachMapper.deleteAll(bno);
		return boardMapper.delete(bno) > 0;
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		return boardMapper.getListWithPaging(cri);
	}

	@Override
	public int getTotalCount(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.getTotalCount(cri);
	}

	@Override
	public List<BoardAttachVO> getAttachs(Long bno) {
		// TODO Auto-generated method stub
		return boardAttachMapper.findBy(bno);
	}
	
}
