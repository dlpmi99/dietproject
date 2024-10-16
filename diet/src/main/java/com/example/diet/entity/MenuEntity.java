package com.example.diet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "menuentity")
public class MenuEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	private String day;
	private String meal;
	private String rice;
	private String soup;
	private String side1;
	private String side2;
	private String side3;
	
	private Double bloodNo;
	private Double cholNo;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getMeal() {
		return meal;
	}
	public void setMeal(String meal) {
		this.meal = meal;
	}
	public String getRice() {
		return rice;
	}
	public void setRice(String rice) {
		this.rice = rice;
	}
	public String getSoup() {
		return soup;
	}
	public void setSoup(String soup) {
		this.soup = soup;
	}
	public String getSide1() {
		return side1;
	}
	public void setSide1(String side1) {
		this.side1 = side1;
	}
	public String getSide2() {
		return side2;
	}
	public void setSide2(String side2) {
		this.side2 = side2;
	}
	public String getSide3() {
		return side3;
	}
	public void setSide3(String side3) {
		this.side3 = side3;
	}
	public Double getBloodNo() {
		return bloodNo;
	}
	public void setBloodNo(Double bloodNo) {
		this.bloodNo = bloodNo;
	}
	public Double getCholNo() {
		return cholNo;
	}
	public void setCholNo(Double cholNo) {
		this.cholNo = cholNo;
	}
	@Override
	public String toString() {
		return "MenuEntity [no=" + no + ", day=" + day + ", meal=" + meal + ", rice=" + rice + ", soup=" + soup
				+ ", side1=" + side1 + ", side2=" + side2 + ", side3=" + side3 + ", bloodNo=" + bloodNo + ", cholNo="
				+ cholNo + "]";
	}
	
	
	
	
}
