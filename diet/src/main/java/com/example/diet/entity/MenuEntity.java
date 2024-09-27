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
}
