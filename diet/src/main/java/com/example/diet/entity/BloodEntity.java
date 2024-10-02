package com.example.diet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="blood")
public class BloodEntity {
	
	@Id
	private Long bloodNo;
	
	private double bloodFrom;
	
	private double bloodTo;

	public Long getBloodNo() {
		return bloodNo;
	}

	public void setBloodNo(Long bloodNo) {
		this.bloodNo = bloodNo;
	}

	public double getBloodFrom() {
		return bloodFrom;
	}

	public void setBloodFrom(double bloodFrom) {
		this.bloodFrom = bloodFrom;
	}

	public double getBloodTo() {
		return bloodTo;
	}

	public void setBloodTo(double bloodTo) {
		this.bloodTo = bloodTo;
	}
	
	
	

}
