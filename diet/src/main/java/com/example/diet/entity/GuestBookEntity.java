package com.example.diet.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="guestbook13")
/*
	Spring Data JPA의 Auditing 기능을 사용하기 위해 엔티티 클래스에 추가하는 어노테이션
	엔티티의 생성 및 수정 시점에 자동으로 특정 필드(예: 생성일, 수정일 등)를 업데이트해주는 역할
 */
@EntityListeners(AuditingEntityListener.class)
public class GuestBookEntity {
	//글번호
	@Id
	//MySQL의 AUTO_INCREMENT 방식을 이용
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="num")
	private Integer num;

	//이름
	@Column(name="name", nullable = false, length = 100)
	String name;
	
	//비밀번호
	@Column(name="password", nullable = false, length = 100)
	String password;
	
	//글내용
	//columnDefinition 속성은 기본적으로 JPA가 데이터베이스의 타입을 자동으로 매핑하는 대신, 명시적으로 데이터베이스 컬럼 정의를 지정해야 할 때 사용
	@Column(name="message", nullable = false, columnDefinition = "text")
	String message;
	
	//작성시간
	//@CreatedDate 엔티티가 생성된 날짜와 시간을 자동으로 저장하기 위해 사용
	//주로 LocalDateTime, LocalDate, Date 타입의 필드와 함께 사용
	@CreatedDate
	@Column(name="inputdate")
	LocalDateTime inputdate;
}
