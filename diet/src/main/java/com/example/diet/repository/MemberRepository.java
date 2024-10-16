package com.example.diet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.diet.entity.MemberEntity;


@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String> {

}
