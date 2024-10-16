package com.example.diet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "info_entity")
public class InfoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	private int bloodNo;
	
	private int cholNo;
	
	@Column(columnDefinition = "TEXT")
	private String title;
	@Column(columnDefinition = "LONGTEXT")
	private String info;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public int getBloodNo() {
		return bloodNo;
	}

	public void setBloodNo(int bloodNo) {
		this.bloodNo = bloodNo;
	}

	public int getCholNo() {
		return cholNo;
	}

	public void setCholNo(int cholNo) {
		this.cholNo = cholNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "InfoEntity [no=" + no + ", bloodNo=" + bloodNo + ", cholNo=" + cholNo + ", title=" + title + ", info="
				+ info + "]";
	}
	
}
