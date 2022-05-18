package com.moran.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.moran.domain.MemberVO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("member")
@Log4j
public class MemberController {
	
	@GetMapping("login")
	public void login(@ModelAttribute String error, @ModelAttribute String logout) {
		
	}
	
	@GetMapping("join")
	public void join() {
		
	}
	
	@PostMapping("join")
	public void join(MemberVO vo) {
		log.info(vo);
	}
	
	@PreAuthorize("isFullyAuthenticated()")
	@GetMapping("mypage")
	public void mypage() {
		
	}
	
	@GetMapping("juso")
	public void getAddrApi(String keyword, HttpServletResponse response) throws Exception {
		// 요청변수 설정
		String resultType = "json";      //요청 변수 설정 (검색결과형식 설정, json)
		String confmKey = "U01TX0FVVEgyMDIyMDUwOTE2MTYzNDExMjU0MTg=";   //요청 변수 설정 (승인키)
		// OPEN API 호출 URL 정보 설정
		String apiUrl = "https://www.juso.go.kr/addrlink/addrLinkApi.do?"
		+"keyword="+URLEncoder.encode(keyword,"UTF-8")+"&confmKey="+confmKey+"&resultType="+resultType;
		URL url = new URL(apiUrl);
    	BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
    	StringBuffer sb = new StringBuffer();
    	String tempStr = null;

    	while(true){
    		tempStr = br.readLine();
    		if(tempStr == null) break;
    		sb.append(tempStr);								// 응답결과 JSON 저장
    	}
    	br.close();
    	response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().write(sb.toString());			// 응답결과 반환
    }
	
	
}
