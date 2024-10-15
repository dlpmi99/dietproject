package com.example.diet.entity;

import java.sql.Blob;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;



@Entity
@Table(name = "support")
public class SupportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supportNo;

    private String spName;

    private String spTitle;

    
    @Column(columnDefinition = "TEXT") // 텍스트로 정의
    private String spContents;

    @Temporal(TemporalType.TIMESTAMP) // 작성 날짜 및 시간 자동 기록
    private Date createdAt;

    @Lob // 파일을 Blob 타입으로 저장
    private Blob spFile;
    
    // Getter 및 Setter
    public Long getSupportNo() {
        return supportNo;
    }

    public void setSupportNo(Long supportNo) {
        this.supportNo = supportNo;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public String getSpTitle() {
        return spTitle;
    }

    public void setSpTitle(String spTitle) {
        this.spTitle = spTitle;
    }

    public String getSpContents() {
        return spContents;
    }

    public void setSpContents(String spContents) {
        this.spContents = spContents;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "SupportEntity [supportNo=" + supportNo + ", spName=" + spName + ", spTitle=" + spTitle + ", spContents=" + spContents + ", createdAt=" + createdAt + "]";
    }

	public Blob getSpFile() {
		return spFile;
	}

	public void setSpFile(Blob spFile) {
		this.spFile = spFile;
	}
    
  
}