package com.example.diet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dsa.web5.dto.MemberDTO;
import com.example.diet.AuthenticatedUser;
import com.example.diet.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("member")
public class MemberController {

	private final MemberService ms;
	
	public MemberController(MemberService ms) {
		this.ms = ms;
	}

	/**
	 * 요청시 회원가입 페이지로 이동
	 * @return joinForm.html
	 */
	@GetMapping("join")
	public String join() {
		return "member/joinForm";
	}
	
	/**
	 * 회원가입페이지에서 "ID중복확인" 버튼을 클릭하면 새창으로 보여줄 검색 페이지 이동
	 * @return idCheck.html
	 */
	@GetMapping("idCheck")
	public String idCheck() {
		return "member/idCheck";
	}
	
	/**
	 * ID중복확인 페이지에서 검색 요청했을때 처리
	 * @param searchId 검색할 아이디
	 * @return idCheck.html
	 */
	@PostMapping("idCheck")
	public String idCheck(@RequestParam("searchId") String searchId
			, Model model) {
		
		boolean result = ms.idCheck(searchId);
		
		model.addAttribute("result", result);
		model.addAttribute("searchId",  searchId);
		
		return "member/idCheck";
	}
	
	/**
	 * 회원가입 정보를 받아 회원가입 처리
	 * @param member
	 * @return
	 */
	@PostMapping("join")
	public String join(@ModelAttribute MemberDTO member) {
		log.debug("전달된 회원가입정보: {}", member);
		ms.join(member);
		return "redirect:/";
	}
	
	/**
	 * 로그인 페이지로 이동
	 * @return loginForm.html
	 */
	@GetMapping("loginForm")
	public String loginForm() {
		log.info("member/loginForm>>>>>>>>");
		return "member/loginForm";
	}
	
	/**
	 * 개인정보 수정 폼으로 이동
	 * @param user 로그인한 사용자 정보(인증 정보)
	 * @param Model
	 * @return infoForm.html
	 */
	@GetMapping("info")
	public String info(@AuthenticationPrincipal AuthenticatedUser user
			, Model model) {
		log.debug("현재 접속중인 유저의 ID: {}", user.getUsername());
		
		MemberDTO memberDTO = ms.getMember(user.getUsername());
		
		model.addAttribute("member", memberDTO);
		
		return "member/infoForm";
	}
	
	/**
	 * 개인정보수정
	 * @param user 인증정보
	 * @param memberDTO 수정폼에서 입력한 값
	 * @return home.html
	 */
	@PostMapping("info")
	public String info(@AuthenticationPrincipal AuthenticatedUser user
			, @ModelAttribute MemberDTO memberDTO) {
		
		memberDTO.setMemberId(user.getUsername());
		
		ms.update(memberDTO);
		
		return "redirect:/";
	}
	
	
	
	
}
