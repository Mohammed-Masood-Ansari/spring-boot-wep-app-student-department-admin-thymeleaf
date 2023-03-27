package com.zeromass.springbootwebappproject.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zeromass.springbootwebappproject.dto.Department;
import com.zeromass.springbootwebappproject.repository.DepartmentRepository;

@Repository
public class DepartmentDao {
	@Autowired
	private DepartmentRepository departmentRepository;
	
	/*
	 * Save Department method 
	 */
	public void saveDepartment(Department department) {
		departmentRepository.save(department);
	}
	
	/*
	 * in this method we have to pass department-id to fetch all the department
	 */
	public Department getDepartmentById(int departmentId) {
		
		Optional<Department> optional = departmentRepository.findById(departmentId);
		
		if(optional!=null) {
			
			return optional.get();
		}
		
		return null;
	}
	/*
	 * this method will give me all data from department
	 */
	public List<Department> getAllDepartmentData(){
		
		return departmentRepository.findAll();
	}
}
