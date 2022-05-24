package domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDto {
	private static final int PAGE_COUNT = 10;
	
	private int start; // 시작 숫자
	private int end; // 종료 숫자
	private int total; // 게시글 총 갯수
	private boolean next; // 다음 페이지 가능 여부
	private boolean prev; // 이전 페이지 가능 여부
	private Criteria cri; // pageNum, amount
	
	
	public PageDto(int total, Criteria cri) {
		super();
		this.total = total;
		this.cri = cri;
		
		// 1 10
		// 2 10
		// 3 10
		// .....
		// 11 20
		// .....
		// 21 30
		
		end = (cri.getPageNum() +(PAGE_COUNT-1)) / PAGE_COUNT * PAGE_COUNT;						
		start = end - PAGE_COUNT + 1;
		
		
//		end = (total + (PAGE_COUNT-1)) / cri.getAmount();		
		int realEnd = (total + (cri.getAmount()-1)) / cri.getAmount();
		
		if(realEnd < end) {
			end = realEnd;
		}
		
		prev = cri.getPageNum() > 1;
		next = cri.getPageNum() < realEnd;
				
				
	}
	
	public static void main(String[] args) {
//		int i = 11;
//		int result = (i + 9) / 10 * 10;
//		
//		System.out.printf("%10s %10s %10s %10s %n", "origin", "floor", "round", "ceil");
//		for(int j = 1 ; j <= 30 ; j ++) {
//			System.out.printf("%10d %10d %10d %10d %n", j, j/10*10, (j+5)/10*10, (j+9)/10*10);
//		}
//		System.out.println(result);
		
		PageDto dto = new PageDto(190, new Criteria(18,10,1));
		System.out.println(dto);
	}

}
