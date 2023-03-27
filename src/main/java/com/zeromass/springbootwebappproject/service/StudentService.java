package com.zeromass.springbootwebappproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.zeromass.springbootwebappproject.dao.StudentDao;
import com.zeromass.springbootwebappproject.dto.ResponseStructure;
import com.zeromass.springbootwebappproject.dto.Student;

@Service
public class StudentService {
	@Autowired
	private StudentDao studentDao;

	@Autowired
	private ResponseStructure<Student> responseStructure;

	public ResponseStructure<Student> saveStudent(Student student) {

		int rollnumber = student.getStudentRollNumber();
		int countDigit=0;
		
		while(rollnumber!=0) {
			
			countDigit++;
			
			rollnumber/=10;
		}
		
		if (student != null) {

			responseStructure.setHttpCode(HttpStatus.ACCEPTED.value());
			responseStructure.setMsg("Data-Stored...");
			responseStructure.setDescription("student-saved-");
			if(((student.getStudentName().length()>=5)&&(student.getStudentName().length()<=45))
					&&((rollnumber>=3)&&(rollnumber<=5))) {
				responseStructure.setData(student);
			}
			
			studentDao.saveStudent(student);
			return responseStructure;
		} else {
			responseStructure.setHttpCode(HttpStatus.NO_CONTENT.value());
			responseStructure.setMsg("Data-are-not-Stored...");
			responseStructure.setDescription("student-are-null-please-check");
			responseStructure.setData(student);
			return responseStructure;
		}
	}
}
