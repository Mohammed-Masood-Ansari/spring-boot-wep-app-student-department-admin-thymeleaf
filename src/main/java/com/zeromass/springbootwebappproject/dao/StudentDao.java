package com.zeromass.springbootwebappproject.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zeromass.springbootwebappproject.dto.Department;
import com.zeromass.springbootwebappproject.dto.Student;
import com.zeromass.springbootwebappproject.repository.StudentRepository;

@Repository
public class StudentDao {
	
	@Autowired
	private StudentRepository repository;

	@Autowired
	private DepartmentDao departmentDao;

	public void saveStudent(Student student) {
		if (student != null) {
			repository.save(student);
		}
	}

	public Student getStudentById(int studentId) {

		Optional<Student> optional = repository.findById(studentId);

		if (optional.isPresent()) {

			return optional.get();
		}
		return null;
	}
	
	public List<Student> getAllStudents(){
		
		return repository.findAll();
	}
	
	/*
	 * update student method
	 */
	public void updateStudent(Student student) {
		repository.save(student);
	}
}
