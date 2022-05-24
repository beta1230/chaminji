package domain;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Builder
public class Board {
	// int(기본형), Integer(참조형) : null 처리 가능여부 
	private Long bno ; // PK
	private String title;
	private String content;
	private int hitcount; // 0
	private String regDate;
	private int category;
	
	private String writer; // FK
	// 아이디, 조회수, 작성시각
	
	private List<Attach> attachs = new ArrayList<Attach>();
	
	private int replyCnt;
	
	public Board(String title, String content, String writer) {
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
	
	// 등록작업
	public Board(String title, String content, String writer, int category) {
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.category = category;
	}
	
	// 수정작업
	public Board(Long bno, String title, String content, int category) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.category = category;
	}
	
	// 조회작업
	public Board(Long bno, String title, String content, int hitcount, String regDate, int category, String writer) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.hitcount = hitcount;
		this.regDate = regDate;
		this.category = category;
		this.writer = writer;
	}
	
	
	
	
	
}
