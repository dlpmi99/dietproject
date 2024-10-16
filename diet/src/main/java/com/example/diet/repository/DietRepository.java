package com.example.diet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.diet.entity.InfoEntity;
import com.example.diet.entity.MenuEntity;

public interface DietRepository extends JpaRepository<MenuEntity, Long>{
	@Query("SELECT m FROM MenuEntity m " +
	           "JOIN BloodEntity b ON m.bloodNo = b.bloodNo " +
	           "JOIN CholesterolEntity c ON m.cholNo = c.cholNo " +
	           "WHERE :blood BETWEEN b.bloodFrom AND b.bloodTo " +
	           "AND :cholesterol BETWEEN c.cholFrom AND c.cholTo AND m.meal = :meal")
	    List<MenuEntity> findByBloodAndCholesterol(@Param("blood") double blood, 
	                                              @Param("cholesterol") double cholesterol,
	                                              @Param("meal") String meal);

	



}