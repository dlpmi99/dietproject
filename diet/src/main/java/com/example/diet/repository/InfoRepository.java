package com.example.diet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.diet.entity.InfoEntity;



public interface InfoRepository extends JpaRepository<InfoEntity, Long>{
	@Query("SELECT m FROM InfoEntity m " +
	           "JOIN BloodEntity b ON m.bloodNo = b.bloodNo " +
	           "JOIN CholesterolEntity c ON m.cholNo = c.cholNo " +
	           "WHERE :blood BETWEEN b.bloodFrom AND b.bloodTo " +
	           "AND :cholesterol BETWEEN c.cholFrom AND c.cholTo ")
	public  Optional<InfoEntity> findHealthInfoByBloodAndCholesterol(@Param("blood") double blood, 
            @Param("cholesterol") double cholesterol);
}
