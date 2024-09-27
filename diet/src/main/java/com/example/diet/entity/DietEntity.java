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
	
	
	
	
}
