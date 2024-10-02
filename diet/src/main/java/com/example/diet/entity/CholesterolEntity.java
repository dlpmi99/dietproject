package com.example.diet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cholesterol")
public class CholesterolEntity {
	@Id
	private Long cholNo;
	
	private double cholFrom;
	
	private double cholTo;

	public Long getCholNo() {
		return cholNo;
	}

	public void setCholNo(Long cholNo) {
		this.cholNo = cholNo;
	}

	public double getCholFrom() {
		return cholFrom;
	}

	public void setCholFrom(double cholFrom) {
		this.cholFrom = cholFrom;
	}

	public double getCholTo() {
		return cholTo;
	}

	public void setCholTo(double cholTo) {
		this.cholTo = cholTo;
	}
	
	
	

}
