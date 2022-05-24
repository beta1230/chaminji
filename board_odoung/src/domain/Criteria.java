package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO

@Data @NoArgsConstructor @AllArgsConstructor
public class Criteria {
	private int pageNum = 1; // 페이지 번호
	private int amount = 10; // 페이지당 게시글 수 
	private int category = 1;
	
	
	// 사과가 123개 10개씩 들어감 몇개의 바구니가 필요?
	// (총 게시글 갯수 + 9) / 어마운트
	
	public String getParams2() {
		return getParams()+ "&pageNum=" +pageNum;
	}

	public String getParams() {
		return  "?amount="+amount +
				"&category="+category;
	}

}
