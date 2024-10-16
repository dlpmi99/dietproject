package com.example.diet.service;

import com.dsa.web5.dto.MemberDTO;

public interface MemberService {

	boolean idCheck(String searchId);

	void join(MemberDTO member);

	MemberDTO getMember(String username);

	void update(MemberDTO memberDTO);

}
