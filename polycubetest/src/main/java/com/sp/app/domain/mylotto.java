package com.sp.app.domain;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "lotto")
@Data
public class mylotto {
	
	
	@Id
	@Column(length = 500, nullable = false)
	private long id;
	@Column(length = 500, nullable = false)
	private long number_1;
	@Column(length = 500, nullable = false)
	private long number_2;
	@Column(length = 500, nullable = false)
	private long number_3;
	@Column(length = 500, nullable = false)
	private long number_4;
	@Column(length = 500, nullable = false)
	private long number_5;
	@Column(length = 500, nullable = false)
	private long number_6;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}



}
