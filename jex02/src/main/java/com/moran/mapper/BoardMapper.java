package com.moran.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.moran.domain.BoardVO;
import com.moran.domain.Criteria;

public interface BoardMapper {
//	@Select("SELECT * FROM TBL_BOARD WHERE BNO > 0")
	public List<BoardVO> getList();

	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public BoardVO read(Long bno);
	
//	@Insert("INSERT INTO TBL_BOARD(BNO,TITLE, CONTENT, WRITER) "
//			+ "VALUES (SEQ_BOARD.NEXTVAL, #{title}, #{content},#{writer})")
	public int insert(BoardVO boardVO);
	
	public int insertSelectKey(BoardVO boardVO);
	
	public int delete(Long bno);
	
	public int update(BoardVO boardVO);

	public int getTotalCount(Criteria cri);
	
	public List<BoardVO> getListDynamicTest(Criteria cri);
	
	void updateReplyCnt(@Param("bno") long bno, @Param("amount") int amount);
}
