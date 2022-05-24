package com.moran.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.moran.domain.BoardVO;
import com.moran.domain.Criteria;
import com.moran.domain.PageDTO;
import com.moran.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("board/*")
@AllArgsConstructor
@Log4j
public class BoardController {
	private final BoardService boardService;
	
	@GetMapping("list")
	public String list(Model model, Criteria cri) {
		model.addAttribute("boards", boardService.getList(cri));
		model.addAttribute("page", new PageDTO(boardService.getTotalCount(cri), cri));
		return "/board/list";
	}

	@GetMapping("list2") @ResponseBody
	public List<BoardVO> list(Criteria cri) {
		return boardService.getList(cri);
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("register")
	public void register(@ModelAttribute("cri") Criteria cri) {
	}
	
	@PreAuthorize("isAuthenticated() and principal.username == #boardVO.writer")
	@PostMapping("register")
	public String register(BoardVO boardVO, RedirectAttributes rttr, Criteria cri) {
		boardService.register(boardVO);
		rttr.addFlashAttribute("result", boardVO.getBno());
		rttr.addAttribute("pageNum", 1);
		rttr.addAttribute("amount", cri.getAmount());		
		rttr.addAttribute("type", cri.getType());		
		rttr.addAttribute("keyword", cri.getKeyword());		
		return "redirect:/board/list";
	}
	
	@GetMapping({"get", "modify"})
	public void get(Long bno, Criteria cri, Model model) {
		model.addAttribute("board", boardService.get(bno));
		model.addAttribute("cri", cri);
	}
	
	@PreAuthorize("isAuthenticated() and principal.username == #boardVO.writer")
	@PostMapping("modify")
	public String modify(BoardVO boardVO, RedirectAttributes rttr, Criteria cri) {
		log.info(cri);
		if(boardService.modify(boardVO)) {			
			rttr.addFlashAttribute("result", "수정");
		}		
		return "redirect:/board/list" + cri.getParams();
	}
	
	@PreAuthorize("isAuthenticated() and principal.username == #writer")
	@PostMapping("remove")
	public String remove(Long bno, RedirectAttributes rttr, Criteria cri, String writer) {
		log.info(cri);
		if(boardService.remove(bno)) {			
			rttr.addFlashAttribute("result", "삭제");
		}
		return "redirect:/board/list"  + cri.getParams();
	}
	
	
}
