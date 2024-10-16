package com.example.diet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.diet.entity.SupportEntity;

public interface SupportBoardRepository extends JpaRepository<SupportEntity, Long> {

	
}
