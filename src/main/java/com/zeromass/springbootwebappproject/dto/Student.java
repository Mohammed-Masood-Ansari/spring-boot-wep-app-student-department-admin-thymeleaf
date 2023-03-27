package com.zeromass.springbootwebappproject.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "studentid")
	private int studentId;
	@Column(name = "studentname")
	private String studentName;
	@Column(name = "studentrollnumber")
	private int studentRollNumber;

	@OneToOne
	@JoinColumn(name = "departmentid")
	private Department department;
}
