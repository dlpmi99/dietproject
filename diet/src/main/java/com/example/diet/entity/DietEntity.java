package com.example.diet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dietentity")
public class DietEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	private int age;
	private double height;
	private double weight;
	private double blood;
	private double cholesterol;
	
	@Override
	public String toString() {
		return "DietEntity [no=" + no + ", age=" + age + ", height=" + height + ", weight=" + weight + ", blood="
				+ blood + ", cholesterol=" + cholesterol + "]";
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getBlood() {
		return blood;
	}

	public void setBlood(double blood) {
		this.blood = blood;
	}

	public double getCholesterol() {
		return cholesterol;
	}

	public void setCholesterol(double cholesterol) {
		this.cholesterol = cholesterol;
	}
	
	
	
	
}
