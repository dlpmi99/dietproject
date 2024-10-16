package com.example.diet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dsa.web5.dto.MemberDTO;
import com.example.diet.entity.MemberEntity;
import com.example.diet.repository.MemberRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class MemberServiceImpl implements MemberService {

	private final MemberRepository mr;
	// 암호화
	private final BCryptPasswordEncoder passwordEncoder;
	
   @Autowired
	public MemberServiceImpl(MemberRepository mr, BCryptPasswordEncoder passwordEncoder) {
		this.mr = mr;
		this.passwordEncoder = passwordEncoder;
	}


	/**
	 * 가입시 아이디 중복 확인 처리
	 * @param searchId
	 * @return 해당 아이디로 가입 가능 여부 true / false
	 */
	@Override
	public boolean idCheck(String searchId) {
		return !mr.existsById(searchId);
	}

	/**
	 * 회원가입 처리
	 * @param MemberDTO 가입 데이터
	 */
	@Override
	public void join(MemberDTO member) {
		MemberEntity entity = MemberEntity.builder()
				.memberId(member.getMemberId())
				.memberPassword(
					passwordEncoder.encode(member.getMemberPassword())
				)
				.memberName(member.getMemberName())
				.email(member.getEmail())
				.phone(member.getPhone())
				.address(member.getAddress())
//				.enabled(true)
//				.rolename("ROLE_USER")
				.build();
		mr.save(entity);
	}

	@Override
	public MemberDTO getMember(String username) {
		
		MemberEntity entity = mr.findById(username)
				.orElseThrow(() -> new EntityNotFoundException(
					username + " : 아이디가 없습니다."));
		
		MemberDTO dto = MemberDTO.builder()
						.memberId(entity.getMemberId())
						.memberPassword(entity.getMemberPassword())
						.memberName(entity.getMemberName())
						.email(entity.getEmail())
						.phone(entity.getPhone())
						.address(entity.getAddress())
						.enabled(entity.getEnabled())
						.rolename(entity.getRolename())
						.build();
		return dto;
	}

	/**
	 * 개인정보 수정
	 * @param MemberDTO
	 */
	@Override
	public void update(MemberDTO memberDTO) {
		MemberEntity entity = mr.findById(memberDTO.getMemberId())
				.orElseThrow(() -> new EntityNotFoundException(
						memberDTO.getMemberId() + " : 아이디가 없습니다."));
		
		if (!memberDTO.getMemberPassword().isEmpty()) {
			entity.setMemberPassword(
				passwordEncoder.encode(memberDTO.getMemberPassword())
			);
		}
		
		entity.setMemberName(memberDTO.getMemberName());
		entity.setEmail(memberDTO.getEmail());
		entity.setPhone(memberDTO.getPhone());
		entity.setAddress(memberDTO.getAddress());
		mr.save(entity);
	}
	
	
	
}
