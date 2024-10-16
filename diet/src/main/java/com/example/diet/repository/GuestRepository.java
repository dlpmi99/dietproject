package com.example.diet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.diet.entity.GuestBookEntity;



public interface GuestRepository extends JpaRepository<GuestBookEntity, Integer>{

}
